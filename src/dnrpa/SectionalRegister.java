package dnrpa;

import vehicle.model.Vehicle;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class SectionalRegister {

	private Integer idSR;
	Map<String, Vehicle> listVehicle = new HashMap<>();

	public SectionalRegister(Integer idSR) {
		this.idSR = idSR;
	}

	public Integer getIdSR() {
		return idSR;
	}

	public void setIdSR(Integer idSR) {
		this.idSR = idSR;
	}

	public Map<String, Vehicle> getListVehicle() {
		return listVehicle;
	}


	public void addVehicle(Vehicle vehicle) {
		listVehicle.put(vehicle.getPatent(), vehicle);
	}


	public void showVehicles() {
		if (listVehicle == null || listVehicle.isEmpty()) {
			System.out.println("There is no information to show");
		}
		listVehicle.values().forEach(vehicle -> System.out.println("Patent: " + vehicle.getPatent() + " - Owner: " + vehicle.getOwner() + " - Register Date: " + vehicle.getRegistrationDate()));
	}


	public Optional<Vehicle> getVehicleByPatent(final String patent) {
		return Optional.ofNullable(listVehicle.get(patent));
	}


}
