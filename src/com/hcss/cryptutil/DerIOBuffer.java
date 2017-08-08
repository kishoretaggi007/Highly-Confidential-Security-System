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

import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.util.Vector;


//ansi-X9-62 OBJECT IDENTIFIER ::= { iso(1) member-body(2) us(840) 10045 }

/**
 * DER Encoding/Decoding.
 * <P>
 * DER (Distinguished Encoding Rules for ASN.1) is a standard
 * way of encoding data for transmission over networks or
 * communicating with other programs. The objects in this library
 * are encoded according to the ASN.1 syntax in the ANSI X9.62,
 * ANSI X9.62 and SEC 1 Elliptic Curve Cryptography standards.
 * @author <a href="http://www.dragongate-technologies.com">Dragongate Technologies Ltd.</a>
 * @version 0.90
 */
public class DerIOBuffer {
	/**
	 * DER data
	 */
	public int[] data;

	protected static Vector DER_Encode(long z) {
		Vector der_rep = new Vector();
		while (z != 0) {
			der_rep.insertElementAt(new Integer((int) (z & 0xff)), 0);
			z /= 0x100;
		}
		if ((((Integer) der_rep.elementAt(0)).intValue() & 0x80) != 0)
			der_rep.insertElementAt(new Integer(0x00), 0);
		DerInsertLength(der_rep, 0x02);
		return der_rep;
	}

	protected static Vector DER_Encode(BigInteger z) {
		Vector der_rep = new Vector();
		while (!z.equals(BigInteger.valueOf(0))) {
			der_rep.insertElementAt(new Integer(z.intValue() & 0xff), 0);
			z = z.shiftRight(8);
		}
		if ((((Integer) der_rep.elementAt(0)).intValue() & 0x80) != 0)
			der_rep.insertElementAt(new Integer((int) 0x00), 0);
		DerInsertLength(der_rep, 0x02);
		return der_rep;
	}
	protected static BigInteger DER2BigInt(int[] der_rep) throws Exception {
		int length;
		if (der_rep[0] != 0x02)
			throw (new Exception("Not an Integer"));
		else {
			Vector v = Utils.OS2V(der_rep);
			length = DerExtractLength(v);
			der_rep = Utils.Vector2OS(v);
		}
		BigInteger I = BigInteger.valueOf(0);
		for (int i = 0; i < der_rep.length; i++) {
			I = I.shiftLeft(8);
			I = I.add(BigInteger.valueOf(der_rep[i]));
		}
		return I;
	}

	protected static Vector DER_Encode(F2m f) {
		Vector der_rep = Utils.FE2OSV(f);
		DerInsertLength(der_rep, 0x04);
		return der_rep;
	}

	protected static F2m DER2F2M(int[] der_rep) throws Exception {
		int length;
		if (der_rep[0] != 0x04)
			throw (new Exception("Not an Octet String"));
		else {
			Vector v = Utils.OS2V(der_rep);
			length = DerExtractLength(v);
			der_rep = Utils.Vector2OS(v);
		}
		F2m f = new F2m(Utils.OS2FEP(der_rep));
		return f;
	}

	protected static Vector DER_Encode(ECPointF2m P) {
		int[] PC = new int[1];
		PC[0] = 0x04;
		int[] b = Utils.concatenate(PC, Utils.FE2OSP(P.x), Utils.FE2OSP(P.y));
		Vector der_rep = new Vector();
		for (int i = 0; i < b.length; i++) {
			der_rep.insertElementAt(new Integer(b[i]), i);
		}
		DerInsertLength(der_rep, 0x04);
		return der_rep;
	}

	protected static ECPointF2m DER2Point(int[] der_rep) throws Exception {
		int length;
		if (der_rep[0] != 0x04)
			throw (new Exception("Not an Octet String"));
		else {
			Vector v = Utils.OS2V(der_rep);
			length = DerExtractLength(v);
			der_rep = Utils.Vector2OS(v);
		}

		BigInteger x = BigInteger.valueOf(0);
		for (int i = 1; i <= length / 2; i++) {
			x = x.shiftLeft(8);
			x = x.add(BigInteger.valueOf(der_rep[i]));
		}

		BigInteger y = BigInteger.valueOf(0);
		for (int i = length / 2 + 1; i < length; i++) {
			y = y.shiftLeft(8);
			y = y.add(BigInteger.valueOf(der_rep[i]));
		}

		ECPointF2m p = new ECPointF2m(new F2m(x), new F2m(y));

		return p;
	}

