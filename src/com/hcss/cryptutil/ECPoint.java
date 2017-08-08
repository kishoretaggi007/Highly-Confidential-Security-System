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

/**
 * An abstract elliptic curve point class representing a
 * point on the curve as two finite field elements x and y.
 * @author <a href="http://www.dragongate-technologies.com">Dragongate Technologies Ltd.</a>
 * @version 0.90
 */
public abstract class ECPoint {
	/**
	 * The x coordinate of the point.
	 */
	public Fq x;

	/**
	 * The x coordinate of the point.
	 */
	public Fq y;

	/**
	 * Returns true if this is the point at infinity, O = (0, 0).
	 */
	public boolean isZero() {
		return (x.isZero() & y.isZero());
	}

	public String toString() {
		return "x:0x" + x + " y:0x" + y;
	}

	/**
	 * Returns the additive inverse of this point (-P).
	 */
	public abstract ECPoint negate();

	protected abstract Object clone();

}
