package ximulib;

public class CalADXL345busData extends xIMUdata {
	private float[] ADXL345_A;
	private float[] ADXL345_B;
	private float[] ADXL345_C;
	private float[] ADXL345_D;
	
	public float[] getADXL345_A() {
		return ADXL345_A;
	}
	
	public void setADXL345_A(float[] aDXL345_A) {
		ADXL345_A = aDXL345_A;
	}

	public float[] getADXL345_B() {
		return ADXL345_B;
	}

	public void setADXL345_B(float[] aDXL345_B) {
		ADXL345_B = aDXL345_B;
	}

	public float[] getADXL345_C() {
		return ADXL345_C;
	}

	public void setADXL345_C(float[] aDXL345_C) {
		ADXL345_C = aDXL345_C;
	}

	public float[] getADXL345_D() {
		return ADXL345_D;
	}

	public void setADXL345_D(float[] aDXL345_D) {
		ADXL345_D = aDXL345_D;
	}

	public CalADXL345busData(float[] ADXL345_A, float[] ADXL345_B, float[] ADXL345_C, float[] ADXL345_D) {
		this.ADXL345_A = ADXL345_A;
		this.ADXL345_B = ADXL345_B;
		this.ADXL345_C = ADXL345_C;
		this.ADXL345_D = ADXL345_D;
	}

}