	protected static int[] DER_Encode(ECurveF2m C) {
		int[] der_rep =
			Utils.concatenate(
				Utils.Vector2OS(DER_Encode((F2m) C.a4)),
				Utils.Vector2OS(DER_Encode((F2m) C.a6)));
		return DerSeqEncode(der_rep);
	}

	protected static ECurveF2m DER2Curve(int[] der_rep) throws Exception {
		if (der_rep[0] != 0x30)
			throw (new Exception("Not a Sequence"));
		Vector v = DerSeqDecode(der_rep);
		return new ECurveF2m(
			DER2F2M(Utils.Vector2OS((Vector) v.elementAt(0))),
			DER2F2M(Utils.Vector2OS((Vector) v.elementAt(1))));
	}

	protected static Vector DER_Encode(int i[]) {
		Vector der_rep = Utils.OS2V(i);
		DerInsertLength(der_rep, 0x04);
		return der_rep;
	}

	protected static Vector DER2OCTETSTR(Vector der_rep) throws Exception {
		int length;
		if (((Integer) der_rep.elementAt(0)).intValue() != 0x04)
			throw (new Exception("Not an Octet String"));
		else
			length = DerExtractLength(der_rep);
		return der_rep;
	}

	protected static int[] DER_Encode(ECDomainParameters dp) {
		Vector v = new Vector();
		v.insertElementAt(DER_Encode(1), v.size()); // version
		Vector m = DER_Encode(dp.m); // m
		Vector basis = new Vector(11);
		Vector param = new Vector();
		//if ((dp.basis < 1) || (dp.basis > 3))
		//	throw (
		//		new Exception("DER_Encode (EC_Domain_Parameters dp): Invalid Basis"));
		switch (dp.basis) {
			case 1 : // Gaussian Basis
				// 06 Length (40*01+02+840+10045+01+02+03+01=)
				basis.insertElementAt(new Integer(0x06), 0);
				basis.insertElementAt(new Integer(0x09), 1);
				basis.insertElementAt(new Integer(0x2A), 2);
				basis.insertElementAt(new Integer(0x86), 3);
				basis.insertElementAt(new Integer(0x48), 4);
				basis.insertElementAt(new Integer(0xCE), 5);
				basis.insertElementAt(new Integer(0x3D), 6);
				basis.insertElementAt(new Integer(0x01), 7);
				basis.insertElementAt(new Integer(0x02), 8);
				basis.insertElementAt(new Integer(0x03), 9);
				basis.insertElementAt(new Integer(0x01), 10);
				param = new Vector(2);
				param.insertElementAt(new Integer(0x05), 0);
				param.insertElementAt(new Integer(0x00), 1);
				break;
			case 2 : // Trinomial
				// 06 Length (40*01+02+840+10045+01+02+03+02)
				basis.insertElementAt(new Integer(0x06), 0);
				basis.insertElementAt(new Integer(0x09), 1);
				basis.insertElementAt(new Integer(0x2A), 2);
				basis.insertElementAt(new Integer(0x86), 3);
				basis.insertElementAt(new Integer(0x48), 4);
				basis.insertElementAt(new Integer(0xCE), 5);
				basis.insertElementAt(new Integer(0x3D), 6);
				basis.insertElementAt(new Integer(0x01), 7);
				basis.insertElementAt(new Integer(0x02), 8);
				basis.insertElementAt(new Integer(0x03), 9);
				basis.insertElementAt(new Integer(0x02), 10);
				param = DER_Encode(dp.trinomial_k);
				break;
			case 3 : // Pentanomial Basis
				// 06 Length (40*01+02+840+10045+01+02+03+03)
				basis.insertElementAt(new Integer(0x06), 0);
				basis.insertElementAt(new Integer(0x09), 1);
				basis.insertElementAt(new Integer(0x2A), 2);
				basis.insertElementAt(new Integer(0x86), 3);
				basis.insertElementAt(new Integer(0x48), 4);
				basis.insertElementAt(new Integer(0xCE), 5);
				basis.insertElementAt(new Integer(0x3D), 6);
				basis.insertElementAt(new Integer(0x01), 7);
				basis.insertElementAt(new Integer(0x02), 8);
				basis.insertElementAt(new Integer(0x03), 9);
				basis.insertElementAt(new Integer(0x03), 10);
				Vector p = new Vector();
				p.insertElementAt(DER_Encode(dp.pentanomial_k1), p.size());
				p.insertElementAt(DER_Encode(dp.pentanomial_k2), p.size());
				p.insertElementAt(DER_Encode(dp.pentanomial_k3), p.size());
				param = Utils.OS2V(DerSeqEncode(p));
				break;
		}

		Vector s = new Vector();
		s.insertElementAt(m, s.size());
		s.insertElementAt(basis, s.size());
		s.insertElementAt(param, s.size());
		v.insertElementAt(Utils.OS2V(DerSeqEncode(s)), v.size()); // FieldID
		v.insertElementAt(
			Utils.OS2V(DER_Encode(new ECurveF2m((F2m) dp.E.a4, (F2m) dp.E.a6))),
			v.size());
		// curve
		v.insertElementAt(DER_Encode((ECPointF2m) dp.G), v.size()); // base
		v.insertElementAt(DER_Encode(dp.r), v.size()); // order
		v.insertElementAt(DER_Encode(dp.k), v.size()); // cofactor
		int[] der_rep = DerSeqEncode(v);
		return der_rep;
	}

