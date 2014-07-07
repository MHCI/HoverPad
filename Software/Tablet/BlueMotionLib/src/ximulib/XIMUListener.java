package ximulib;

public interface XIMUListener {
	public void newQuaternionDataReceived(float phi, float theta, float psi);
	public void newCommandDataReceived(CommandData cmd);
}
