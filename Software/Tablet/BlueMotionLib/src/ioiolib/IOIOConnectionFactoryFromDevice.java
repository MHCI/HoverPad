package ioiolib;

import android.bluetooth.BluetoothDevice;
import ioio.lib.android.bluetooth.BluetoothIOIOConnection;
import ioio.lib.api.IOIOConnection;
import ioio.lib.spi.IOIOConnectionFactory;

public class IOIOConnectionFactoryFromDevice implements IOIOConnectionFactory{
	private BluetoothDevice device;
	public IOIOConnectionFactoryFromDevice(BluetoothDevice device){
		this.device = device;
	}
	@Override
	public String getType() {
		return BluetoothIOIOConnection.class
				.getCanonicalName();
	}

	@Override
	public Object getExtra() {
		return new Object[] { device.getName(),
				device.getAddress() };
	}

	@Override
	public IOIOConnection createConnection() {
		return new BluetoothIOIOConnection(device);
	}

}
