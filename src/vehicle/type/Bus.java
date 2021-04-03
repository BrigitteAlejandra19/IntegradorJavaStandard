package vehicle.type;

import vehicle.type.motor.CombustionVehicle;
import driver.type.Owner;
import vehicle.use.CarUse;
import vehicle.model.Vehicle;

import java.time.LocalDate;

public class Bus extends Vehicle implements CombustionVehicle {
	public Bus(CarUse carUse, Owner owner, LocalDate registrationDate) {
		super(carUse, owner, registrationDate);
	}

	@Override
	public String getVehicleType() {
		return "Bus";
	}
}
