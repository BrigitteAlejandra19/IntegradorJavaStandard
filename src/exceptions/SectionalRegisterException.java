package exceptions;

public class SectionalRegisterException extends BaseException {

	public SectionalRegisterException(String codeError, String descriptionError) {
		super(codeError, descriptionError);
	}

	@Override
	public String toString() {
		return "SectionalRegisterException{" +
				"codeError='" + this.getCodeError() + '\'' +
				", descriptionError='" + this.getDescriptionError() + '\'' +
				'}';
	}
}
