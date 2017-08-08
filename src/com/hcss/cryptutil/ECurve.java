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
 * The elliptic curve E(F<sub>q</sub>) : y<sup>2</sup> + xy = x<sup>3</sup> + a<sub>4</sub>x<sup>2</sup> + a<sub>6</sub>
 * @author <a href="http://www.dragongate-technologies.com">Dragongate Technologies Ltd.</a>
 * @version 0.90
 */
public abstract class ECurve {
	protected Fq a4;
	protected Fq a6;

	public String toString() {
		return "a4:0x" + a4 + "\na6:0x" + a6;
	}

	public abstract ECPoint add(ECPoint P0, ECPoint P1);
	public abstract ECPoint mul(BigInteger n, ECPoint P);

}
