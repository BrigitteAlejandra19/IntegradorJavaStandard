package driver.model;

import exceptions.InvalidDataException;

public abstract class Driver {

	private String name;
	private String dni;
	private String direction;


	public Driver(String name, String dni, String direction) throws InvalidDataException {
		this.name = name;
		if (String.valueOf(dni).length() != 8) {
			throw new InvalidDataException("invalid_data", "The DNI must have 8 digits.");
		} else {
			this.dni = dni;
		}
		this.direction = direction;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) throws InvalidDataException {
		if (String.valueOf(dni).length() != 8) {
			throw new InvalidDataException("invalid_data", "The DNI must have 8 digits.");
		} else {
			this.dni = dni;
		}
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	@Override
	public String toString() {
		return "Driver{" +
				"name='" + name + '\'' +
				", dni='" + dni + '\'' +
				", direction='" + direction + '\'' +
				'}';
	}
}
