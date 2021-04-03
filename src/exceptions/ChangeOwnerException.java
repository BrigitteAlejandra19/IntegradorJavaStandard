package exceptions;

public class ChangeOwnerException extends BaseException {

    public ChangeOwnerException(String codeError, String descriptionError) {
        super(codeError, descriptionError);
    }

    @Override
    public String toString() {
        return "ChangeOwnerException{" +
                "codeError='" + this.getCodeError() + '\'' +
                ", descriptionError='" + this.getDescriptionError() + '\'' +
                '}';
    }
}
