package driver.type;

import driver.model.Driver;
import exceptions.InvalidDataException;

public class AuthorizedDriver extends Driver {
	public AuthorizedDriver(String name, String dni, String direction) throws InvalidDataException {
		super(name, dni, direction);
	}
}
