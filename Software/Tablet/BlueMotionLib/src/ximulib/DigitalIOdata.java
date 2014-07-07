package ximulib;

public class DigitalIOdata extends xIMUdata {
	
	private DigitalPortBits direction;
	private DigitalPortBits state;
	
	public DigitalIOdata(byte direction, byte state) {
		this.direction = new DigitalPortBits();
		this.direction.SetBitsFromByte(direction);
		this.state = new DigitalPortBits();
		this.state.SetBitsFromByte(state);
	}

	public DigitalPortBits getDirection() {
		return direction;
	}

	public void setDirection(DigitalPortBits direction) {
		this.direction = direction;
	}

	public DigitalPortBits getState() {
		return state;
	}

	public void setState(DigitalPortBits state) {
		this.state = state;
	}
}
