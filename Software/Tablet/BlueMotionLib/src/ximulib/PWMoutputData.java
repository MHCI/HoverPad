package ximulib;

public class PWMoutputData extends xIMUdata {
	
	private short AX0;
	private short AX2;
	private short AX4;
	private short AX6;
	
	public short getAX0() {
		return AX0;
	}

	public void setAX0(short aX0) {
		AX0 = aX0;
	}

	public short getAX2() {
		return AX2;
	}

	public void setAX2(short aX2) {
		AX2 = aX2;
	}

	public short getAX4() {
		return AX4;
	}

	public void setAX4(short aX4) {
		AX4 = aX4;
	}

	public short getAX6() {
		return AX6;
	}

	public void setAX6(short aX6) {
		AX6 = aX6;
	}

	public PWMoutputData(short AX0, short AX2, short AX4, short AX6) {
		this.AX0 = AX0;
		this.AX2 = AX2;
		this.AX4 = AX4;
		this.AX6 = AX6;
	}

}