	protected static ECDomainParameters DER2ECDP(int[] der_rep)
		throws Exception {
		if (der_rep[0] != 0x30)
			throw (new Exception("DER2ECDP: Not a Sequence"));
		Vector v = DerSeqDecode(der_rep);
		if (DER2BigInt(Utils.Vector2OS((Vector) v.elementAt(0))).intValue()
			!= 0x01)
			throw (new Exception("DER2ECDP: Unsupported Version"));
		Vector FieldID = DerSeqDecode(Utils.Vector2OS((Vector) v.elementAt(1)));

		int m =
			DER2BigInt(Utils.Vector2OS((Vector) FieldID.elementAt(0)))
				.intValue();

		byte basis =
			((Integer) (((Vector) FieldID.elementAt(1)).elementAt(10)))
				.byteValue();

		int trinomial = 0;
		int pentanomial_k1 = 0;
		int pentanomial_k2 = 0;
		int pentanomial_k3 = 0;
		Vector param;
		if ((basis < 1) || (basis > 3))
			throw (new Exception("DER2ECDP: Invalid Basis"));

		switch (basis) {
			case 1 : // Gaussian Basis
				throw (new Exception("DER2ECDP: Gaussian Basis Not Supported"));
			case 2 : // Trinomial
				trinomial =
					DER2BigInt(Utils.Vector2OS((Vector) FieldID.elementAt(2)))
						.intValue();
				F2m.setModulus(m, trinomial, 0);
				break;
			case 3 : // Pentanomial Basis
				param =
					DerSeqDecode(
						Utils.Vector2OS((Vector) FieldID.elementAt(2)));
				pentanomial_k1 =
					DER2BigInt(Utils.Vector2OS((Vector) param.elementAt(0)))
						.intValue();
				pentanomial_k2 =
					DER2BigInt(Utils.Vector2OS((Vector) param.elementAt(1)))
						.intValue();
				pentanomial_k3 =
					DER2BigInt(Utils.Vector2OS((Vector) param.elementAt(2)))
						.intValue();
				F2m.setModulus(
					m,
					pentanomial_k3,
					pentanomial_k2,
					pentanomial_k1,
					0);
				break;
			default : // Error
				break;
		}

		ECurveF2m C = DER2Curve(Utils.Vector2OS((Vector) v.elementAt(2)));
		ECPointF2m G = DER2Point(Utils.Vector2OS((Vector) v.elementAt(3)));
		BigInteger r = DER2BigInt(Utils.Vector2OS((Vector) v.elementAt(4)));
		BigInteger k = DER2BigInt(Utils.Vector2OS((Vector) v.elementAt(5)));
		ECDomainParameters dp;

		if (basis == 2)
			dp = new ECDomainParameters(m, trinomial, C, r, G, k);
		else
			dp =
				new ECDomainParameters(
					m,
					pentanomial_k3,
					pentanomial_k2,
					pentanomial_k1,
					C,
					r,
					G,
					k);
		if (!dp.isValid()) {
			throw (new Exception("DER2ECDP: Invalid Domain Parameters"));
		}
		return dp;
	}

