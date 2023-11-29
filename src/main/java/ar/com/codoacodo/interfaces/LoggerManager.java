package ar.com.codoacodo.interfaces;

public class LoggerManager {

	public static ILogger getLogger (String target) {
		// Interface i = new claseQueImplementa ()
		
//		ILogger i = new SMSLogger();
//		
//		ILogger i2 = new FSLogger();
//		
//		ILogger i3 = new EmailLogger();
		
		ILogger logger;
		
		switch (target) {
		case "A": 
			logger = new SMSLogger();
			((SMSLogger)logger).setNumber("+54238172");
			break;
		case "B": 
			logger = new FSLogger();
			break;
		case "C": 
			logger = new EmailLogger();
			break;	
		default:
			logger = null;
		}
		return logger;
	}
}
