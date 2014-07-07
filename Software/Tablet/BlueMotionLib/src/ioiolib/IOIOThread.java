package ioiolib;

import android.util.Log;
import ioio.lib.api.IOIO;
import ioio.lib.api.IOIOFactory;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.api.exception.IncompatibilityException;
import ioio.lib.spi.IOIOConnectionFactory;
import ioio.lib.util.IOIOConnectionManager;
import ioio.lib.util.IOIOLooper;

/**
 * A thread, dedicated for communication with a single physical IOIO device.
 */
public class IOIOThread extends IOIOConnectionManager.Thread {
	private static final String TAG = "ioio-thread: ";

	protected IOIO ioio_;
	private boolean abort_ = false;
	private boolean connected_ = false;
	private final IOIOLooper looper_;
	private final IOIOConnectionFactory connectionFactory_;
	private TouchListener listener_;

	public IOIOThread(IOIOLooper looper, IOIOConnectionFactory factory,
			TouchListener listener) {
		looper_ = looper;
		connectionFactory_ = factory;
		listener_ = listener;
	}

	@Override
	public final void run() {
		super.run();
		while (!abort_) {
			try {
				synchronized (this) {
					if (abort_) {
						break;
					}
					ioio_ = IOIOFactory.create(connectionFactory_
							.createConnection());
				}
			} catch (Exception e) {
				Log.e(TAG, "Failed to create IOIO, aborting IOIOThread!");
				return;
			}
			/* if we got here, we have a ioio_! */
			try {
				ioio_.waitForConnect();
				connected_ = true;
				looper_.setup(ioio_);
				((Looper) looper_).setListener(listener_);
				while (!abort_ && ioio_.getState() == IOIO.State.CONNECTED) {
					looper_.loop();
				}
			} catch (ConnectionLostException e) {
			} catch (InterruptedException e) {
				ioio_.disconnect();
			} catch (IncompatibilityException e) {
				Log.e(TAG, "Incompatible IOIO firmware", e);
				looper_.incompatible();
				/*
				 *  nothing to do - just wait until physical disconnection
				 */
			} catch (Exception e) {
				Log.e(TAG, "Unexpected exception caught", e);
				ioio_.disconnect();
				break;
			} finally {
				try {
					ioio_.waitForDisconnect();
				} catch (InterruptedException e1) {
				}
				synchronized (this) {
					ioio_ = null;
				}
				if (connected_) {
					looper_.disconnected();
					connected_ = false;
				}
			}
		}
		Log.d(TAG, "IOIOThread is exiting");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ioio.lib.util.IOIOConnectionThread#abort()
	 */
	@Override
	public synchronized final void abort() {
		abort_ = true;
		if (ioio_ != null) {
			ioio_.disconnect();
		}
		if (connected_) {
			interrupt();
		}
	}

	/**
	 * Values are passed to the loop instance
	 */
	public void rotate(Motor id, MotorState s) {
		try {
			((Looper) looper_).rotate(id, s);
		} catch (Exception e) {

		}
	}
	public void setSensThreshold(int id, int value) {
		((Looper) looper_).setSensThreshold(id, value);	
	}
}
