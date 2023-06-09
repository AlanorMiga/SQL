package exceptions;

public class CamposVaciosException extends Exception {

	public CamposVaciosException() {
		super("Favor de llenar todos los campos");
	}

	public CamposVaciosException(String nombre) {
		super("Favor de llenar el campo " + nombre + ".");
	}

}