	protected static Vector DerSeqDecode(int[] in) throws Exception {
		int length;
		if (in[0] != 0x30)
			throw (new Exception("Not a Sequence"));
		else {
			Vector v = Utils.OS2V(in);
			length = DerExtractLength(v);
			in = Utils.Vector2OS(v);
		}
		Vector out = new Vector();

		int i, k;
		i = 0;
		Vector vec;
		vec = new Vector();
		for (int j = 0; j < length; j++) {
			if ((in[j + 1] & 0x80) != 0) { // Long
				int l = 0;
				vec.insertElementAt(new Integer(in[j]), vec.size());
				vec.insertElementAt(new Integer(in[j + 1]), vec.size());
				for (k = 0; k < (in[j + 1] & 0x7f); k++) {
					l *= 0x100;
					l += in[j + 2 + k];
					vec.insertElementAt(new Integer(in[j + 2 + k]), vec.size());
				}
				for (k = 0; k < l; k++) {
					vec.insertElementAt(
						new Integer(in[j + 2 + (in[j + 1] & 0x7f) + k]),
						vec.size());
				}
				out.insertElementAt(vec, out.size());
				j += (1 + in[j + 1] & 0x7f) + l;
			} else { // Short
				for (k = j; k <= j + 1 + in[j + 1]; k++) {
					vec.insertElementAt(new Integer(in[k]), vec.size());
				}
				out.insertElementAt(vec, out.size());
				j += 1 + in[j + 1];
			}
			vec = new Vector();
		}
		return out;
	}

	protected static int[] DerSeqEncode(int[] der_rep) {
		Vector v = Utils.OS2V(der_rep);
		DerInsertLength(v, 0x30);
		int[] b = Utils.Vector2OS(v);
		return b;
	}

	protected static int[] DerSeqEncode(Vector in) {
		int[] seq_rep = Utils.Vector2OS((Vector) in.elementAt(0));

		for (int i = 1; i < in.size(); i++) {
			seq_rep =
				Utils.concatenate(
					seq_rep,
					Utils.Vector2OS((Vector) in.elementAt(i)));
		}
		Vector v = Utils.OS2V(seq_rep);
		DerInsertLength(v, 0x30);
		seq_rep = Utils.Vector2OS(v);
		return seq_rep;
	}

	protected static int DerExtractLength(Vector data) {

		int length = 0;
		data.removeElementAt(0); // erase DER type OCTET
		if ((((Integer) data.elementAt(0)).intValue() & 0x80) != 0) { // Long
			for (int j = 1;
				j <= (((Integer) data.elementAt(0)).intValue() & 0x7f);
				j++) {
				length *= 0x100;
				length += ((Integer) data.elementAt(j)).intValue();
			}
			int k = ((Integer) data.elementAt(0)).intValue() & 0x0f;
			for (int l = 0; l < k + 1; l++) {
				data.removeElementAt(0);
			}
		} else { // Short
			length = ((Integer) data.elementAt(0)).intValue();
			data.removeElementAt(0);
		}
		if (length != data.size())
			length = 0;

		return length;
	}

