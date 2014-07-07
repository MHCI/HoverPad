package ximulib;

public class CalInertialAndMagneticData extends xIMUdata {
	private float[] gyroscope;
	private float[] accelerometer;
	private float[] magnetometer;
	
	public float[] getGyroscope() {
		return gyroscope;
	}

	public void setGyroscope(float[] gyroscope) {
		this.gyroscope = gyroscope;
	}

	public float[] getAccelerometer() {
		return accelerometer;
	}

	public void setAccelerometer(float[] accelerometer) {
		this.accelerometer = accelerometer;
	}

	public float[] getMagnetometer() {
		return magnetometer;
	}

	public void setMagnetometer(float[] magnetometer) {
		this.magnetometer = magnetometer;
	}

	public CalInertialAndMagneticData(float[] gyroscope, float[] accelerometer, float[] magnetometer) {
		this.gyroscope = gyroscope;
		this.accelerometer = accelerometer;
		this.magnetometer = magnetometer;
	}
	
	public CalInertialAndMagneticData() {
	 	this.gyroscope = new float[] {0,0,0};
		this.accelerometer = new float[] {0,0,0};
		this.magnetometer = new float[] {0,0,0};
	}

}
