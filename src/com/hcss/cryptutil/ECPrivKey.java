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
import java.security.SecureRandom;

/**
 * Elliptic Curve Private Keys consisting of two member variables: dp,
 * the EC domain parameters and s, the private key which must
 * be kept secret.
 * @author <a href="http://www.dragongate-technologies.com">Dragongate Technologies Ltd.</a>
 * @version 0.90
 */
public class ECPrivKey implements Serializable{
	/**
	 * The EC Domain Parameters
	 */
	public ECDomainParameters dp;

	/**
	 * The Private Key
	 */
	public BigInteger s;

	/**
	 * Generate a random private key with ECDomainParameters dp
	 */
	public ECPrivKey(ECDomainParameters dp)
	{
		
		this.dp = (ECDomainParameters) dp.clone();
		SecureRandom rnd = new SecureRandom();
		s = new BigInteger(dp.m, rnd);
		s = s.mod(dp.r);
	}

	/**
	 * Generate a private key with ECDomainParameters dp
	 * and private key s
	 */
	public ECPrivKey(ECDomainParameters dp, BigInteger s) 
	{
		this.dp = dp;
		this.s = s;
	}

	public String toString() {
		String str = new String("dp: ").concat(dp.toString()).concat("\n");
		str = str.concat("s: ").concat(s.toString()).concat("\n");
		return str;
	}

	protected Object clone() {
		return new ECPrivKey(dp, s);
	}

}
