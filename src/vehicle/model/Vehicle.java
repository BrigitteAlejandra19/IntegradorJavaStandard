package vehicle.model;

import driver.type.AuthorizedDriver;
import driver.type.Owner;
import exceptions.ChangeOwnerException;
import vehicle.use.CarUse;
import vehicle.patent.PatentGenerator;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public abstract class Vehicle {

	private String patent;
	private CarUse carUse;
	private Owner owner;
	private Set<AuthorizedDriver> authorizedDrivers = new HashSet<>();
	private LocalDate registrationDate;
	private LocalDate changeDate;

	public Vehicle(CarUse carUse, Owner owner, LocalDate registrationDate) {
		this.patent = PatentGenerator.getNewPatent();
		this.carUse = carUse;
		this.owner = owner;
		this.registrationDate = registrationDate;
	}

	public String getPatent() {
		return patent;
	}


	public CarUse getCarUse() {
		return carUse;
	}

	public void setCarUse(CarUse carUse) {
		this.carUse = carUse;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Set<AuthorizedDriver> getAuthorizedDriver() {
		return authorizedDrivers;
	}

	public void addAuthorizedDriver(final AuthorizedDriver authorizedDriver) {
		authorizedDrivers.add(authorizedDriver);
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	public LocalDate getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(LocalDate changeDate) {
		this.changeDate = changeDate;
	}

	public void changeOwner(Owner newOwner, LocalDate newDate) throws ChangeOwnerException {
		if (newOwner == null || newDate == null) {
			throw new ChangeOwnerException("change_owner_param_error", "Invalid new owner or new date");
		}
		if (this.changeDate != null) {
			// Second change of owner
			LocalDate oneYearLater = registrationDate.plusYears(1);
			if (!newDate.isAfter(oneYearLater)) {
				throw new ChangeOwnerException("change_owner_date_error", "Must past one year to change the owner");
			}
		}
		this.changeDate = newDate;
		this.owner = newOwner;

	}

	public abstract String getVehicleType();
}
