/**
 * jBorZoi - An Elliptic Curve Cryptography Library
 *
 * Copyright (C) 2003 Dragongate Technologies Ltd.
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.  
 */

package com.hcss.cryptutil;

import java.math.BigInteger;
import java.util.Vector;


/**
 * Various internal utility functions.
 * @author <a href="http://www.dragongate-technologies.com">Dragongate Technologies Ltd.</a>
 * @version 0.90
 */
public class Utils {
	public static String intArrayToString(int[] a) {
		String s = new String("(");
		for (int i = 0; i < a.length; i++) {
			s = s + " " + Integer.toHexString(a[i]);
		}
		s = s + ")";
		return s;
	}

	public static byte[] toByteArray(int[] a) {
		byte[] b = new byte[a.length];
		for (int i = 0; i < a.length; i++) {
			b[i] = (byte) a[i];
		}
		return b;
	}

	public static int[] toIntArray(byte[] a) {
		int[] b = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			b[i] = (int) a[i];
			if (b[i] < 0)
				b[i] += 256;
		}
		return b;
	}

	public static int[] revIntArray(int[] a) {
		int[] b = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			b[a.length - 1 - i] = a[i];
		}
		return b;
	}

	public static int[] xor(int[] a, int[] b) {
		int[] c;
		if (a.length >= b.length) {
			c = new int[a.length];
			for (int i = 0; i < b.length; i++) {
				c[i] = a[i] ^ b[i];
			}
			for (int i = b.length; i < c.length; i++) {
				c[i] = a[i];
			}
		} else {
			c = new int[b.length];
			for (int i = 0; i < a.length; i++) {
				c[i] = a[i] ^ b[i];
			}
			for (int i = a.length; i < c.length; i++) {
				c[i] = b[i];
			}
		}
		return c;
	}

	public static boolean compare(int[] a, int[] b) {
		if (a.length != b.length)
			return false;
		for (int i = 0; i < a.length; i++) {
			if (a[i] != b[i])
				return false;
		}
		return true;
	}

	public static int[] concatenate(int[] a, int[] b) {
		int[] c = new int[a.length + b.length];
		for (int i = 0; i < a.length; i++) {
			c[i] = a[i];
		}
		for (int j = 0; j < b.length; j++) {
			c[j + a.length] = b[j];
		}
		return c;
	}

	public static int[] concatenate(int[] a, int[] b, int[] c) {
		int[] d = new int[a.length + b.length + c.length];
		for (int i = 0; i < a.length; i++) {
			d[i] = a[i];
		}
		for (int j = 0; j < b.length; j++) {
			d[j + a.length] = b[j];
		}
		for (int j = 0; j < c.length; j++) {
			d[j + a.length + b.length] = c[j];
		}
		return d;
	}

	public static int[] resize(int[] a, int length) {
		int[] b = new int[length];
		if (length <= a.length) {
			for (int i = 0; i < length; i++) {
				b[i] = a[i];
			}
		} else { // length > a.length
			for (int i = 0; i < a.length; i++) {
				b[i] = a[i];
			}
			for (int j = a.length; j < length; j++) {
				b[j] = 0;
			}
		}
		return b;
	}

	public static Vector OS2V(int[] a) {
		Vector v = new Vector(a.length);
		for (int i = 0; i < a.length; i++) {
			v.insertElementAt(new Integer(a[i]), i);
		}

		return v;
	}

	public static int[] Vector2OS(Vector a) {
		int[] b = new int[a.size()];
		for (int i = 0; i < a.size(); i++) {
			b[i] = ((Integer) a.elementAt(i)).intValue();
		}
		return b;
	}

	public static Vector FE2OSV(Fq FE) {
		BigInteger z = FE.val;

		int size = Fq.getModulus().bitLength() / 8;
		if ((Fq.getModulus().bitLength() % 8) > 0)
			size++;

		Vector o = new Vector();
		for (int i = 0; i < size; i++) {
			o.insertElementAt(new Integer(z.intValue() & 0xff), 0);
			z = z.shiftRight(8);
		}

		return o;
	}

	/**
	 * Convert finite field element x to an octet string using
	 *  the algorithm in the IEEE P1363 standard.
	 */
	public static int[] FE2OSP(Fq FE) {
		BigInteger z = FE.val;

		int size = Fq.getModulus().bitLength() / 8;
		if ((Fq.getModulus().bitLength() % 8) > 0)
			size++;

		int[] o = new int[size];
		for (int i = 0; i < size; i++) {
			o[size - 1 - i] = z.intValue() & 0xff;
			z = z.shiftRight(8);
		}

		return o;
	}

	/**
	 * Convert octet string o to a finite field elemet using
	 *  the algorithm in the IEEE P1363 standard.
	 */
	public static BigInteger OS2FEP(int[] o) {
		BigInteger I = BigInteger.valueOf(0);

		for (int i = 0; i < o.length; i++) {
			I = I.shiftLeft(8);
			I = I.add(BigInteger.valueOf(o[i]));
		}
		return I;
	}

	/**
	 * Convert octet string o to large integer form using the 
	 * algorithm in the IEEE P1363 standard.
	 */
	public static BigInteger OS2IP(int[] o) {
		BigInteger I = BigInteger.valueOf(0);

		for (int i = 0; i < o.length; i++) {
			I = I.shiftLeft(8);
			I = I.add(BigInteger.valueOf(o[o.length - i - 1]));
		}
		return I;
	}

}
