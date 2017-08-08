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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Vector;


/**
 * Elliptic Curve Cryptography Functions
 * @author <a href="http://www.dragongate-technologies.com">Dragongate Technologies Ltd.</a>
 * @version 0.90
 */
public class ECC {
	private static byte[] Enc(byte[] U, byte[] KB, int keysize) {
		Rijndael r = new Rijndael();
		r.makeKey(KB, keysize);
		byte c[] = new byte[U.length];
		r.encrypt(U, c);
		return c;
	}

	private static byte[] Dec(byte[] U, byte[] KB, int keysize) {
		Rijndael r = new Rijndael();
		r.makeKey(KB, keysize);
		byte p[] = new byte[U.length];
		r.decrypt(U, p);
		return p;
	}

	/**
	 * The Advanced Encryption Symmetric (AES) decryption algorithm in 
	 * Cipher Block Chaining (CBC) mode with a null initialization 
	 * vector.
	 * <P>
	 * The AES implementation used is the public domain optimised 
	 * Java implementation of the Rijndael (AES) block cipher by 
	 * Paul Barreto.
	 * (see com.dragongate_technologies.borZoi.internal.Rijndael)
	 * <P>
	 * Decrypt an octet string C with key KB of length keysize.
	 * @param KB the key
	 * @param C the ciphertext to be decrypted
	 * @param keysize can be 128, 192 or 256 bits
	 * @return the decrypted plain text
	 */
	public static int[] AES_CBC_IV0_Decrypt(int[] KB, int[] C, int keysize) {
		Vector v1=new Vector();
		if (C.length % 16 != 0) {
			throw (
				new RuntimeException("AES_CBC_IV0_Decrypt: C.length not a multiple of 16"));
		} else if (C.length < 16) {
			throw (new RuntimeException("AES_CBC_IV0_Decrypt: C.length < 16"));
		}
		int k = ((C.length + 1) / 16);

		int T[] = new int[C.length];
		int U[] = new int[16];
		int CI[] = new int[16];
		for (int i = 1; i <= k; i++) {
			for (int n = 0; n < 16; n++) {
				CI[n] = C[(i - 1) * 16 + n];
			}
			U =
				Utils.toIntArray(
					Dec(Utils.toByteArray(CI), Utils.toByteArray(KB), keysize));
			for (int j = 0; j < 16; j++) {
				if (i > 1)
					T[(i - 1) * 16 + j] = U[j] ^ C[(i - 2) * 16 + j];
				else
					T[j] = U[j];
				v1.add(T[j]+"");
				
			}
			System.out.println("t[j] Value"+v1);
		}

		int padLen = T[(k * 16) - 1];
		if (padLen < 1) {
			throw (new RuntimeException("AES_CBC_IV0_Decrypt: padLen < 1"));
		} else if (padLen > 16) {
			throw (
				new RuntimeException(
					"AES_CBC_IV0_Decrypt: padLen(" + padLen + ")>16"));
		}
		for (int l = 1; l < padLen; l++) {
			if (T[(k * 16) - 1 - l] != padLen) {
				throw (
					new RuntimeException("AES_CBC_IV0_Decrypt: OCTET != padLen"));
			}
		}
		int M[] = new int[T.length - padLen];
		for (int m = 0; m < T.length - padLen; m++) {
			M[m] = T[m];
		}
		return M;
	}

	/**
	 * The Advanced Encryption Symmetric (AES) encryption algorithm in 
	 * Cipher Block Chaining (CBC) mode with a null initialization 
	 * vector.
	 * <P>
	 * The AES implementation used is the public domain optimised 
	 * Java implementation of the Rijndael (AES) block cipher by 
	 * Paul Barreto.
	 * (see com.dragongate_technologies.borZoi.internal.Rijndael)
	 * <P>
	 * Encrypt an octet string M with key KB of length keysize.
	 * @param KB the key
	 * @param M the plaintext to be encrypted
	 * @param keysize can be 128, 192 or 256 bits
	 * @return the encrypted cipher text
	 */
	public static int[] AES_CBC_IV0_Encrypt(int[] KB, int[] M, int keysize) {
		int padLen = 16 - (M.length % 16);
		int k = ((M.length + 1) / 16);
		if ((M.length + 1) % 16 != 0)
			k++;

		int P1[] = new int[1];
		P1[0] = padLen;
		int P2[] = new int[padLen];
		for (int l = 0; l < padLen; l++) {
			P2[l] = P1[0];
		}

		int T[] = Utils.concatenate(M, P2);
		int C[] = new int[16];
		int U[] = new int[16];

		for (int i = 1; i <= k; i++) {
			for (int j = 0; j < 16; j++) {
				if (i == 1)
					U[j] = T[(i - 1) * 16 + j];
				else
					U[j] = T[(i - 1) * 16 + j] ^ C[(i - 2) * 16 + j];
			}
			if (i == 1)
				C =
					Utils.toIntArray(
						Enc(
							Utils.toByteArray(U),
							Utils.toByteArray(KB),
							keysize));
			else
				C =
					Utils.concatenate(
						C,
						Utils.toIntArray(
							Enc(
								Utils.toByteArray(U),
								Utils.toByteArray(KB),
								keysize)));
		}
		return C;
	}

