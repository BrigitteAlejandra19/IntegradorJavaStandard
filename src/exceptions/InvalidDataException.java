package exceptions;

public class InvalidDataException extends BaseException {

    public InvalidDataException(String codeError, String descriptionError) {
        super(codeError, descriptionError);
    }

    @Override
    public String toString() {
        return "InvalidDataException{" +
                "codeError='" + this.getCodeError() + '\'' +
                ", descriptionError='" + this.getDescriptionError() + '\'' +
                '}';
    }
}
