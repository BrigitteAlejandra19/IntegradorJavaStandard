package exceptions;

public class BaseException extends Exception {
	private String codeError;
	private String descriptionError;

	public BaseException(String codeError, String descriptionError) {
		super(descriptionError);
		this.codeError = codeError;
		this.descriptionError = descriptionError;
	}

	public String getCodeError() {
		return codeError;
	}

	public String getDescriptionError() {
		return descriptionError;
	}

	@Override
	public String toString() {
		return "BaseException{" +
				"codeError='" + codeError + '\'' +
				", descriptionError='" + descriptionError + '\'' +
				'}';
	}
}
