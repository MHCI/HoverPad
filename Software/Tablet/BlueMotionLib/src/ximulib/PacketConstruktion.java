package ximulib;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class PacketConstruktion {
	
	public static byte[] ConstructCommandPacket(CommandCodes commandCode)
    {
        byte[] decodedPacket = new byte[] { (byte)PacketHeaders.Command.ordinal(),
                                            (byte)((short)commandCode.ordinal() >> 8),
                                            (byte)commandCode.ordinal(),
                                            0};
        decodedPacket = InsertChecksum(decodedPacket);
        return PacketEncoding.EncodePacket(decodedPacket);
    }
	
	public static byte[] ConstructReadRegisterPacket(RegisterData registerData)
    {
        byte[] decodedPacket = new byte[] { (byte)PacketHeaders.ReadRegister.ordinal(),
                                            (byte)((short)registerData.getAddress().ordinal() >> 8),
                                            (byte)registerData.getAddress().ordinal(),
                                            0};
        decodedPacket = InsertChecksum(decodedPacket);
        return PacketEncoding.EncodePacket(decodedPacket);
    }
	
	public static byte[] ConstructWriteRegisterPacket(RegisterData registerData)
    {
        byte[] decodedPacket = new byte[] { (byte)PacketHeaders.WriteRegister.ordinal(),
                                            (byte)((short)registerData.getAddress().ordinal() >> 8),
                                            (byte)registerData.getAddress().ordinal(),
                                            (byte)(registerData.getValue() >> 8),
                                            (byte)registerData.getValue(),
                                            0};
        decodedPacket = InsertChecksum(decodedPacket);
        return PacketEncoding.EncodePacket(decodedPacket);
    }
	
	public static byte[] ConstructReadDateTimePacket()
    {
        byte[] decodedPacket = new byte[] { (byte)PacketHeaders.ReadDateTime.ordinal(),
                                             0};
        decodedPacket = InsertChecksum(decodedPacket);
        return PacketEncoding.EncodePacket(decodedPacket);
    }
	
	public static byte[] ConstructWriteDateTimePacket(DateTimeData dateTimeData)
    {
        byte[] decodedPacket = new byte[] { (byte)PacketHeaders.WriteDateTime.ordinal(),
                                            (byte)(((((dateTimeData.getYear() - 2000) % 100) / 10) << 4) | ((dateTimeData.getYear() - 2000) % 10)),
                                            (byte)(((((dateTimeData.getMonth()) % 100) / 10) << 4) | (dateTimeData.getMonth() % 10)),
                                            (byte)(((((dateTimeData.getDay()) % 100) / 10) << 4) | (dateTimeData.getDay() % 10)),
                                            (byte)(((((dateTimeData.getHours()) % 100) / 10) << 4) | (dateTimeData.getHours() % 10)),
                                            (byte)(((((dateTimeData.getMinutes()) % 100) / 10) << 4) | (dateTimeData.getMinutes() % 10)),
                                            (byte)(((((dateTimeData.getSeconds()) % 100) / 10) << 4) | (dateTimeData.getSeconds() % 10)),
                                            0};
        decodedPacket = InsertChecksum(decodedPacket);
        return PacketEncoding.EncodePacket(decodedPacket);
    }
	
	public static byte[] ConstructDigitalIOpacket(DigitalIOdata digitalIOdata)
    {
        byte[] decodedPacket = new byte[] { (byte)PacketHeaders.DigitalIOdata.ordinal(),
                                            0x00,
                                            digitalIOdata.getState().ConvertToByte(),
                                            0};
        decodedPacket = InsertChecksum(decodedPacket);
        return PacketEncoding.EncodePacket(decodedPacket);
    }
	
	public static byte[] ConstructPWMoutputPacket(PWMoutputData PWMoutputData)
    {
        byte[] decodedPacket = new byte[] { (byte)PacketHeaders.PWMoutputData.ordinal(),
                                            (byte)(PWMoutputData.getAX0() >> 8),
                                            (byte)PWMoutputData.getAX0(),
                                            (byte)(PWMoutputData.getAX2() >> 8),
                                            (byte)PWMoutputData.getAX2(),
                                            (byte)(PWMoutputData.getAX4() >> 8),
                                            (byte)PWMoutputData.getAX4(),
                                            (byte)(PWMoutputData.getAX6() >> 8),
                                            (byte)PWMoutputData.getAX6(),
                                            0};
        decodedPacket = InsertChecksum(decodedPacket);
        return PacketEncoding.EncodePacket(decodedPacket);
    }
	
	private static byte[] InsertChecksum(byte[] decodedPacket)
    {
        decodedPacket[decodedPacket.length - 1] = 0;                        
        for (int i = 0; i < (decodedPacket.length - 1); i++)
        {
            decodedPacket[decodedPacket.length - 1] += decodedPacket[i];    
        }
        return decodedPacket;
    }
	
	public static xIMUdata DeconstructPacket(byte[] encodedPacket)
			throws Exception {
		
		// Decode packet
		if (encodedPacket.length < 4) {
			throw new Exception("Too few bytes in packet. <4: " + encodedPacket.length);
		}
		if (encodedPacket.length > 30) {
			throw new Exception("Too many bytes in packet. <30");
		}
		byte[] decodedPacket = PacketEncoding.DecodePacket(encodedPacket);

		// Confirm checksum
		byte checksum = 0;
		for (int i = 0; i < (decodedPacket.length - 1); i++)
			checksum += decodedPacket[i];
		if (checksum != decodedPacket[decodedPacket.length - 1]) {
			throw new Exception("Invalid checksum.");
		}

		// Interpret packet according to header
		int header = (int) decodedPacket[0];
		if (header == PacketHeaders.Error.ordinal()) {
			if (decodedPacket.length != 4) {
				throw new Exception(
						"Invalid number of bytes for packet header.");
			}
			return new ErrorData(Concat(decodedPacket[1], decodedPacket[2]));
		} else if (header == PacketHeaders.Command.ordinal()) {
			if (decodedPacket.length != 4) {
				throw new Exception(
						"Invalid number of bytes for packet header.");
			}
			return new CommandData(Concat(decodedPacket[1], decodedPacket[2]));
		} else if (header == PacketHeaders.WriteRegister.ordinal()) {
			if (decodedPacket.length != 6) {
				throw new Exception(
						"Invalid number of bytes for packet header.");
			}
			return new RegisterData(Concat(decodedPacket[1], decodedPacket[2]),
					Concat(decodedPacket[3], decodedPacket[4]));
		} else if (header == PacketHeaders.WriteDateTime.ordinal()) {
			if (decodedPacket.length != 8) {
				throw new Exception(
						"Invalid number of bytes for packet header.");
			}
			return new DateTimeData(
					(int) (10 * ((decodedPacket[1] & 0xF0) >> 4) + (decodedPacket[1] & 0x0F)) + 2000,
					(int) (10 * ((decodedPacket[2] & 0xF0) >> 4) + (decodedPacket[2] & 0x0F)),
					(int) (10 * ((decodedPacket[3] & 0xF0) >> 4) + (decodedPacket[3] & 0x0F)),
					(int) (10 * ((decodedPacket[4] & 0xF0) >> 4) + (decodedPacket[4] & 0x0F)),
					(int) (10 * ((decodedPacket[5] & 0xF0) >> 4) + (decodedPacket[5] & 0x0F)),
					(int) (10 * ((decodedPacket[6] & 0xF0) >> 4) + (decodedPacket[6] & 0x0F)));
		} else if (header == PacketHeaders.RawBatteryAndThermometerData
				.ordinal()) {
			if (decodedPacket.length != 6) {
				throw new Exception(
						"Invalid number of bytes for packet header.");
			}
			return new RawBatteryAndThermometerData(Concat(decodedPacket[1],
					decodedPacket[2]), Concat(decodedPacket[3],
					decodedPacket[4]));
		}

		else if (header == PacketHeaders.CalBatteryAndThermometerData.ordinal()) {
			if (decodedPacket.length != 6) {
				throw new Exception(
						"Invalid number of bytes for packet header.");
			}
			return new CalBatteryAndThermometerData(FixedFloat.FixedToFloat(
					Concat(decodedPacket[1], decodedPacket[2]),
					Qvals.CalibratedBattery), FixedFloat.FixedToFloat(
					Concat(decodedPacket[3], decodedPacket[4]),
					Qvals.CalibratedThermometer));
		} else if (header == PacketHeaders.RawInertialAndMagneticData.ordinal()) {
			if (decodedPacket.length != 20) {
				throw new Exception(
						"Invalid number of bytes for packet header.");
			}
			return new RawInertialAndMagneticData(new short[] {
					Concat(decodedPacket[1], decodedPacket[2]),
					Concat(decodedPacket[3], decodedPacket[4]),
					Concat(decodedPacket[5], decodedPacket[6]) }, new short[] {
					Concat(decodedPacket[7], decodedPacket[8]),
					Concat(decodedPacket[9], decodedPacket[10]),
					Concat(decodedPacket[11], decodedPacket[12]) },
					new short[] { Concat(decodedPacket[13], decodedPacket[14]),
							Concat(decodedPacket[15], decodedPacket[16]),
							Concat(decodedPacket[17], decodedPacket[18]) });
		} else if (header == PacketHeaders.CalInertialAndMagneticData.ordinal()) {
			if (decodedPacket.length != 20) {
				throw new Exception(
						"Invalid number of bytes for packet header.");
			}
			return new CalInertialAndMagneticData(new float[] {
					FixedFloat.FixedToFloat(
							Concat(decodedPacket[1], decodedPacket[2]),
							Qvals.CalibratedGyroscope),
					FixedFloat.FixedToFloat(
							Concat(decodedPacket[3], decodedPacket[4]),
							Qvals.CalibratedGyroscope),
					FixedFloat.FixedToFloat(
							Concat(decodedPacket[5], decodedPacket[6]),
							Qvals.CalibratedGyroscope) }, new float[] {
					FixedFloat.FixedToFloat(
							Concat(decodedPacket[7], decodedPacket[8]),
							Qvals.CalibratedAccelerometer),
					FixedFloat.FixedToFloat(
							Concat(decodedPacket[9], decodedPacket[10]),
							Qvals.CalibratedAccelerometer),
					FixedFloat.FixedToFloat(
							Concat(decodedPacket[11], decodedPacket[12]),
							Qvals.CalibratedAccelerometer) }, new float[] {
					FixedFloat.FixedToFloat(
							Concat(decodedPacket[13], decodedPacket[14]),
							Qvals.CalibratedMagnetometer),
					FixedFloat.FixedToFloat(
							Concat(decodedPacket[15], decodedPacket[16]),
							Qvals.CalibratedMagnetometer),
					FixedFloat.FixedToFloat(
							Concat(decodedPacket[17], decodedPacket[18]),
							Qvals.CalibratedMagnetometer) });
		} else if (header == PacketHeaders.QuaternionData.ordinal()) {
			if (decodedPacket.length != 10) {
				throw new Exception(
						"Invalid number of bytes for packet header.");
			}
			QuaternionData returnQ = new QuaternionData(new float[] {
					FixedFloat.FixedToFloat(
							Concat(decodedPacket[1], decodedPacket[2]),
							Qvals.Quaternion),
					FixedFloat.FixedToFloat(
							Concat(decodedPacket[3], decodedPacket[4]),
							Qvals.Quaternion),
					FixedFloat.FixedToFloat(
							Concat(decodedPacket[5], decodedPacket[6]),
							Qvals.Quaternion),
					FixedFloat.FixedToFloat(
							Concat(decodedPacket[7], decodedPacket[8]),
							Qvals.Quaternion) });
			return returnQ;
		} else if (header == PacketHeaders.DigitalIOdata.ordinal()) {
			if (decodedPacket.length != 4) {
				throw new Exception(
						"Invalid number of bytes for packet header.");
			}
			return new DigitalIOdata(decodedPacket[1], decodedPacket[2]);
		} else if (header == PacketHeaders.RawAnalogueInputData.ordinal()) {
			if (decodedPacket.length != 18) {
				throw new Exception(
						"Invalid number of bytes for packet header.");
			}
			return new RawAnalogueInputData(Concat(decodedPacket[1],
					decodedPacket[2]), Concat(decodedPacket[3],
					decodedPacket[4]), Concat(decodedPacket[5],
					decodedPacket[6]), Concat(decodedPacket[7],
					decodedPacket[8]), Concat(decodedPacket[9],
					decodedPacket[10]), Concat(decodedPacket[11],
					decodedPacket[12]), Concat(decodedPacket[13],
					decodedPacket[14]), Concat(decodedPacket[15],
					decodedPacket[16]));
		} else if (header == PacketHeaders.CalAnalogueInputData.ordinal()) {
			if (decodedPacket.length != 18) {
				throw new Exception(
						"Invalid number of bytes for packet header.");
			}
			return new CalAnalogueInputData(FixedFloat.FixedToFloat(
					Concat(decodedPacket[1], decodedPacket[2]),
					Qvals.CalibratedAnalogueInput), FixedFloat.FixedToFloat(
					Concat(decodedPacket[3], decodedPacket[4]),
					Qvals.CalibratedAnalogueInput), FixedFloat.FixedToFloat(
					Concat(decodedPacket[5], decodedPacket[6]),
					Qvals.CalibratedAnalogueInput), FixedFloat.FixedToFloat(
					Concat(decodedPacket[7], decodedPacket[8]),
					Qvals.CalibratedAnalogueInput), FixedFloat.FixedToFloat(
					Concat(decodedPacket[9], decodedPacket[10]),
					Qvals.CalibratedAnalogueInput), FixedFloat.FixedToFloat(
					Concat(decodedPacket[11], decodedPacket[12]),
					Qvals.CalibratedAnalogueInput), FixedFloat.FixedToFloat(
					Concat(decodedPacket[13], decodedPacket[14]),
					Qvals.CalibratedAnalogueInput), FixedFloat.FixedToFloat(
					Concat(decodedPacket[15], decodedPacket[16]),
					Qvals.CalibratedAnalogueInput));
		} else if (header == PacketHeaders.PWMoutputData.ordinal()) {
			if (decodedPacket.length != 10) {
				throw new Exception(
						"Invalid number of bytes for packet header.");
			}
			return new PWMoutputData(
					Concat(decodedPacket[1], decodedPacket[2]), Concat(
							decodedPacket[3], decodedPacket[4]), Concat(
							decodedPacket[5], decodedPacket[6]), Concat(
							decodedPacket[7], decodedPacket[8]));
		} else if (header == PacketHeaders.RawADXL345busData.ordinal()) {
			if (decodedPacket.length != 26) {
				throw new Exception(
						"Invalid number of bytes for packet header.");
			}
			return new RawADXL345busData(new short[] {
					Concat(decodedPacket[1], decodedPacket[2]),
					Concat(decodedPacket[3], decodedPacket[4]),
					Concat(decodedPacket[5], decodedPacket[6]) }, new short[] {
					Concat(decodedPacket[7], decodedPacket[8]),
					Concat(decodedPacket[9], decodedPacket[10]),
					Concat(decodedPacket[11], decodedPacket[12]) },
					new short[] { Concat(decodedPacket[13], decodedPacket[14]),
							Concat(decodedPacket[15], decodedPacket[16]),
							Concat(decodedPacket[17], decodedPacket[18]) },
					new short[] { Concat(decodedPacket[19], decodedPacket[20]),
							Concat(decodedPacket[21], decodedPacket[22]),
							Concat(decodedPacket[23], decodedPacket[24]) });
		} else if (header == PacketHeaders.CalADXL345busData.ordinal()) {
			if (decodedPacket.length != 26) {
				throw new Exception(
						"Invalid number of bytes for packet header.");
			}
			return new CalADXL345busData(new float[] {
					FixedFloat.FixedToFloat(
							Concat(decodedPacket[1], decodedPacket[2]),
							Qvals.CalibratedADXL345),
					FixedFloat.FixedToFloat(
							Concat(decodedPacket[3], decodedPacket[4]),
							Qvals.CalibratedADXL345),
					FixedFloat.FixedToFloat(
							Concat(decodedPacket[5], decodedPacket[6]),
							Qvals.CalibratedADXL345) }, new float[] {
					FixedFloat.FixedToFloat(
							Concat(decodedPacket[7], decodedPacket[8]),
							Qvals.CalibratedADXL345),
					FixedFloat.FixedToFloat(
							Concat(decodedPacket[9], decodedPacket[10]),
							Qvals.CalibratedADXL345),
					FixedFloat.FixedToFloat(
							Concat(decodedPacket[11], decodedPacket[12]),
							Qvals.CalibratedADXL345) }, new float[] {
					FixedFloat.FixedToFloat(
							Concat(decodedPacket[13], decodedPacket[14]),
							Qvals.CalibratedADXL345),
					FixedFloat.FixedToFloat(
							Concat(decodedPacket[15], decodedPacket[16]),
							Qvals.CalibratedADXL345),
					FixedFloat.FixedToFloat(
							Concat(decodedPacket[17], decodedPacket[18]),
							Qvals.CalibratedADXL345) }, new float[] {
					FixedFloat.FixedToFloat(
							Concat(decodedPacket[19], decodedPacket[20]),
							Qvals.CalibratedADXL345),
					FixedFloat.FixedToFloat(
							Concat(decodedPacket[21], decodedPacket[22]),
							Qvals.CalibratedADXL345),
					FixedFloat.FixedToFloat(
							Concat(decodedPacket[23], decodedPacket[24]),
							Qvals.CalibratedADXL345) });
		} else {
			throw new Exception("Unknown packet header.");
		}
	}

	// / <summary>
	// / Concatenates 2 bytes to return a short.
	// / </summary>
	// / <param name="MSB">
	// / Most Significant Byte.
	// / </param>
	// / <param name="LSB">
	// / Least Significant Byte.
	// / </param>
	// / <returns>
	// / MSB and LSB concatenated to create a short.
	// / </returns>
	private static short Concat(byte MSB, byte LSB) {
		//short returnshort = (short) ((short) ((short) MSB << 8) | (short) LSB);
		ByteBuffer bb = ByteBuffer.allocate(2);
		bb.order(ByteOrder.LITTLE_ENDIAN);
		bb.put(LSB);
		bb.put(MSB);
		short shortVal = bb.getShort(0);
		return shortVal;
	}

}
