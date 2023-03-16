package com.daisy.happyhorse.model.services.reservationservice; 

import com.daisy.happyhorse.model.domain.RentalCart;
import com.daisy.happyhorse.model.domain.Reservation;
import com.daisy.happyhorse.model.services.IService;

public interface IReservationService extends IService {
	Reservation confirmReservation(RentalCart cart);
}