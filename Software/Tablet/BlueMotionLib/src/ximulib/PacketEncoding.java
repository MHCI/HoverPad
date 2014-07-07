package ximulib;

public class PacketEncoding {
    /// <summary>
    /// Encodes packet with consecutive right shifts so that the msb of each encoded byte is clear. The msb of the final byte is set to indicate the end of the packet.
    /// </summary>
    /// <param name="decodedPacket">
    /// The decoded packet contents to be encoded.
    /// </param>
    /// <returns>
    /// The encoded packet.
    /// </returns> 
    public static byte[] EncodePacket(byte[] decodedPacket)
    {
        byte[] encodedPacket = new byte[(int)(Math.ceil((((float)decodedPacket.length * 1.125f)) + 0.125f))];
        byte[] shiftRegister = new byte[encodedPacket.length];
        java.lang.System.arraycopy(decodedPacket,0, shiftRegister,0, decodedPacket.length);     // copy encoded packet to shift register
        for (int i = 0; i < encodedPacket.length; i++)
        {
            shiftRegister = RightShiftByteArray(shiftRegister);             // right shift to clear msb of byte i
            encodedPacket[i] = shiftRegister[i];                            // store encoded byte i
            shiftRegister[i] = 0;                                           // clear byte i in shift register
        }
        encodedPacket[encodedPacket.length - 1] |= 0x80;                    // set msb of framing byte
        return encodedPacket;
    }

    /// <summary>
    /// Right shifts a byte array by 1 bit. The lsb of byte x becomes the msb of byte x+1.
    /// </summary>
    /// <param name="byteArray">
    /// The byte array to be right shifted.
    /// </param>
    /// <returns>
    /// The right shifted byte array.
    /// </returns> 
    private static byte[] RightShiftByteArray(byte[] byteArray)
    {
        byteArray[byteArray.length - 1] >>= 1;
        
        for (int i = byteArray.length - 2; i >= 0; i--)
        {
            if ((byteArray[i] & 0x01) == 0x01){
            	byteArray[i + 1] |= 0x80;
            }
            byteArray[i] >>= 1;
        }
        return byteArray;
    }

	// / <summary>
	// / Decodes a packet with consecutive left shifts so that the msb of
	// each encoded byte is removed.
	// / </summary>
	// / <param name="encodedPacket">
	// / The endcoded packet to be decoded.
	// / </param>
	// / <returns>
	// / The decoded packet.
	// / </returns>
	public static byte[] DecodePacket(byte[] encodedPacket) {
		byte[] decodedPacket = new byte[(int) (Math
				.floor(((float) encodedPacket.length - 0.125f) / 1.125f))];
		byte[] shiftRegister = new byte[encodedPacket.length];
		for (int i = shiftRegister.length - 1; i >= 0; i--) {
			shiftRegister[i] = encodedPacket[i];
			shiftRegister = LeftShiftByteArray(shiftRegister);
		}
		java.lang.System.arraycopy(shiftRegister, 0, decodedPacket, 0,
				decodedPacket.length);
		return decodedPacket;
	}

	// / <summary>
	// / Left shifts a byte array by 1 bit. The msb of byte x becomes the lsb of
	// byte x-1.
	// / </summary>
	// / <param name="byteArray">
	// / The byte array to be left shifted.
	// / </param>
	// / <returns>
	// / The left shifted byte array.
	// / </returns>
	private static byte[] LeftShiftByteArray(byte[] byteArray) {
		byteArray[0] <<= 1;
		for (int i = 1; i < byteArray.length; i++) {
			if ((byteArray[i] & 0x80) == 0x80)
				byteArray[i - 1] |= 0x01;
			byteArray[i] <<= 1;
		}
		return byteArray;
	}
}
