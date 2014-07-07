package ximulib;

public class CalAnalogueInputData extends xIMUdata {

	private float AX0;
	private float AX1;
	private float AX2;
	private float AX3;
	private float AX4;
	private float AX5;
	private float AX6;
	private float AX7;
	
	public float getAX0() {
		return AX0;
	}

	public void setAX0(float aX0) {
		AX0 = aX0;
	}

	public float getAX1() {
		return AX1;
	}

	public void setAX1(float aX1) {
		AX1 = aX1;
	}

	public float getAX2() {
		return AX2;
	}

	public void setAX2(float aX2) {
		AX2 = aX2;
	}

	public float getAX3() {
		return AX3;
	}

	public void setAX3(float aX3) {
		AX3 = aX3;
	}

	public float getAX4() {
		return AX4;
	}

	public void setAX4(float aX4) {
		AX4 = aX4;
	}

	public float getAX5() {
		return AX5;
	}

	public void setAX5(float aX5) {
		AX5 = aX5;
	}

	public float getAX6() {
		return AX6;
	}

	public void setAX6(float aX6) {
		AX6 = aX6;
	}

	public float getAX7() {
		return AX7;
	}

	public void setAX7(float aX7) {
		AX7 = aX7;
	}

	public CalAnalogueInputData(float AX0, float AX1,
			float AX2, float AX3, float AX4,
			float AX5, float AX6, float AX7) {
		
		this.AX0 = AX0;
		this.AX1 = AX1;
		this.AX2 = AX2;
		this.AX3 = AX3;
		this.AX4 = AX4;
		this.AX5 = AX5;
		this.AX6 = AX6;
		this.AX7 = AX7;
	}
	
	public CalAnalogueInputData(){
		this.AX0 = 0;
		this.AX1 = 0;
		this.AX2 = 0;
		this.AX3 = 0;
		this.AX4 = 0;
		this.AX5 = 0;
		this.AX6 = 0;
		this.AX7 = 0;
	}

}
