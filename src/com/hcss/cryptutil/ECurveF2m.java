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
import java.math.BigInteger;

/**
 * The elliptic curve E(F<sub>2<sup>m</sup></sub>) : y<sup>2</sup> + xy = x<sup>3</sup> + a<sub>4</sub>x<sup>2</sup> + a<sub>6</sub>
 * @author <a href="http://www.dragongate-technologies.com">Dragongate Technologies Ltd.</a>
 * @version 0.90
 */
public class ECurveF2m extends ECurve implements Serializable {

	public ECurveF2m(F2m a4, F2m a6) 
	{
		System.out.println("A4"+a4+"   a6"+a6);
		
		this.a4 = (F2m) a4.clone();
		this.a6 = (F2m) a6.clone();
	}

	public ECPoint add(ECPoint P0, ECPoint P1) {
		BigInteger a, b, lambda, x0, y0, x1, y1, x2, y2;
		a = a4.val;
		b = a6.val;
		x0 = P0.x.val;
		y0 = P0.y.val;
		x1 = P1.x.val;
		y1 = P1.y.val;

		ECPointF2m P2 = new ECPointF2m();

		if (P0.isZero())
			return P1;

		if (P1.isZero())
			return P0;

		if (P0.x.compareTo(P1.x) != 0) {
			lambda =
				F2m.F2x_mul(
					F2m.F2x_add(y0, y1),
					F2m.F2x_inv(F2m.F2x_add(x0, x1)));
			x2 = F2m.F2x_add(a, F2m.F2x_mul(lambda, lambda));
			x2 = F2m.F2x_add(x2, lambda);
			x2 = F2m.F2x_add(x2, x0);
			x2 = F2m.F2x_add(x2, x1);
		} else if (P0.y.compareTo(P1.y) != 0) {
			return P2;
		} else if (P1.x.isZero()) {
			return P2;
		} else {
			lambda = F2m.F2x_add(x1, F2m.F2x_mul(y1, F2m.F2x_inv(x1)));
			x2 = F2m.F2x_add(a, F2m.F2x_mul(lambda, lambda));
			x2 = F2m.F2x_add(x2, lambda);
		}

		y2 = F2m.F2x_mul(F2m.F2x_add(x1, x2), lambda);
		y2 = F2m.F2x_add(y2, x2);
		y2 = F2m.F2x_add(y2, y1);

		P2.x.val = x2;
		P2.y.val = y2;
		return P2;
	}

	/**
	 * Based on Algorithm IV.1 on p. 63 of "Elliptic Curves in Cryptography"
	 * by I. F. Blake, G. Seroussi, N. P. Smart.
	 */
	public ECPoint mul(BigInteger n, ECPoint P) {
		ECPoint Q;
		ECPoint S = new ECPointF2m();
		BigInteger k;

		if (n.compareTo(BigInteger.valueOf(0)) == 0)
			return new ECPointF2m();

		if (n.compareTo(BigInteger.valueOf(0)) < 0) {
			k = n.negate();
			Q = P.negate();
		} else {
			k = n;
			Q = P;
		}

		for (int j = k.bitLength() - 1; j >= 0; j--) {
			S = add(S, S);
			if (k.testBit(j)) {
				S = add(S, Q);
			}
		}

		return S;
	}

	protected Object clone() {
		return new ECurveF2m((F2m) a4, (F2m) a6);
	}

}
