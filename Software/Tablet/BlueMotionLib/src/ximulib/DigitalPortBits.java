package ximulib;

public class DigitalPortBits {
	
	private boolean AX0;
	private boolean AX1;
	private boolean AX2;
	private boolean AX3;
	private boolean AX4;
	private boolean AX5;
	private boolean AX6;
	private boolean AX7;
	
	public boolean isAX0() {
		return AX0;
	}

	public void setAX0(boolean aX0) {
		AX0 = aX0;
	}

	public boolean isAX1() {
		return AX1;
	}

	public void setAX1(boolean aX1) {
		AX1 = aX1;
	}

	public boolean isAX2() {
		return AX2;
	}

	public void setAX2(boolean aX2) {
		AX2 = aX2;
	}

	public boolean isAX3() {
		return AX3;
	}

	public void setAX3(boolean aX3) {
		AX3 = aX3;
	}

	public boolean isAX4() {
		return AX4;
	}

	public void setAX4(boolean aX4) {
		AX4 = aX4;
	}

	public boolean isAX5() {
		return AX5;
	}

	public void setAX5(boolean aX5) {
		AX5 = aX5;
	}

	public boolean isAX6() {
		return AX6;
	}

	public void setAX6(boolean aX6) {
		AX6 = aX6;
	}

	public boolean isAX7() {
		return AX7;
	}

	public void setAX7(boolean aX7) {
		AX7 = aX7;
	}
	
	public DigitalPortBits(){
		AX0 = false;
		AX1 = false;
		AX2 = false;
		AX3 = false;
		AX4 = false;
		AX5 = false;
		AX6 = false;
		AX7 = false;	
	}

	public void SetBitsFromByte(byte byteValue) {
		AX7 = (byteValue & 0x80) == 0x80;
		AX6 = (byteValue & 0x40) == 0x40;
		AX5 = (byteValue & 0x20) == 0x20;
		AX4 = (byteValue & 0x10) == 0x10;
		AX3 = (byteValue & 0x08) == 0x08;
		AX2 = (byteValue & 0x04) == 0x04;
		AX1 = (byteValue & 0x02) == 0x02;
		AX0 = (byteValue & 0x01) == 0x01;	
	}
	
	public byte ConvertToByte()
    {
        return (byte)((AX7 ? 0x80 : 0x00) | (AX6 ? 0x40 : 0x00) | (AX5 ? 0x20 : 0x00) | (AX4 ? 0x10 : 0x00) | (AX3 ? 0x08 : 0x00) | (AX2 ? 0x04 : 0x00) | (AX1 ? 0x02 : 0x00) | (AX0 ? 0x01 : 0x00));
    }
}
