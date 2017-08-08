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
 * This is a mult-precision abstract finite field class (f<sub>q</sub>)
 * @author <a href="http://www.dragongate-technologies.com">Dragongate Technologies Ltd.</a>
 * @version 0.90
 */
public abstract class Fq {
	/**
	 * Finite Field Modulus
	 */
	static protected BigInteger modulus = BigInteger.valueOf(0);

	/**
	 * Return the Finite Field Modulus
	 */
	public static BigInteger getModulus() {
		return modulus;
	}

	/**
	 * Finite Field Element
	 */
	public BigInteger val;

	/**
	 * Finite Field Additive Identity Element
	 */
	static public Fq O;

	/**
	 * Finite Field Multiplicative Identity Element
	 */
	static public Fq I;

	public Fq() {
		this.val = BigInteger.valueOf(0);
	}

	/**
	 * Returns true if this element is equal to 0
	 */
	public boolean isZero() {
		if (val.equals(BigInteger.valueOf(0)))
			return true;
		else
			return false;
	}

	public int compareTo(Fq a) {
		return val.compareTo(a.val);
	}

	public String toString() {
		return val.toString(16);
	}

	public abstract Fq add(Fq b);
	public abstract Fq inverse();
	public abstract Fq mul(Fq b);
	public abstract Fq negative();

}
