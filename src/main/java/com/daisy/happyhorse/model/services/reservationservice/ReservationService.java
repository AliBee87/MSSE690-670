package com.daisy.happyhorse.model.services.reservationservice;

import com.daisy.happyhorse.model.domain.Horse;
import com.daisy.happyhorse.model.domain.RentalCart;
import com.daisy.happyhorse.model.domain.Reservation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class ReservationService implements IReservationService {
	
	private static final Logger logger = LogManager.getLogger("com.daisy.happyhorse");
	
	public Reservation confirmReservation(RentalCart cart) {
		if(cart.getHorses().isEmpty()) {
			logger.error("No horses selected for checkout");
			return null;
		}
		
		Reservation reservation = new Reservation(new Random().nextInt(1000), cart.getCustomer(), cart.getHorses());
		for (Horse horse : reservation.getReservedHorses()) {
			horse.setAvailable(false);
		}
		
		logger.info("Success! Reservation created. Reservation ID: " + reservation.getId());
		
		return reservation;
	}
}