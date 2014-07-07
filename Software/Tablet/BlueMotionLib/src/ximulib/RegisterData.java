package ximulib;

public class RegisterData extends xIMUdata {
	private RegisterAddresses address;
	private int value;
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public RegisterAddresses getAddress() {
		return address;
	}
	public void setAddress(RegisterAddresses address) {
		this.address = address;
	}
	
	public RegisterData(short address, short value) throws Exception {
		try{
			this.address = RegisterAddresses.values()[address];
			this.value = value;
		}catch(Exception e){
			throw new Exception("Invalid register address.");
		}
	}

}
