package devicemanager;

import java.util.Set;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

public class DeviceManager {

	private BluetoothAdapter adapter;

	//Default Bluetooth adapter form Device
	public DeviceManager() throws Exception {
		adapter = BluetoothAdapter.getDefaultAdapter();
		checkBluetooth();
	}

	//Throws Exceptions if there no Bluetooth available or the Bluetoothmodul is disabled
	private void checkBluetooth() throws Exception {
		if (adapter != null) {
			if (!adapter.isEnabled()) {
				throw new Exception("Turn Bluetooth on.");
			}
		} else {
			throw new Exception("This Device does not support Bluetooth.");
		}
	}

	//Returns the first Bluetoothdevice with the contained passed String
	public BluetoothDevice getDevicebyName(String name) {
		Set<BluetoothDevice> pairedDevices = adapter.getBondedDevices();
		if (pairedDevices.size() > 0) {
			for (BluetoothDevice foundDevice : pairedDevices) {
				if (foundDevice.getName().contains(name)) {
					return foundDevice;
				}
			}
		}
		return null;
	}
}
