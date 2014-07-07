package ximulib;

public class CalBatteryAndThermometerData extends xIMUdata {
	private float batteryVoltage;
	private float thermometer;
	
	public float getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(float batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public float getThermometer() {
		return thermometer;
	}

	public void setThermometer(float thermometer) {
		this.thermometer = thermometer;
	}

	public CalBatteryAndThermometerData(float batteryVoltage, float thermometer) {
		this.batteryVoltage = batteryVoltage;
		this.thermometer = thermometer;
	}
	
	public CalBatteryAndThermometerData() {
		this.batteryVoltage = 0;
		this.thermometer = 0;
	}

}
