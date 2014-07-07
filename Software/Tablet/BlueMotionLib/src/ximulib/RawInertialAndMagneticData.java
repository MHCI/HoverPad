package ximulib;

public class RawInertialAndMagneticData extends xIMUdata {
	private short[] gyroscope;
	private short[] accelerometer;
	private short[] magnetometer;
	
	public RawInertialAndMagneticData(short[] gyroscope, short[] accelerometer, short[] magnetometer) {
		this.gyroscope = gyroscope;
		this.accelerometer = accelerometer;
		this.magnetometer = magnetometer;
	}

	public short[] getGyroscope() {
		return gyroscope;
	}

	public void setGyroscope(short[] gyroscope) {
		this.gyroscope = gyroscope;
	}

	public short[] getAccelerometer() {
		return accelerometer;
	}

	public void setAccelerometer(short[] accelerometer) {
		this.accelerometer = accelerometer;
	}

	public short[] getMagnetometer() {
		return magnetometer;
	}

	public void setMagnetometer(short[] magnetometer) {
		this.magnetometer = magnetometer;
	}

}