	protected static void DerInsertLength(Vector data, int DerTypeOctet) {
		if (data.size() > 127) { // long form
			long length = data.size();
			int no_octets = 0x80;
			while (length != 0) {
				data.insertElementAt(new Integer((int) (length % 256)), 0);
				length /= 256;
				no_octets++;
			}
			data.insertElementAt(new Integer((int) no_octets), 0);
		} else { // short form
			data.insertElementAt(new Integer((int) (data.size())), 0);
		}
		data.insertElementAt(new Integer((int) DerTypeOctet), 0);
	}

	/**
	 * Constructor
	 */
	/*public DerIOBuffer (int[] data)
	{
		this.data = new int[data.length];
		for (int i=0; i<data.length; i++) {
			this.data[i] = data[i];
		}
	}*/

	/**
	 * Constructor
	 */
	public DerIOBuffer(byte[] data) {
		this.data = Utils.toIntArray(data);
	}

	protected static int[] DER_Encode(ECPubKey pk) {
		Vector algorithmidentifier = new Vector();
		// 06 Length (40*01+02+840+10045+02+01=)
		Vector a = new Vector();
		Vector algorithm = new Vector(9);
		algorithm.insertElementAt(new Integer(0x06), 0);
		algorithm.insertElementAt(new Integer(0x07), 1);
		algorithm.insertElementAt(new Integer(0x2a), 2);
		algorithm.insertElementAt(new Integer(0x86), 3);
		algorithm.insertElementAt(new Integer(0x48), 4);
		algorithm.insertElementAt(new Integer(0xce), 5);
		algorithm.insertElementAt(new Integer(0x3d), 6);
		algorithm.insertElementAt(new Integer(0x02), 7);
		algorithm.insertElementAt(new Integer(0x01), 8);
		a.insertElementAt(algorithm, a.size()); // version

		// EC domain parameters
		a.insertElementAt(Utils.OS2V(DER_Encode(pk.dp)), a.size());
		algorithmidentifier = Utils.OS2V(DerSeqEncode(a));

		Vector buf = new Vector();
		buf.insertElementAt(algorithmidentifier, buf.size());
		Vector ecpoint = DER_Encode((ECPointF2m) pk.W); // public key

		ecpoint.insertElementAt(new Integer(0x00), 0);

		if (ecpoint.size() > 127) {
			int size = ecpoint.size();
			int no_octets = 0;
			while (size > 0) {
				ecpoint.insertElementAt(new Integer(size % 0x100), 0);
				size /= 0x100;
				no_octets++;
			}
			no_octets |= 0x80;
			ecpoint.insertElementAt(new Integer(no_octets), 0);
		} else {
			ecpoint.insertElementAt(new Integer(ecpoint.size()), 0);

		}
		ecpoint.insertElementAt(new Integer(0x03), 0);
		buf.insertElementAt(ecpoint, buf.size());

		int[] der_rep = DerSeqEncode(buf);
		return der_rep;
	}

	protected static ECPubKey DER2ECPubKey(int[] der_rep) throws Exception {
		if (der_rep[0] != 0x30)
			throw (new Exception("DER2ECPrivKey: Not a Sequence"));

		Vector buf = DerSeqDecode(der_rep);
		Vector a = DerSeqDecode(Utils.Vector2OS((Vector) buf.elementAt(0)));

		// buf[0]

		// 06 Length (40*01+02+840+10045+02+01=)
		// a[0]
		// algorithm[0] = 0x06; algorithm[1] = 0x07; algorithm[2] = 0x2A;
		// algorithm[3] = 0x86; algorithm[4] = 0x48; algorithm[5] = 0xCE;
		// algorithm[6] = 0x3D; algorithm[7] = 0x02; algorithm[8] = 0x01;
		int algorithm =
			((Integer) (((Vector) a.elementAt(0)).elementAt(8))).intValue();
		if (algorithm != 0x01)
			throw (new Exception("DER2ECPubKey: Invalid Algorithm"));

		// a[1]
		ECDomainParameters dp =
			DER2ECDP(Utils.Vector2OS((Vector) a.elementAt(1)));

		// buf[1]
		if (((Integer) ((Vector) buf.elementAt(1)).elementAt(0)).intValue()
			!= 0x03)
			throw (new Exception("DER2ECPubKey: ECPOINT Invalid"));
		else {
			// Use this to delete the length OCTETs
			int length = DerExtractLength((Vector) buf.elementAt(1));
			// delete 0x00 OCTET
			 ((Vector) buf.elementAt(1)).removeElementAt(0);
		}

		ECPointF2m W = DER2Point(Utils.Vector2OS((Vector) buf.elementAt(1)));

		ECPubKey pk = new ECPubKey(dp, W);
		return pk;
	}

