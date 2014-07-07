package ximulib;

public class RawADXL345busData extends xIMUdata {
	private short[] ADXL345_A;
	private short[] ADXL345_B;
	private short[] ADXL345_C;
	private short[] ADXL345_D;
	
	public short[] getADXL345_A() {
		return ADXL345_A;
	}

	public void setADXL345_A(short[] aDXL345_A) {
		ADXL345_A = aDXL345_A;
	}

	public short[] getADXL345_B() {
		return ADXL345_B;
	}

	public void setADXL345_B(short[] aDXL345_B) {
		ADXL345_B = aDXL345_B;
	}

	public short[] getADXL345_C() {
		return ADXL345_C;
	}

	public void setADXL345_C(short[] aDXL345_C) {
		ADXL345_C = aDXL345_C;
	}

	public short[] getADXL345_D() {
		return ADXL345_D;
	}

	public void setADXL345_D(short[] aDXL345_D) {
		ADXL345_D = aDXL345_D;
	}

	public RawADXL345busData(short[] ADXL345_A, short[] ADXL345_B, short[] ADXL345_C, short[] ADXL345_D) {
		this.ADXL345_A = ADXL345_A;
		this.ADXL345_B = ADXL345_B;
		this.ADXL345_C = ADXL345_C;
		this.ADXL345_D = ADXL345_D;
	}

}
