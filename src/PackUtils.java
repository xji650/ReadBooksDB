/**
 * Utilitats d'empaquetament.
 * <br>
 * Els mètodes pack<em>Tipus</em> empaqueten una dada del tipus corresponent
 * mentre que els mètodes unpack<em>Tipus</em> la desempaqueten i la retornen.
 * <br>
 * Els tipus primitius ocupen un espai fix en el <em>buffer</em> a partir del
 * desplaçament indicat (<em>offset</em>). Les seves mides són:
 *  <b>byte</b>:   1 byte  (8  bits), <b>short</b>:  2 bytes (16 bits),
 *   <b>int</b>:   4 bytes (32 bits),  <b>long</b>:  8 bytes (64 bits),
 *  <b>float</b>:  4 bytes (32 bits), <b>double</b>: 8 bytes (64 bits),
 * <b>boolean</b>: 1 byte  (8  bits),  <b>char</b>:  2 bytes (16 bits).
 * <br>
 * El tipus no primitiu <b>String</b> ocupa 2*<em>maxLength</em> bytes
 * (16*<em>maxLength</em> bits), on <em>maxLength</em> és el nombre
 * màxim de caràcters que l'usuari considera oportú guardar
 * (encara que el <em>String</em> proporcionat n'ocupi menys).
 * <br>
 * Cap d'aquests mètodes comprova els límits de <em>buffer</em> ni modifica
 * <em>offset</em> (donat que aquest es passa per valor).
 * L'usuari d'aquesta classe és qui s'ha d'assegurar que el registre
 * tingui mida suficient i qui ha d'anar modificant el desplaçament.
 */

public class PackUtils {

	// Tipus de nombres enters:

	// byte

	/**
	 * Empaqueta un byte.
	 * @param b El byte
	 * @param buffer Registre on es guardarà el byte
	 * @param offset Desplaçament dintre del registre
	 */
	public static void packByte (byte b,
	                             byte[] buffer, int offset) {
		buffer[offset] = b;
	}

	/**
	 * Desempaqueta un byte.
	 * @param buffer Registre del qual es recuperarà el byte
	 * @param offset Desplaçament dintre del registre
	 * @return El byte
	 */
	public static byte unpackByte (byte[] buffer,
	                               int offset) {
		return buffer[offset];
	}

	// short

	/**
	 * Empaqueta un short.
	 * @param s El short
	 * @param buffer Registre on es guardarà el short
	 * @param offset Desplaçament dintre del registre
	 */
	public static void packShort (short s,
	                              byte[] buffer, int offset) {
		buffer[offset    ] = (byte) (s >> 8);
		buffer[offset + 1] = (byte)  s      ;
	}

	/**
	 * Desempaqueta un short.
	 * @param buffer Registre del qual es recuperarà el short
	 * @param offset Desplaçament dintre del registre
	 * @return El short
	 */
	public static short unpackShort (byte[] buffer,
	                                 int offset) {
		return (short)(
		       ((buffer[offset    ]       ) << 8) |
		       ((buffer[offset + 1] & 0xFF)     ));
	}

	// int

	/**
	 * Empaqueta un int.
	 * @param i L'int
	 * @param buffer Registre on es guardarà l'int
	 * @param offset Desplaçament dintre del registre
	 */
	public static void packInt (int i,
	                            byte[] buffer, int offset) {
		buffer[offset    ] = (byte) (i >> 24);
		buffer[offset + 1] = (byte) (i >> 16);
		buffer[offset + 2] = (byte) (i >>  8);
		buffer[offset + 3] = (byte)  i       ;
	}

	/**
	 * Desempaqueta un int.
	 * @param buffer Registre del qual es recuperarà l'int
	 * @param offset Desplaçament dintre del registre
	 * @return L'int
	 */
	public static int unpackInt (byte[] buffer,
	                             int offset) {
		return ((buffer[offset    ]       ) << 24) |
		       ((buffer[offset + 1] & 0xFF) << 16) |
		       ((buffer[offset + 2] & 0xFF) <<  8) |
		       ((buffer[offset + 3] & 0xFF)      ) ;
	}

	// long

	/**
	 * Empaqueta un long.
	 * @param l El long
	 * @param buffer Registre on es guardarà el long
	 * @param offset Desplaçament dintre del registre
	 */
	public static void packLong (long l,
	                             byte[] buffer, int offset)  {
		buffer[offset    ] = (byte) (l >> 56);
		buffer[offset + 1] = (byte) (l >> 48);
		buffer[offset + 2] = (byte) (l >> 40);
		buffer[offset + 3] = (byte) (l >> 32);
		buffer[offset + 4] = (byte) (l >> 24);
		buffer[offset + 5] = (byte) (l >> 16);
		buffer[offset + 6] = (byte) (l >>  8);
		buffer[offset + 7] = (byte)  l       ;
	}

