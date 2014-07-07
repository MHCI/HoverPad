package ximulib;

public enum PacketHeaders {
	Error,
    Command,
    ReadRegister,
    WriteRegister,
    ReadDateTime,
    WriteDateTime,
    RawBatteryAndThermometerData,
    CalBatteryAndThermometerData,
    RawInertialAndMagneticData,
    CalInertialAndMagneticData,
    QuaternionData,
    DigitalIOdata,
    RawAnalogueInputData,
    CalAnalogueInputData,
    PWMoutputData,
    RawADXL345busData,
    CalADXL345busData;
}
