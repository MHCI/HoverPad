package motionmanager;

import ioiolib.ButtonState;

public interface MotionListener {
	public void buttonStatusChanged(int id, ButtonState s);
	public void orientationChanged(float roll, float pitch, float yaw);
}
