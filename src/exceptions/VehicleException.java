package exceptions;

public class VehicleException extends BaseException {
	public VehicleException(String codeError, String descriptionError) {
		super(codeError, descriptionError);
	}

	@Override
	public String toString() {
		return "VehicleException{" +
				"codeError='" + this.getCodeError() + '\'' +
				", descriptionError='" + this.getDescriptionError() + '\'' +
				'}';
	}
}
