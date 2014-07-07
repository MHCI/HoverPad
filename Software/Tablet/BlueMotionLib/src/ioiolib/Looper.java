package ioiolib;

import ioio.lib.api.CapSense;
import ioio.lib.api.DigitalOutput;
import ioio.lib.api.IOIO;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.IOIOLooper;

/**
 * A handler implementing interaction with a single IOIO over a single
 * connection period. The interface utilizes a basic workflow for working with a
 * IOIO instance: as soon as a connection is established, {@link #setup(IOIO)}
 * will be called. Then, the {@link #loop()} method will be called repeatedly as
 * long as the connection is alive. Last, the {@link #disconnected()} method
 * will be called upon losing the connection (as result of physical
 * disconnection, closing the application, etc). In case a IOIO with an
 * incompatible firmware is encountered, {@link #incompatible()} will be called
 * instead of {@link #setup(IOIO)}, and the IOIO instance is entirely useless,
 * until eventually {@link #disconnected()} gets called.
 * 
 */
public class Looper implements IOIOLooper {

	//private static final String TAG = "ioio-loop: ";

	private TouchListener listener = null;

	private int[] sensThreshold = new int[16];
	private boolean[] sense = new boolean[16];
	private CapSense[] capSense = new CapSense[16];

	private DigitalOutput motor19;
	private DigitalOutput motor20;

	private DigitalOutput motor21;
	private DigitalOutput motor22;

	/**
	 * The loop checks the buttons if they are touched or not. It fires an ENTER
	 * or LEAVE event with the button id of the concerned buttons.
	 */
	@Override
	public void loop() throws ConnectionLostException, InterruptedException {

		if (listener != null) {
			for (int i = 0; i < 16; i++) {
				if (capSense[i].read() > sensThreshold[i] && sense[i] == false) {
					listener.buttonStatusChanged(i, ButtonState.ENTER);
					sense[i] = true;
				} else if (capSense[i].read() <= sensThreshold[i]
						&& sense[i] == true) {
					listener.buttonStatusChanged(i, ButtonState.LEAVE);
					sense[i] = false;
				}
			}
		}

	}

	/**
	 * On disconnect all motors are stopped and a last touch LEAVE event is
	 * fired for every button. Then the listener is removed.
	 */
	@Override
	public void disconnected() {
//		try {
//			rotate(Motor.PITCH, MotorState.STOP);
//			rotate(Motor.ROLL, MotorState.STOP);
//		} catch (Exception e) {
//			Log.e(TAG,"lost connection befor stopping motors",e);
//		}
		if (listener != null) {
			for (int i = 0; i < 16; i++) {
				listener.buttonStatusChanged(i, ButtonState.LEAVE);
				sense[i] = false;
			}
			removeListener();
		}
	}

	@Override
	public void incompatible() {

	}

	/**
	 * Pins on which the motors and capacitive buttons are mounted. This
	 * function is called every time when the connection to the IOIO-board is
	 * established. It the default threshold for the capacitive buttons are set.
	 * 
	 * PLEAS DO NOT CHANGE THE VALUES!
	 */
	@Override
	public void setup(IOIO ioio_) throws ConnectionLostException,
			InterruptedException {

		ioio_.openDigitalOutput(0, true);

		motor19 = ioio_.openDigitalOutput(19);
		motor20 = ioio_.openDigitalOutput(20);
		motor21 = ioio_.openDigitalOutput(21);
		motor22 = ioio_.openDigitalOutput(22);

		int port = 31;

		for (int i = 0; i < 16; i++) {
			sensThreshold[i] = 100;
			sense[i] = false;

			capSense[i] = ioio_.openCapSense(port);
			port++;

			// TODO was hei§t das? Nachschauen
			capSense[i].setFilterCoef(500);
		}
	}

	/**
	 * Use this to set the sensitivity of each capacitive button. Be careful
	 * because there are side effects. If you touch one button the neighboring
	 * buttons are influenced too. Just pass the number of the Button 0-15 an
	 * threshold value. Default value is 100.
	 */
	public void setSensThreshold(int id, int value) {
		if (id >= 1 && id <= 16) {
			id -= 1;
			sensThreshold[id] = value;
		}
	}

	/**
	 * Controls the motors and throws ConnectionLostException Pass enum Motor
	 * YAW or PITCH for the Motor ID and enum MotorState FORWARD, BACKWARD or
	 * STOP
	 */
	public void rotate(Motor id, MotorState s) throws ConnectionLostException {
		if (id == Motor.ROLL) {
			if (s == MotorState.FORWARD) {
				motor19.write(true);
				motor20.write(false);
			} else if (s == MotorState.BACKWARD) {
				motor19.write(false);
				motor20.write(true);
			} else {
				motor19.write(false);
				motor20.write(false);
			}
		} else if (id == Motor.PITCH) {
			if (s == MotorState.FORWARD) {
				motor21.write(true);
				motor22.write(false);
			} else if (s == MotorState.BACKWARD) {
				motor21.write(false);
				motor22.write(true);
			} else {
				motor21.write(false);
				motor22.write(false);
			}
		}
	}

	/**
	 * Getter and setter only for the Motion Framework
	 */
	public void setListener(TouchListener listener) {
		this.listener = listener;
	}

	public void removeListener() {
		this.listener = null;
	}

}
