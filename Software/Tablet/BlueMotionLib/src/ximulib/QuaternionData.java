package ximulib;

public class QuaternionData extends xIMUdata {
	private float[] quaternion;

	public float[] getquaternion() {
		return quaternion;
	}

	public void setquaternion(float[] quaternion) throws Exception {
		if (quaternion.length != 4) {
			throw new Exception("quaternion vector must be of 4 elements.");
		}
		float norm = (float) Math.sqrt(quaternion[0] * quaternion[0]
				+ quaternion[1] * quaternion[1] + quaternion[2] * quaternion[2]
				+ quaternion[3] * quaternion[3]);
		this.quaternion = quaternion;
		this.quaternion[0] /= norm;
		this.quaternion[1] /= norm;
		this.quaternion[2] /= norm;
		this.quaternion[3] /= norm;
	}

	public QuaternionData() {
		this.quaternion = new float[] {1,0,0,0};
	}

	public QuaternionData(float[] quaternion) {
		this.quaternion = quaternion;
	}

	public QuaternionData ConvertToConjugate() {
		return new QuaternionData(new float[] { quaternion[0], -quaternion[1], -quaternion[2], -quaternion[3] });
	}
	
	public float[] ConvertToRotationMatrix()
    {
        float R11 = 2 * quaternion[0] * quaternion[0] - 1 + 2 * quaternion[1] * quaternion[1];
        float R12 = 2 * (quaternion[1] * quaternion[2] + quaternion[0] * quaternion[3]);
        float R13 = 2 * (quaternion[1] * quaternion[3] - quaternion[0] * quaternion[2]);
        float R21 = 2 * (quaternion[1] * quaternion[2] - quaternion[0] * quaternion[3]);
        float R22 = 2 * quaternion[0] * quaternion[0] - 1 + 2 * quaternion[2] * quaternion[2];
        float R23 = 2 * (quaternion[2] * quaternion[3] + quaternion[0] * quaternion[1]);
        float R31 = 2 * (quaternion[1] * quaternion[3] + quaternion[0] * quaternion[2]);
        float R32 = 2 * (quaternion[2] * quaternion[3] - quaternion[0] * quaternion[1]);
        float R33 = 2 * quaternion[0] * quaternion[0] - 1 + 2 * quaternion[3] * quaternion[3];
        return new float[] { R11, R12, R13,
                             R21, R22, R23,
                             R31, R32, R33 };
    }
	
	 public float[] ConvertToEulerAngles()
     {
         float phi = (float)Math.atan2(2 * (quaternion[2] * quaternion[3] - quaternion[0] * quaternion[1]), 2 * quaternion[0] * quaternion[0] - 1 + 2 * quaternion[3] * quaternion[3]);
         float theta = (float)-Math.atan((2.0 * (quaternion[1] * quaternion[3] + quaternion[0] * quaternion[2])) / Math.sqrt(1.0 - Math.pow((2.0 * quaternion[1] * quaternion[3] + 2.0 * quaternion[0] * quaternion[2]), 2.0)));
         float psi = (float)Math.atan2(2 * (quaternion[1] * quaternion[2] - quaternion[0] * quaternion[3]), 2 * quaternion[0] * quaternion[0] - 1 + 2 * quaternion[1] * quaternion[1]);
         return new float[] { Rad2Deg(phi), Rad2Deg(theta), Rad2Deg(psi) };
     }
	 
	 private float Rad2Deg(float radians)
     {
         return  57.2957795130823f * radians;
     } 
}
