package ximulib;

public class RawBatteryAndThermometerData extends xIMUdata {
	private short batteryVoltage;
	private short thermometer;
	
	public short getBatteryVoltage() {
		return batteryVoltage;
	}



	public void setBatteryVoltage(short batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}



	public short getThermometer() {
		return thermometer;
	}



	public void setThermometer(short thermometer) {
		this.thermometer = thermometer;
	}



	public RawBatteryAndThermometerData(short batteryVoltage, short thermometer) {
		this.batteryVoltage = batteryVoltage;
		this.thermometer = thermometer;
	}

}
