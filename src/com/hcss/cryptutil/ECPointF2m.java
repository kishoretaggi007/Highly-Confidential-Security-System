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

import java.io.Serializable;

/**
 * An elliptic curve point class representing a point 
 * on the curve as two binary finite field elements x and y.
 * @author <a href="http://www.dragongate-technologies.com">Dragongate Technologies Ltd.</a>
 * @version 0.90
 */
public class ECPointF2m extends ECPoint implements Serializable{
	/**
	 * Construct a new point P = O the point at infinity
	 */
	public ECPointF2m() {
		x = new F2m();
		y = new F2m();
	}

	/**
	 * Construct a new point P = (x, y) on the curve 
	 * @param x : the x coordinate of the point
	 * @param y : the y coordinate of the point
	 */
	public ECPointF2m(F2m x, F2m y) {
		this.x = (F2m) x.clone();
		this.y = (F2m) y.clone();
	}

	/**
	 * Returns the additive inverse of this point (-P).
	 */
	public ECPoint negate() {
		return new ECPointF2m((F2m) x, (F2m) y.add(x));
	}

	protected Object clone() {
		return new ECPointF2m((F2m) x, (F2m) y);
	}
}
