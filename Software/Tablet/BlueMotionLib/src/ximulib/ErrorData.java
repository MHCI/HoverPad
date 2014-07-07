package ximulib;

public class ErrorData extends xIMUdata {
	private ErrorCodes errorCode;
	
	public ErrorCodes getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCodes errorCode) {
		this.errorCode = errorCode;
	}

	public ErrorData(short errorCode) throws Exception
    {
		try{
			this.errorCode = ErrorCodes.values()[errorCode];
		}catch(Exception e){
			throw new Exception("Invalid error code.");
		}
    }
}
