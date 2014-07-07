package ximulib;

import java.io.IOException;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

public class XIMUConnection extends Thread{

	private static final String TAG = "xIMU-Connection: ";

	private final static String MY_UUID = "00001101-0000-1000-8000-00805F9B34FB";
	private BluetoothSocket ximuSocket;
	private BluetoothDevice ximuDevice;

	private XIMUDataProcessing ximuData;
	private XIMUListener ximulistener;
	
	/**
	 * Get a BluetoothSocket to connect with the given BluetoothDevice MY_UUID
	 * is the app's UUID string, also used by the server code
	 */
	public XIMUConnection(BluetoothDevice device, XIMUListener listener) {

		BluetoothSocket tmp = null;
		ximuDevice = device;
		ximulistener = listener;
		
		try {
			tmp = ximuDevice.createRfcommSocketToServiceRecord(java.util.UUID
					.fromString(MY_UUID));
		} catch (IOException e) {
			Log.e(TAG, "error creating RFcomm", e);
		}
		ximuSocket = tmp;
	}

	/**
	 * Connect the device through the socket. This will block until it succeeds
	 * or throws an exception If it succeeds, the XIMUDataProcessing is starting
	 * and the Listener is passed through
	 */
	public void run() {
		try {
			ximuSocket.connect();
			Log.d(TAG, "connected to xIMU");
			ximuData = new XIMUDataProcessing(ximuSocket, ximulistener);
			ximuData.start();
		} catch (IOException connectException) {
			Log.e(TAG, "unable to connect", connectException);
			try {
				ximuSocket.close();
			} catch (IOException closeException) {
				Log.e(TAG, "unable to close socket ", closeException);
			}
			return;
		}
	}

	public void cancel() {
		if (ximuData != null) {
			ximuData.cancel();
			ximuData = null;
			Log.d(TAG, "disconnected from xIMU");
		}
	}

	public void tareXIMU() {
		if (ximuData != null) {
			byte[] b = new byte[] { 0, 64, 1, 96, (byte) 248 };
			ximuData.write(b);
			// ximuData.write(PacketConstruktion.ConstructCommandPacket(CommandCodes.AlgorithmClearTare));
		}
	}

}
