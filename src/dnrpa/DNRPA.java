package dnrpa;

import exceptions.SectionalRegisterException;
import exceptions.VehicleException;
import vehicle.model.Vehicle;
import vehicle.type.Truck;

import java.util.*;


public class DNRPA {

	Set<SectionalRegister> sectionalRegisters = new HashSet<>();

	public void addSeccionalRegister(SectionalRegister sectionalR) {
		sectionalRegisters.add(sectionalR);
	}

	public void showSectionalRegistrer() {
		sectionalRegisters.forEach(
				sectionalRegister -> System.out.println("Seccional Register: " + sectionalRegister.getIdSR())
		);
	}

	public SectionalRegister getSectionalRegister(Integer id) throws SectionalRegisterException {
		Optional<SectionalRegister> sectionalRegisterOptional = sectionalRegisters.stream().filter(
				sectionalReg -> sectionalReg.getIdSR().equals(id)
		).findFirst();
		return sectionalRegisterOptional.orElseThrow(
				() -> new SectionalRegisterException("sr_not_found", "Sectional register not found")
		);
	}

	public void showAllVehiclesInSectionalRegisters() {
		if (sectionalRegisters == null || sectionalRegisters.isEmpty()) {
			System.out.println("There is no information to show");
		} else {
			sectionalRegisters.forEach(sectionalRegister -> {
						System.out.println("Sectional Register: " + sectionalRegister.getIdSR());
						sectionalRegister.showVehicles();
					}
			);
		}
	}

	public void showTruckOwnerNamesInAlphabeticOrder() {
		List<String> ownerNames = new ArrayList<>();
		if (ownerNames == null || ownerNames.isEmpty()) {
			System.out.println("There is no information to show");
		} else {
			sectionalRegisters.forEach(sectionalRegister -> {
				sectionalRegister.getListVehicle().values().stream().filter(
						vehicle -> Truck.class.isInstance(vehicle)).map(vehicle -> ownerNames.add(vehicle.getOwner().getName()));
			});

			Collections.sort(ownerNames);

			System.out.println("Messy Drivers :");
			sectionalRegisters.forEach(
					sectionalRegister -> sectionalRegister.getListVehicle().values().stream().filter(
							vehicle -> Truck.class.isInstance(vehicle)).forEach(
							vehicle -> System.out.println("Drivers : " + vehicle.getOwner().getName()))
			);

			System.out.println("Sort Drivers:");
			ownerNames.forEach(ownerName -> System.out.println("Drivers : " + ownerName));
		}

	}

	public Vehicle searchVehicleByPatent(String patent) throws VehicleException {
		if (patent == null || "".equals(patent.trim())) {

			throw new VehicleException("invalid_patent", "Patent can not be empty");
		}

		for (SectionalRegister sectionalRegister : sectionalRegisters) {
			final Optional<Vehicle> vehicleOpt = sectionalRegister.getVehicleByPatent(patent);
			if (vehicleOpt.isPresent()) {
				return vehicleOpt.get();
			}
		}
		throw new VehicleException("vehicle_not_found", "Vehicle not found for this patent: " + patent);
	}
}

