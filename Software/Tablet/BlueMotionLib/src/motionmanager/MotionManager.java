package motionmanager;

import ioiolib.ButtonState;
import ioiolib.IOIOConnectionFactoryFromDevice;
import ioiolib.IOIOThread;
import ioiolib.Looper;
import ioiolib.Motor;
import ioiolib.MotorState;
import ioiolib.TouchListener;

import java.math.BigDecimal;

import ximulib.CommandData;
import ximulib.XIMUConnection;
import ximulib.XIMUListener;

import android.bluetooth.BluetoothDevice;
import android.util.Log;

public class MotionManager implements XIMUListener, TouchListener {

	private static final String TAG = "MotionManager: ";
	private BluetoothDevice ioio;
	private BluetoothDevice ximu;

	private MotionThread mt;

	/**
	 * Accuracy of the decimal places which are provided by the xIMU
	 */
	private int fractionalDigits;

	/**
	 * Holds the connections to the bluetooth devices
	 */
	private XIMUConnection xIMUConnection;

	private IOIOConnectionFactoryFromDevice factory;
	private IOIOThread ioioThread;

	/**
	 * Listener for buttons and orientation if the application needs realtime
	 * feedback or the capacitive buttons from the holder
	 */
	private MotionListener listener;

	/** Current pitch and roll of the tablet according from the xIMU */
	private volatile float curYaw;
	private volatile float curRoll;
	private volatile float curPitch;

	/**
	 * Pitch and Roll how it should be (set by user or an application) Used with
	 * the motors to get the tablet in a specific position automatically Not
	 * needed if the tablet is controlled manually over the capacitive buttons
	 */
	private float yaw2be;
	private float roll2be;
	private float pitch2be;

	boolean already = false;

	/**
	 * If only one device is needed use null as parameter for the other device
	 */
	public MotionManager(BluetoothDevice ioio, BluetoothDevice ximu) {
		this.ioio = ioio;
		this.ximu = ximu;
		fractionalDigits = 0;

		mt = new MotionThread(this);

		pitch2be = 0;
		roll2be = 0;
		pitch2be = 0;
	}

	/**
	 * Automatically connects devices and starts new threads for IOIO and xIMU
	 * At this point the listeners are passed so that the framework gets xIMU
	 * data and touch events If both devices are used, a new thread starts which
	 * checks if "cur" and "2be" have the same value. If not motors are started
	 */
	public void connect() {
		if (ioio != null && ioioThread == null) {
			factory = new IOIOConnectionFactoryFromDevice(ioio);
			ioioThread = new IOIOThread(new Looper(), factory, this);
			ioioThread.start();
		}
		if (ximu != null && xIMUConnection == null) {
			xIMUConnection = new XIMUConnection(ximu, this);
			xIMUConnection.start();
		}
	}

	/**
	 * Automatically disconnects devices
	 */
	public void disconnect() {
		if (ioio != null && ioioThread != null) {
			this.deactivateAutomaticOrientation();
			ioioThread.abort();
			ioioThread = null;
		}
		if (ximu != null && xIMUConnection != null) {
			xIMUConnection.cancel();
			xIMUConnection = null;
		}
	}

	/**
	 * Public Yaw functions
	 */
	public float getCurYaw() {
		return curYaw;
	}

	public float getYaw2be() {
		return yaw2be;
	}

	public void setYaw2be(float yaw2be) {
		this.yaw2be = yaw2be;
	}

	/**
	 * Public Roll functions
	 */
	public float getCurRoll() {
		return curRoll;
	}

	public float getRoll2be() {
		return roll2be;
	}

	public void setRoll2be(float roll2be) {
		this.roll2be = roll2be;
	}

	/**
	 * Public Pitch functions
	 */
	public float getCurPitch() {
		return curPitch;
	}

	public float getPitch2be() {
		return pitch2be;
	}

	public void setPitch2be(float pitch2be) {
		this.pitch2be = pitch2be;
	}

