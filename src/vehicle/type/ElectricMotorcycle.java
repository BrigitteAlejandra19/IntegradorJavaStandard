package vehicle.type;

import driver.type.Owner;
import vehicle.use.CarUse;
import vehicle.model.Vehicle;
import vehicle.type.motor.ElectricVehicle;

import java.time.LocalDate;

public class ElectricMotorcycle extends Vehicle implements ElectricVehicle {
    public ElectricMotorcycle(CarUse carUse, Owner owner, LocalDate registrationDate) {
        super(carUse, owner, registrationDate);
    }

    @Override
    public String getVehicleType() {
        return "Electric Motorcycle";
    }
}
