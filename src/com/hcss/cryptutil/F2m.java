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

/**
 * This is a mult-precision binary finite field class (F<sub>2<sup>m</sup></sub>).
 * Finite field elements in a polynomial basis of the form:
 * b<sub>m-1</sub>x<sup>m-1</sup>+b<sub>m-2</sub>x<sup>m-2</sup>+...+b<sub>0</sub>,
 * where all operations are modulo an irreducible polynomial 
 * a<sub>m</sub>x<sup>m</sup>+a<sub>m-1</sub>x<sup>m-1</sup>+...+a<sub>0</sub>.
 * @author <a href="http://www.dragongate-technologies.com">Dragongate Technologies Ltd.</a>
 * @version 0.90
 */
public class F2m extends Fq {
	private static BigInteger F2x_mod(BigInteger a)
		throws ArithmeticException {
		BigInteger r = BigInteger.valueOf(0);

		if (modulus.compareTo(BigInteger.valueOf(0)) == 0)
			throw new ArithmeticException("F2m.F2x_mod: modulus is zero");
		if (a.compareTo(modulus) < 0)
			return a;
		else if (a.compareTo(modulus) == 0)
			return r;
		r = a;
		while (r.bitLength() >= modulus.bitLength()) {
			r = r.xor(modulus.shiftLeft(r.bitLength() - modulus.bitLength()));
		}
		return r;
	}

	protected static BigInteger F2x_add(BigInteger a, BigInteger b) {
		return a.xor(b);
	}

	/**
	 * Algorithm 2 in "Software Implementation of Elliptic Curve Cryptography
	 * Over Binary Fields", D. Hankerson, J.L. Hernandez, A. Menezes.
	 */
	protected static BigInteger F2x_mul(BigInteger a, BigInteger b) {
		BigInteger c = BigInteger.valueOf(0);
		for (int j = 0; j < a.bitLength(); j++) {
			if (a.testBit(j)) {
				c = c.xor(b);
			}
			b = b.shiftLeft(1);
		}

		return F2x_mod(c);
	}

	/**
	 * Algorithm 8 in "Software Implementation of Elliptic Curve Cryptography
	 * Over Binary Fields", D. Hankerson, J.L. Hernandez, A. Menezes.
	 */
	protected static BigInteger F2x_inv(BigInteger a) {
		BigInteger b = BigInteger.valueOf(1);
		BigInteger c = BigInteger.valueOf(0);
		BigInteger u = F2x_mod(a);
		BigInteger v = modulus;
		int j;
		BigInteger buf;
		while (u.bitLength() > 1) {
			j = u.bitLength() - v.bitLength();
			if (j < 0) {
				buf = u;
				u = v;
				v = buf;
				buf = c;
				c = b;
				b = buf;
				j = -j;
			}
			u = F2x_add(u, v.shiftLeft(j));
			b = F2x_add(b, c.shiftLeft(j));
		}

		return b;
	}

	/**
	 * Set the modulus as a irreducible trinomial of the form
	 * x<sup>k3</sup>+x<sup>k2</sup>+x<sup>k1</sup>
	 */
	public static void setModulus(int k3, int k2, int k1) {
		modulus = BigInteger.valueOf(0);
		modulus = modulus.setBit(k3);
		modulus = modulus.setBit(k2);
		modulus = modulus.setBit(k1);
	}

	/**
	 * Set the modulus as a irreducible pentanomial of the form
	 * x<sup>k5</sup>+x<sup>k4</sup>+x<sup>k3</sup>+x<sup>k2</sup>+x<sup>k1</sup>
	 */
	public static void setModulus(int k5, int k4, int k3, int k2, int k1) {
		modulus = BigInteger.valueOf(0);
		modulus = modulus.setBit(k5);
		modulus = modulus.setBit(k4);
		modulus = modulus.setBit(k3);
		modulus = modulus.setBit(k2);
		modulus = modulus.setBit(k1);
	}

	static {
		O = new F2m();
		I = new F2m();
		I.val = BigInteger.valueOf(1);
	}

	public F2m() {
		super();
	}

	/**
	 * Construct a finite field element = val (mod modulus).
	 */
	public F2m(BigInteger val) {
		this.val = F2x_mod(val);
	}

	public F2m(String strVal, int radix) {
		val = F2x_mod(new BigInteger(strVal, radix));
	}

	/**
	 * Compute c = a + b (mod modulus).
	 */
	public Fq add(Fq b) {
		return new F2m(F2x_mod(F2x_add(val, b.val)));
	}

	/**
	 * Compute b = a<sup>-1</sup> (mod modulus).
	 * <P>
	 * Algorithm 8 in "Software Implementation of Elliptic Curve Cryptography
	 * Over Binary Fields", D. Hankerson, J.L. Hernandez, A. Menezes.
	 */
	public Fq inverse() {
		BigInteger b = BigInteger.valueOf(1);
		BigInteger c = BigInteger.valueOf(0);
		BigInteger u = val;
		BigInteger v = modulus;
		int j;
		BigInteger buf;
		while (u.bitLength() != 0) {
			j = u.bitLength() - v.bitLength();
			if (j < 0) {
				buf = u;
				u = v;
				v = buf;
				buf = c;
				c = b;
				b = buf;
				j = -j;
			}
			u = F2x_add(u, v.shiftLeft(j));
			b = F2x_add(b, c.shiftLeft(j));
		}

		return new F2m(F2x_mod(c));
	}

	/**
	 * Compute c = a * b (mod modulus).
	 */
	public Fq mul(Fq b) {
		return new F2m(F2x_mod(F2x_mul(val, b.val)));
	}

	/**
	 * Compute b = -a (mod modulus).
	 */
	public Fq negative() {
		return new F2m(val);
	}

	protected Object clone() {
		return new F2m(val);
	}
}
