package ar.com.codoacodo.interfaces;

public class SMSLogger implements ILogger {
	
	private String number;
	

	public void log() {
		
		System.out.println("Enviando SMS al :" + getNumber());
	}
	
	public void setNumber (String number) {
		this.number = number;
	}

	public String getNumber() {
		return this.number;
	}
	
	

}