	/**
	 * Desempaqueta un long.
	 * @param buffer Registre del qual es recuperarà el long
	 * @param offset Desplaçament dintre del registre
	 * @return El long
	 */
	public static long unpackLong (byte[] buffer,
	                               int offset) {
		return ((long)(buffer[offset    ]       ) << 56) |
		       ((long)(buffer[offset + 1] & 0xFF) << 48) |
		       ((long)(buffer[offset + 2] & 0xFF) << 40) |
		       ((long)(buffer[offset + 3] & 0xFF) << 32) |
		       ((long)(buffer[offset + 4] & 0xFF) << 24) |
		       ((long)(buffer[offset + 5] & 0xFF) << 16) |
		       ((long)(buffer[offset + 6] & 0xFF) <<  8) |
		       ((long)(buffer[offset + 7] & 0xFF)      ) ;
	}


	// Tipus de nombres reals:

	// float

	/**
	 * Empaqueta un float.
	 * @param f El float
	 * @param buffer Registre on es guardarà el float
	 * @param offset Desplaçament dintre del registre
	 */
	public static void packFloat (float f,
	                              byte[] buffer, int offset) {
		int bits = Float.floatToRawIntBits (f);
		packInt (bits, buffer, offset);
	}

	/**
	 * Desempaqueta un float.
	 * @param buffer Registre del qual es recuperarà el float
	 * @param offset Desplaçament dintre del registre
	 * @return El float
	 */
	public static float unpackFloat (byte[] buffer,
	                                 int offset) {
		int bits = unpackInt (buffer, offset);
		return Float.intBitsToFloat (bits);
	}

	// double

	/**
	 * Empaqueta un double.
	 * @param d El double
	 * @param buffer Registre on es guardarà el double
	 * @param offset Desplaçament dintre del registre
	 */
	public static void packDouble (double d,
	                               byte[] buffer, int offset) {
		long bits = Double.doubleToRawLongBits (d);
		packLong (bits, buffer, offset);
	}

	/**
	 * Desempaqueta un double.
	 * @param buffer Registre del qual es recuperarà el double
	 * @param offset Desplaçament dintre del registre
	 * @return El double
	 */
	public static double unpackDouble (byte[] buffer,
	                                   int offset) {
		long bits = unpackLong (buffer, offset);
		return Double.longBitsToDouble (bits);
	}


	// Altres tipus primitius:

	// boolean

	/**
	 * Empaqueta un boolean.
	 * @param b El boolean
	 * @param buffer Registre on es guardarà el boolean
	 * @param offset Desplaçament dintre del registre
	 */
	public static void packBoolean (boolean b,
	                                byte[] buffer, int offset) {
		buffer[offset] = b? (byte)1 : (byte)0;
	}

	/**
	 * Desempaqueta un boolean.
	 * @param buffer Registre del qual es recuperarà el boolean
	 * @param offset Desplaçament dintre del registre
	 * @return El boolean
	 */
	public static boolean unpackBoolean (byte[] buffer,
	                                     int offset) {
		return buffer[offset] != 0;
	}

	// char

	/**
	 * Empaqueta un char.
	 * @param c El char
	 * @param buffer Registre on es guardarà el char
	 * @param offset Desplaçament dintre del registre
	 */
	public static void packChar (char c,
	                             byte[] buffer, int offset) {
		buffer[offset    ] = (byte) (c >> 8);
		buffer[offset + 1] = (byte)  c;
	}

	/**
	 * Desempaqueta un char.
	 * @param buffer Registre del qual es recuperarà el char
	 * @param offset Desplaçament dintre del registre
	 * @return El char
	 */
	public static char unpackChar (byte[] buffer,
	                               int offset) {
		return (char) ((buffer[offset    ] << 8) |
		               (buffer[offset + 1] & 0xFF));
	}


	// String:

	/**
	 * Empaqueta un String.
	 * @param s El String
	 * @param maxLength Longitud màxima del String
	 * @param buffer Registre on es guardarà el String
	 * @param offset Desplaçament dintre del registre
	 */
	public static void packString (String s, int maxLength,
	                               byte[] buffer, int offset) {
		for (int i = 0; i < maxLength; i++) {
			if (i < s.length()) {
				packChar (s.charAt (i), buffer, offset + 2 * i);
			} else {
				// Omple amb zeros
				packChar ('\0', buffer, offset + 2 * i);
			}
		}
	}

	/**
	 * Desempaqueta un String.
	 * @param maxLength Longitud màxima del String
	 * @param buffer Registre del qual es recuperarà el String
	 * @param offset Desplaçament dintre del registre
	 * @return El String
	 */
	public static String unpackString (int maxLength,
	                                   byte[] buffer, int offset) {
		String s = "";
		for (int i = 0; i < maxLength; i++) {
			char c = unpackChar (buffer, offset + 2 * i);
			if (c != '\0') {
				s += c;
			} else {
				break;
			}
		}
		return s;
	}

}
