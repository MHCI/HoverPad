package ximulib;
public enum Qvals {
			CalibratedBattery(12),
	        CalibratedThermometer(8),
	        CalibratedGyroscope(4),
	        CalibratedAccelerometer(11),
	        CalibratedMagnetometer(11),
	        Quaternion(15),
	        BatterySensitivity(5),
	        BatteryBias(8),
	        ThermometerSensitivity(6),
	        ThermometerBias(0),
	        GyroscopeSensitivity(7),
	        GyroscopeSampled200dps(0),
	        GyroscopeBiasAt25degC(3),
	        GyroscopeBiasTempSensitivity(11),
	        GyroscopeSampledBias(3),
	        AccelerometerSensitivity(4),
	        AccelerometerBias(8),
	        AccelerometerSampled1g(4),
	        MagnetometerSensitivity(4),
	        MagnetometerBias(8),
	        MagnetometerHardIronBias(11),
	        AlgorithmKp(11),
	        AlgorithmKi(15),
	        AlgorithmInitKp(11),
	        AlgorithmInitPeriod(11),
	        CalibratedAnalogueInput(12),
	        AnalogueInputSensitivity(4),
	        AnalogueInputBias(8),
	        CalibratedADXL345(10),
	        ADXL345busSensitivity(6),
	        ADXL345busBias(8);
	 
	 private int code;
	 
	 private Qvals(int c) {
	   code = c;
	 }
	 
	 public int getCode() {
	   return code;
	 }
}