	protected static int[] DER_Encode(ECPrivKey sk) {
		Vector v = new Vector();
		v.insertElementAt(DER_Encode(1), v.size()); // version
		v.insertElementAt(DER_Encode(sk.s), v.size()); // private key
		v.insertElementAt(Utils.OS2V(DER_Encode(sk.dp)), v.size());
		// EC domain parameters

		int[] der_rep = DerSeqEncode(v);
		return der_rep;
	}

	protected static ECPrivKey DER2ECPrivKey(int[] der_rep) throws Exception {
		if (der_rep[0] != 0x30)
			throw (new Exception("DER2ECPrivKey: Not a Sequence"));
		Vector v = DerSeqDecode(der_rep);
		if (DER2BigInt(Utils.Vector2OS((Vector) v.elementAt(0))).intValue()
			!= 0x01)
			throw (new Exception("DER2ECPrivKey: Unsupported Version"));
		BigInteger s = DER2BigInt(Utils.Vector2OS((Vector) v.elementAt(1)));
		ECDomainParameters dp =
			DER2ECDP(Utils.Vector2OS((Vector) v.elementAt(2)));

		ECPrivKey sk = new ECPrivKey(dp, s);
		return sk;
	}

	protected static int[] DER_Encode(ECDSA sig) {
		Vector AlgorithmIdentifier = new Vector();
		// 06 Length (40*01+02+840+10045+02+01=)
		Vector alg = new Vector(9);
		alg.insertElementAt(new Integer(0x06), 0);
		alg.insertElementAt(new Integer(0x07), 1);
		alg.insertElementAt(new Integer(0x2a), 2);
		alg.insertElementAt(new Integer(0x86), 3);
		alg.insertElementAt(new Integer(0x48), 4);
		alg.insertElementAt(new Integer(0xce), 5);
		alg.insertElementAt(new Integer(0x3d), 6);
		alg.insertElementAt(new Integer(0x02), 7);
		alg.insertElementAt(new Integer(0x01), 8);
		Vector algid_buf = new Vector();
		algid_buf.insertElementAt(alg, algid_buf.size()); // algorithm
		AlgorithmIdentifier = Utils.OS2V(DerSeqEncode(algid_buf));

		Vector ECDSA_Sig_Value = new Vector();
		Vector sigval_buf = new Vector();
		sigval_buf.insertElementAt(DER_Encode(sig.c), sigval_buf.size());
		// r in X9.62
		sigval_buf.insertElementAt(DER_Encode(sig.d), sigval_buf.size());
		// s in X9.62
		ECDSA_Sig_Value = Utils.OS2V(DerSeqEncode(sigval_buf));

		Vector buf = new Vector();
		buf.insertElementAt(AlgorithmIdentifier, buf.size());
		buf.insertElementAt(ECDSA_Sig_Value, buf.size());
		int[] der_rep = DerSeqEncode(buf);
		return der_rep;
	}

