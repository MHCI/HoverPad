package motionmanager;

import ioiolib.Motor;
import ioiolib.MotorState;

public class MotionThread extends Thread {

	private MotionManager mm;
	boolean alive = true;
	private float delta;

	private MotorState pitchState;
	private MotorState rollState;
	private boolean forcedPitch;
	private boolean forcedRoll;

	float A, B, C, D;

	public void setPitchState(MotorState pitchState) {
		this.pitchState = pitchState;
	}

	public void setRollState(MotorState rollState) {
		this.rollState = rollState;
	}

	public MotionThread(MotionManager mm) {
		this.mm = mm;
		delta = 20;
		forcedPitch = false;
		forcedRoll = false;
		pitchState = MotorState.FORWARD;
		rollState = MotorState.FORWARD;
	}

	public void run() {
		while (alive) {

			A = (mm.getRoll2be() - delta + 360) % 360;
			B = (mm.getRoll2be() + delta + 360) % 360;
			
			C = (mm.getPitch2be() - delta + 360) % 360;
			D = (mm.getPitch2be() + delta + 360) % 360;

			//PTICH
			if (!forcedPitch) {
				if (mm.getRoll2be() != 0) {
					if (mm.getCurRoll() - mm.getRoll2be() > 0) {
						pitchState = MotorState.FORWARD;
					} else {
						pitchState = MotorState.BACKWARD;
					}
				}
				else{
					if (Math.abs(mm.getCurRoll() - mm.getRoll2be()) >= 180) {
						pitchState = MotorState.FORWARD;
					} else {
						pitchState = MotorState.BACKWARD;
					}
				}
			}
			
			if (A < B) {
				if (!(mm.getCurRoll() >= A && mm.getCurRoll() <= B)) {
					mm.rotate(Motor.PITCH, pitchState);
				} else {
					mm.rotate(Motor.PITCH, MotorState.STOP);
				}
			} else {
				if (mm.getCurRoll() >= B && mm.getCurRoll() <= A) {
					mm.rotate(Motor.PITCH, pitchState);
				} else {
					mm.rotate(Motor.PITCH, MotorState.STOP);
				}
			}
			
			//ROLL
			if (!forcedRoll) {
				if (mm.getPitch2be() != 0) {
					if (mm.getCurPitch() - mm.getPitch2be() > 0) {
						rollState = MotorState.FORWARD;
					} else {
						rollState = MotorState.BACKWARD;
					}
				}
				else{
					if (Math.abs(mm.getCurPitch() - mm.getPitch2be()) >= 180) {
						rollState = MotorState.FORWARD;
					} else {
						rollState = MotorState.BACKWARD;
					}
				}
			}
			
			if (C < D) {
				if (!(mm.getCurPitch() >= C && mm.getCurPitch() <= D)) {
					mm.rotate(Motor.ROLL, rollState);
				} else {
					mm.rotate(Motor.ROLL, MotorState.STOP);
				}
			} else {
				if (mm.getCurPitch() >= D && mm.getCurPitch() <= C) {
					mm.rotate(Motor.ROLL, rollState);
				} else {
					mm.rotate(Motor.ROLL, MotorState.STOP);
				}
			}

		}
	}

	public void setForcedPitch(boolean forcedPitch) {
		this.forcedPitch = forcedPitch;
	}

	public void setForcedRoll(boolean forcedRoll) {
		this.forcedRoll = forcedRoll;
	}

	public float getDelta() {
		return delta;
	}

	public void setDelta(float delta) {
		this.delta = delta;
	}

	public void abort() {
		alive = false;
	}

}
