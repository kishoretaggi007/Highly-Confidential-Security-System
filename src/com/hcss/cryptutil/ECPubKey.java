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
 * Elliptic Curve Public keys consisting of two member variables: dp,
 * the EC domain parameters and W, the public key which is a
 * point on the curve.
 * @author <a href="http://www.dragongate-technologies.com">Dragongate Technologies Ltd.</a>
 * @version 0.90
 */
public class ECPubKey implements Serializable
{
	/**
	 * The EC Domain Parameters
	 */
	public ECDomainParameters dp; // the EC domain parameters for this key pair

	/**
	 * The public key
	 */
	public ECPoint W; // the public key

	/**
	 * Generate a public key from private key sk
	 */
	public ECPubKey (ECPrivKey sk)
	{
		dp = (ECDomainParameters)sk.dp.clone();
		W = dp.E.mul (sk.s, dp.G);
	}
	
	/**
	 * Generate a public key with ECDomainParameters dp
	 * and public key W
	 */
	public ECPubKey (ECDomainParameters dp, ECPointF2m W)
	{
		this.dp = (ECDomainParameters)dp.clone();
		this.W = (ECPoint)W.clone();
	}

	public String toString()
	{
		String str = new String("dp: ").concat(dp.toString()).concat("\n");
		str = str.concat("W: x:").concat(W.x.toString()).concat("\n");
		str = str.concat("   y:").concat(W.y.toString()).concat("\n");
		return str;

	}

	protected Object clone()
	{
		return new ECPubKey(dp, (ECPointF2m)W);
	}

}
