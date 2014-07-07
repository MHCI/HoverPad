package ximulib;

public class FixedFloat {
        /// <summary>
        /// Returns floating-point value from specified 16-bit fixed-point.
        /// </summary>
        /// <param name="fixedValue">
        /// 16-bit fixed-point value.
        /// </param>
        /// <param name="q">
        /// Number of fraction bits. See <see cref="Qvals"/>.
        /// </param>
        /// <returns>
        /// Floating-point number.
        /// </returns>
        public static float FixedToFloat(short fixedValue, Qvals q)
        {
        	float returnfloat = ((float)(fixedValue) / (float)(1 << q.getCode()));
            return returnfloat;
        }

        /// <summary>
        /// Returns 16-bit fixed-point value from specified floating-point value with saturation.
        /// </summary>
        /// <param name="floatValue">
        /// Floating-point representation of 16-bit fixed-point value.
        /// </param>
        /// <param name="q">
        /// Number of fraction bits. See <see cref="Qvals"/>.
        /// </param>
        /// <returns>
        /// 16-bit fixed-point value.
        /// </returns>
        public static short FloatToFixed(float floatValue, Qvals q)
        {
            int temp = (int)((floatValue) * (int)(1 << q.getCode()));
            if (temp > 32767) temp = 32767;
            else if (temp < -32768) temp = -32768;
            return (short)temp;
        }
  
}
