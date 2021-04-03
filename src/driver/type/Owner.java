package driver.type;

import driver.model.Driver;
import exceptions.InvalidDataException;

public class Owner extends Driver {


    public Owner(String name, String dni, String direction) throws InvalidDataException {
        super(name, dni, direction);
    }


}
