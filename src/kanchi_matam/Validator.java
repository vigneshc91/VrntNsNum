package kanchi_matam;

public class Validator {

	private static final String NS_NUM_VALIDATOR = "\\w{1,2}/\\w{1,3}";
	private static final String PINCODE_VALIDATOR = "\\d{6}";
	private static final String STRING_VALIDATOR = "\\w+";
	
	public boolean IsValidNsNumber(String nsNum){
		return nsNum.matches(NS_NUM_VALIDATOR);
	}
	
	public boolean IsVaidPinCode(String pinCode){
		return pinCode.matches(PINCODE_VALIDATOR);
	}
	
	public boolean IsValidString(String inputString){
		return inputString.matches(STRING_VALIDATOR);
	}
}
