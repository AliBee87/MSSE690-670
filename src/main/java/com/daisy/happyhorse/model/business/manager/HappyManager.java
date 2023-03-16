package com.daisy.happyhorse.model.business.manager;

import com.daisy.happyhorse.model.domain.Horse;
import com.daisy.happyhorse.model.domain.RentalCart;
import com.daisy.happyhorse.model.services.exception.ServiceLoadException;
import com.daisy.happyhorse.model.services.factory.IServiceFactory;
import com.daisy.happyhorse.model.services.manager.IPropertyManager;
import com.daisy.happyhorse.model.services.repository.IRepositoryWrapper;
import com.daisy.happyhorse.model.services.reservationservice.ReservationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class HappyManager extends Manager {
    private static final Logger logger = LogManager.getLogger("com.daisy.happyhorse");
    private final IRepositoryWrapper repository;
    private final IServiceFactory factory;

    public HappyManager(IPropertyManager propertyManager, IRepositoryWrapper repository,
                                 IServiceFactory factory) {
        super(propertyManager);
        this.repository = repository;
        this.factory = factory;
    }

  
    public ArrayList<Horse> getAvailableHorses() {
        return new ArrayList<Horse>(
                repository.Horses().getAll().stream().filter(p -> p.isAvailable()).collect(Collectors.toList()));
    }

    public Horse findHorse(int id) {
        return repository.Horses().getById(id);
    }

    @Override
    public boolean performAction(String command, RentalCart cart) {
        switch (command) {
            case "IReservationService":
                return processCreateReservation(command, cart);
        }

        return false;
    }

    private boolean processCreateReservation(String serviceName, RentalCart cart) {
        ReservationService reservationService;

        boolean result = false;
        try {
            reservationService = (ReservationService) factory.getService(serviceName);
            result = reservationService.confirmReservation(cart) != null;
        } catch (ServiceLoadException e) {
            logger.error("Could not load reservation service", e);
        }

        return result;
    }

}