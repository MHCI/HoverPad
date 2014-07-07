package ximulib;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.bluetooth.BluetoothSocket;
import android.util.Log;

public class XIMUDataProcessing extends Thread {
	private static final String TAG = "xIMU prcessing data: ";
	private final BluetoothSocket socket;
	private final InputStream mmInStream;
	private final OutputStream mmOutStream;

	private XIMUListener listener;

	byte[] receiveBuffer = new byte[256];
	int receiveBufferIndex = 0;

	public XIMUDataProcessing(BluetoothSocket socket, XIMUListener listener) {
		this.socket = socket;
		this.listener = listener;

		InputStream tmpIn = null;
		OutputStream tmpOut = null;

		try {
			tmpIn = socket.getInputStream();
			tmpOut = socket.getOutputStream();
		} catch (IOException e) {
			Log.e(TAG, "error getting streams", e);
		}

		mmInStream = tmpIn;
		mmOutStream = tmpOut;

	}

	/**
	 * Thread which processes the incomming data to packets and notify Motionlib
	 */
	public void run() {

		int numBytesRead;
		byte[] serialBuffer = new byte[1024];

		/*
		 * Keep listening to the InputStream until an exception occurs
		 */
		while (socket.isConnected()) {
			try {
				numBytesRead = mmInStream.read(serialBuffer);

				/*
				 * Process each byte one by one
				 */
				for (int i = 0; i < numBytesRead; i++) {
					receiveBuffer[receiveBufferIndex++] = serialBuffer[i];

					if ((serialBuffer[i] & 0x80) == 0x80) {
						xIMUdata dataObject = null;

						try {
							byte[] encodedPacket = new byte[receiveBufferIndex];
							java.lang.System.arraycopy(receiveBuffer, 0,
									encodedPacket, 0, encodedPacket.length);
							dataObject = PacketConstruktion
									.DeconstructPacket(encodedPacket);
						} catch (Exception e) {
							e.printStackTrace();
							Log.e(TAG, "exception", e);
						}
						if (dataObject != null) {
							// OnxIMUdataReceived(dataObject);
							if (dataObject instanceof ErrorData) {
								// OnErrorDataReceived((ErrorData)
								// dataObject);
								// PacketsReadCounter.ErrorPackets++;
								Log.e(TAG, "ErrorData received: " + ((ErrorData)dataObject).getErrorCode());
							} else if (dataObject instanceof CommandData) {
								// OnCommandDataReceived((CommandData)
								// dataObject);
								// PacketsReadCounter.CommandPackets++;
								listener.newCommandDataReceived((CommandData)dataObject);
							} else if (dataObject instanceof RegisterData) {
								// OnRegisterDataReceived((RegisterData)
								// dataObject);
								// PacketsReadCounter.WriteRegisterPackets++;
							} else if (dataObject instanceof DateTimeData) {
								// OnDateTimeDataReceived((DateTimeData)
								// dataObject);
								// PacketsReadCounter.WriteDateTimePackets++;
							} else if (dataObject instanceof RawBatteryAndThermometerData) {
								// OnRawBatteryAndThermometerDataReceived((RawBatteryAndThermometerData)
								// dataObject);
								// PacketsReadCounter.RawBatteryAndThermometerDataPackets++;
							} else if (dataObject instanceof CalBatteryAndThermometerData) {
								// OnCalBatteryAndThermometerDataReceived((CalBatteryAndThermometerData)
								// dataObject);
								// PacketsReadCounter.CalBatteryAndThermometerDataPackets++;
							} else if (dataObject instanceof RawInertialAndMagneticData) {
								// OnRawInertialAndMagneticDataReceived((RawInertialAndMagneticData)
								// dataObject);
								// PacketsReadCounter.RawInertialAndMagneticDataPackets++;
							} else if (dataObject instanceof CalInertialAndMagneticData) {
								// OnCalInertialAndMagneticDataReceived((CalInertialAndMagneticData)
								// dataObject);
								// PacketsReadCounter.CalInertialAndMagneticDataPackets++;
								// float[] gyro = ((CalInertialAndMagneticData)
								// dataObject).getGyroscope();
							} else if (dataObject instanceof QuaternionData) {
								// OnQuaternionDataReceived((QuaternionData)dataObject);
								// PacketsReadCounter.QuaternionDataPackets++;
								// log("QuaternionData");
								float[] quanternion = ((QuaternionData) dataObject)
										.ConvertToEulerAngles();
								listener.newQuaternionDataReceived(quanternion[0],
										quanternion[1], quanternion[2]);
							} else if (dataObject instanceof DigitalIOdata) {
								// OnDigitalIODataReceived((DigitalIOdata)
								// dataObject);
								// PacketsReadCounter.DigitalIOdataPackets++;
							} else if (dataObject instanceof RawAnalogueInputData) {
								// OnRawAnalogueInputDataReceived((RawAnalogueInputData)
								// dataObject);
								// PacketsReadCounter.RawInertialAndMagneticDataPackets++;
							} else if (dataObject instanceof CalAnalogueInputData) {
								// OnCalAnalogueInputDataReceived((CalAnalogueInputData)
								// dataObject);
								// PacketsReadCounter.CalAnalogueInputDataPackets++;
							} else if (dataObject instanceof PWMoutputData) {
								// OnPWMoutputDataReceived((PWMoutputData)
								// dataObject);
								// PacketsReadCounter.PWMoutputDataPackets++;
							} else if (dataObject instanceof RawADXL345busData) {
								// OnRawADXL345busDataReceived((RawADXL345busData)
								// dataObject);
								// PacketsReadCounter.RawADXL345busDataPackets++;
							} else if (dataObject instanceof CalADXL345busData) {
								// OnCalADXL345busDataReceived((CalADXL345busData)
								// dataObject);
								// PacketsReadCounter.CalADXL345busDataPackets++;
							}
						} else {
							Log.d(TAG, "nullData");
						}
						receiveBufferIndex = 0;
					}
				}
			} catch (IOException e) {
				Log.e(TAG, "exception occurs", e);
				break;
			}
		}
	}

	/**
	 * Call this to send data to the remote device 
	 */
	public void write(byte[] bytes) {
		try {
			mmOutStream.write(bytes);
		} catch (IOException e) {
			Log.e(TAG, "error sending packet: ", e);
		}
	}

	/**
	 * Call this to shutdown the connection
	 */
	public void cancel() {
		try {
			socket.close();
		} catch (IOException e) {
			Log.e(TAG, "error closing socket: ", e);
		}
		this.listener = null;
	}

	public static float specialswap(float value) {
		int intValue = Float.floatToRawIntBits(value);
		return Integer.reverseBytes(Integer.parseInt(Integer
				.toBinaryString(intValue)));

	}
}