	protected static ECDSA DER2ECSignature(int[] der_rep) throws Exception {
		if (der_rep[0] != 0x30)
			throw (new Exception("DER2ECSignature: Not a Sequence"));

		Vector buf = DerSeqDecode(der_rep);
		Vector a = DerSeqDecode(Utils.Vector2OS((Vector) buf.elementAt(0)));

		// buf[0]

		// 06 Length (40*01+02+840+10045+02+01=)
		// a[0]
		// algorithm[0] = 0x06; algorithm[1] = 0x07; algorithm[2] = 0x2A;
		// algorithm[3] = 0x86; algorithm[4] = 0x48; algorithm[5] = 0xCE;
		// algorithm[6] = 0x3D; algorithm[7] = 0x02; algorithm[8] = 0x01;
		int algorithm =
			((Integer) (((Vector) a.elementAt(0)).elementAt(8))).intValue();
		if (algorithm != 0x01)
			throw (new Exception("DER2ECSignature: Invalid Algorithm"));

		Vector b = DerSeqDecode(Utils.Vector2OS((Vector) buf.elementAt(1)));
		// c is r in X9.62, d is s in X9.62
		BigInteger c = DER2BigInt(Utils.Vector2OS((Vector) b.elementAt(0)));
		BigInteger d = DER2BigInt(Utils.Vector2OS((Vector) b.elementAt(1)));
		return new ECDSA(c, d);
	}

	protected static int[] DER_Encode(ECIES ct) {
		Vector buf = new Vector();
		buf.insertElementAt(Utils.OS2V(DER_Encode(ct.V)), buf.size());
		buf.insertElementAt((DER_Encode(ct.C)), buf.size());
		buf.insertElementAt((DER_Encode(ct.T)), buf.size());
		int[] der_rep = DerSeqEncode(buf);
		return der_rep;
	}

	protected static ECIES DER2ECIES(int[] der_rep) throws Exception {
		if (der_rep[0] != 0x30)
			throw (new Exception("DER2ECIES: Not a Sequence"));

		Vector buf = DerSeqDecode(der_rep);
		ECPubKey V = DER2ECPubKey(Utils.Vector2OS((Vector) buf.elementAt(0)));
		int C[] = Utils.Vector2OS(DER2OCTETSTR((Vector) buf.elementAt(1)));
		int T[] = Utils.Vector2OS(DER2OCTETSTR((Vector) buf.elementAt(2)));
		return new ECIES(V, C, T);
	}

	/**
	 * Constructor
	 */
	public DerIOBuffer(ECDomainParameters dp) {
		data = DER_Encode(dp);
	}

	public ECDomainParameters toECDP() throws Exception {
		return DER2ECDP(data);
	}

	/**
	 * Constructor
	 */

	public DerIOBuffer()
	{

	}

	public DerIOBuffer(ECPubKey pubKey) {
		data = DER_Encode(pubKey);
	}

	public ECPubKey toECPubKey() throws Exception {
		return DER2ECPubKey(data);
	}

	/**
	 * Constructor
	 */
	public DerIOBuffer(ECPrivKey privKey) {
		data = DER_Encode(privKey);
	}

	public ECPrivKey toECPrivKey() throws Exception {
		return DER2ECPrivKey(data);
	}

	/**
	 * Constructor
	 */
	public DerIOBuffer(ECDSA sig) {
		data = DER_Encode(sig);
	}

	public ECDSA toECSignature() throws Exception {
		return DER2ECSignature(data);
	}

	/**
	 * Constructor
	 */
	public int[] DerIOBuffer(ECIES ct) {
		data = DER_Encode(ct);
		System.out.println("Calling Encryption....");
		//Decryption dec=new Decryption(this);
		System.out.println("Calling Decryption....");
		/*try
		{
		//Decryption dec=new Decryption(crypt,skB);
		Socket so = new Socket("fsds13" , 5412);
		ObjectOutputStream ois = new ObjectOutputStream(so.getOutputStream());

		//ois.writeObject(skB);
		System.out.println("SKB   : \t"+data);
		ois.writeObject(data);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}*/

return data;


	}

	public ECIES toECIES(int[] crypted) throws Exception
	{
		return DER2ECIES(crypted);
	}

	public byte[] toByteArray() {
		return Utils.toByteArray(data);
	}

	public String toString() {
		String s = new String();
		for (int i = 0; i < data.length; i++) {
			if (data[i] < 16)
				s = s.concat("0");
			s = s.concat(BigInteger.valueOf(data[i]).toString(16));
			s = s.concat(",");
		}
		return s;
	}

	protected Object clone() {
		return new DerIOBuffer(Utils.toByteArray(data));
	}

}