	/**
	 * If new data, from the xIMU, is received the local variables musst be
	 * updated. If the application need realtime feedback and a listener is set,
	 * the app becomes notified. At this point the values are rounded to the
	 * given decimal places
	 */
	@Override
	public void newQuaternionDataReceived(float phi, float theta, float psi) {

		float pitch = round(phi);
//		float roll = round(theta);
//		float yaw = round(psi);
		float roll = round(psi);
		float yaw = round(theta);
		
		if(pitch<0){
			pitch+=360;
		}
		if(roll<0){
			roll+=360;
		}
		if(yaw<0){
			yaw+=360;
		}

		setCurRoll(roll);
		setCurPitch(pitch);
		setCurYaw(yaw);
		
		if (listener != null) {
			listener.orientationChanged(roll, pitch, yaw);
		}

	}

	@Override
	public void newCommandDataReceived(CommandData cmd) {
		Log.d(TAG, "CommandData received: " + cmd.getCommandCode());
	}

	/**
	 * Round to certain number of decimals
	 */
	private float round(float d) {
		int decimalPlace = getFractionalDigits();
		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
	}

	/**
	 * Getter and setter for the decimal places, to make the values more user
	 * friendly
	 */
	public int getFractionalDigits() {
		return fractionalDigits;
	}

	public void setFractionalDigits(int fractionalDigits) {
		this.fractionalDigits = fractionalDigits;
	}

	/**
	 * Only XIMUListener can set current orientation
	 */
	private void setCurYaw(float curYaw) {
		this.curYaw = curYaw;
	}

	private void setCurRoll(float curRoll) {
		this.curRoll = curRoll;
	}

	private void setCurPitch(float curPitch) {
		this.curPitch = curPitch;
	}

	/**
	 * If a Button is touched it fires an event
	 */
	@Override
	public void buttonStatusChanged(int id, ButtonState s) {
		if (listener != null) {
			listener.buttonStatusChanged(id, s);
		}

	}

	/**
	 * Controls the motors and throws ConnectionLostException Pass enum Motor
	 * YAW or PITCH for the Motor ID and enum MotorState FORWARD, BACKWARD or
	 * STOP
	 */
	public void rotate(Motor id, MotorState s) {
		if (ioioThread != null) {
			ioioThread.rotate(id, s);
		}
	}

	/**
	 * Use this to set the sensitivity of each capacitive button. Be careful
	 * because there are side effects. If you touch one button the neighboring
	 * buttons are influenced too. Just pass the number of the Button 0-15 an
	 * threshold value. Default value is 100.
	 */
	public void setSensThreshold(int id, int value) {
		ioioThread.setSensThreshold(id, value);
	}

	/**
	 * setting and deleting the listener for the MotionManager
	 */
	public void setListener(MotionListener listener) {
		this.listener = listener;
	}

	public void deleteListener() {
		this.listener = null;
	}

	/**
	 * Clear Tare
	 */
	public void tareXIMU() {
		xIMUConnection.tareXIMU();
	}

	/**
	 * Start Automatic Control
	 */
	public void activateAutomaticOrientation() {
		if (ioio != null && ximu != null && !already) {
			mt.start();
			already = true;
		}
	}
	/**
	 * Abort Automatic Control
	 */
	public void deactivateAutomaticOrientation() {
		if (ioio != null && ximu != null && already) {
			mt.abort();
			already = false;
		}
	}
	/**
	 * Use this method to force the automatic direction of rotation
	 * If you use STOP the rotation of the selected motor is disabled
	 */
	public void setAutomaticRotation(MotorState s, Motor m){
		if(mt != null){
			if(m.equals(Motor.PITCH)){
				mt.setPitchState(s);
				mt.setForcedPitch(true);
			}
			if(m.equals(Motor.ROLL)){
				mt.setRollState(s);
				mt.setForcedRoll(true);
			}
		}
	}	
	/**
	 * Reset to default rotation. The shortest rotation direction
	 */
	public void resetAutomaticRotation(){
		mt.setForcedPitch(false);
		mt.setForcedRoll(false);
	}
}
