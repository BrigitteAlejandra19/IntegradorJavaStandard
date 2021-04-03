package vehicle.type;

import driver.type.Owner;
import vehicle.use.CarUse;
import vehicle.model.Vehicle;
import vehicle.type.motor.CombustionVehicle;

import java.time.LocalDate;

public class CombustionMotorcycle extends Vehicle implements CombustionVehicle {
    public CombustionMotorcycle(CarUse carUse, Owner owner, LocalDate registrationDate) {
        super(carUse, owner, registrationDate);
    }

    @Override
    public String getVehicleType() {
        return "Combustion Motorcycle";
    }
}