	/** Key Derivation Function 2 (KDF2) from the IEEE P1363a 
	  * draft standard.
	  * It generates a secret key of length oLen bits from shared
	  * secret Z and key derivation parameter P.
	  */
	public static int[] KDF2(int[] Z, int oLen, int[] P) {
		// Note:
		// oLen cannot be > hbits * (2^32-1) bits, because the size of an
		// int is 32 bits
		int[] K = new int[0];
		int[] CB = new int[1];
		int cThreshold = (oLen / 160);
		if (oLen % 160 != 0)
			cThreshold++;
		try {
			MessageDigest sha = MessageDigest.getInstance("SHA");

			//sha.update(data);
			//byte[] hash = sha.digest(data);
			for (byte i = 1; i <= cThreshold; i++) {
				CB[0] = i;
				sha.update(Utils.toByteArray(Utils.concatenate(Z, CB, P)));
				K =
					Utils.concatenate(
						K,
						Utils.revIntArray(Utils.toIntArray(sha.digest())));
				//K = Utils.concatenate(K, Utils.toIntArray(sha.digest()));
				sha.reset(); // not needed after diget()
			}
		} catch (NoSuchAlgorithmException e) {
		}
		//return Utils.resize (Utils.revIntArray(K), oLen);
		return Utils.resize(K, oLen);
	}

	/** MAC1 as described in the IEEE P1363 standard.
	  * It computes a HMAC message authentication code tag from 
	  * secret key KB and message M.
	  */
	public static int[] MAC1(int[] K, int[] M) {
		int[] HH = new int[0];
		try {
			int i;
			int[] KK;
			MessageDigest sha = MessageDigest.getInstance("SHA");
			// SHA1 Blocksize B = 512
			if (K.length > (8 * 512)) {
				sha.update(Utils.toByteArray(K));
				// kkLen = 20 octets, 160 bits
				KK = Utils.revIntArray(Utils.toIntArray(sha.digest()));
			} else
				KK = K;
			int[] P = new int[512 - KK.length];
			for (i = 0; i < P.length; i++) {
				P[i] = 0;
			}
			int[] K0 = Utils.concatenate(KK, P);
			int[] iPad = new int[512];
			for (i = 0; i < iPad.length; i++) {
				iPad[i] = 0x36;
			}
			int[] oPad = new int[512];
			for (i = 0; i < oPad.length; i++) {
				oPad[i] = 0x54;
			}
			sha.reset();
			sha.update(
				Utils.toByteArray(Utils.concatenate(Utils.xor(K0, iPad), M)));
			int[] H = Utils.revIntArray(Utils.toIntArray(sha.digest()));
			sha.reset();
			sha.update(
				Utils.toByteArray(Utils.concatenate(Utils.xor(K0, oPad), H)));
			HH = Utils.revIntArray(Utils.toIntArray(sha.digest()));
		} catch (NoSuchAlgorithmException e) {
		}

		return HH;
	}

	/**
	 * ECSVDP_DH
	 * Throws an exception if the point P is zero
	 */
	protected static Fq ECSVDP_DH(
		ECDomainParameters dp,
		BigInteger s,
		ECPoint Wi) {
		ECPoint P = dp.E.mul(s, Wi);
		if (P.isZero())
			throw (new RuntimeException("ECSVDP_DH: P is zero"));
		return P.x;
	}

	/**
	 * The Elliptic Curve Diffe-Hellman Key Agreement Scheme as 
	 * specified in ANSI X9.63 and IEEE P1363
	 * In ECKAS-DH1 (the Elliptic Curve Key Agreement Scheme, 
	 * Diffie-Hellman 1), each party combines its own private key
	 * with the other party?fs public key to calculate a shared 
	 * secret key which can then be used as the key for a 
	 * symmetric encryption algorithm such as AES. Other 
	 * (public or private) information known to both parties 
	 * may be used as key derivation parameters to ensure that 
	 * a dierent secret key is generated every session. This 
	 * key agreement scheme is described in more detail in 
	 * section 9.2 of the IEEEP1363 standard.
	 * This Calculates a 128 bit secret key from EC domain 
	 * parameters dp, private key s, public key Wi and key 
	 * derivation parameter P (an octet string).
	 * s belongs to one party, Wi belongs to the other and dp 
	 * and P are common to both of them.
	 * @param dp The EC domain parameters.
	 * @param s The EC private key.
	 * @param Wi The EC public key.
	 * @param P The key derivation parameter.
	 * @return
	 */
	public static BigInteger ECKAS_DH1(
		ECDomainParameters dp,
		BigInteger s,
		ECPoint Wi,
		int[] P) {
		Fq z = ECSVDP_DH(dp, s, Wi);
		int[] Z = Utils.FE2OSP(z);
		int[] k = KDF2(Z, 16, P); // 128 bits
		BigInteger K = Utils.OS2IP(k);
		return K;
	}
}
