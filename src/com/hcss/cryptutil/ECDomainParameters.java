package com.hcss.cryptutil;

import java.io.Serializable;
import java.math.BigInteger;

 
public class ECDomainParameters implements Serializable {
 
	public static ECDomainParameters NIST_B_163() 
	{
		F2m.setModulus(163, 7, 6, 3, 0);
		ECDomainParameters NIST_B_163 =
			new ECDomainParameters(
				163,
				7,
				6,
				3,
				new ECurveF2m(
					new F2m("1", 16),
					new F2m("20a601907b8c953ca1481eb10512f78744a3205fd", 16)),
				new BigInteger(
					"5846006549323611672814742442876390689256843201587",
					10),
				new ECPointF2m(
					new F2m("3f0eba16286a2d57ea0991168d4994637e8343e36", 16),
					new F2m("0d51fbc6c71a0094fa2cdd545b11c5c0c797324f1", 16)),
				BigInteger.valueOf(2));
		return NIST_B_163;
	}
 


	// Parameters for Curves over Binary Fields

	/**
	 * a positive integer, specifying the field GF(2^m)
	 */
	public int m;
	/**
	 * Gaussian(1), Trinomial(2), Pentanomial(3)
	 */
	public int basis;
	/**
	 * x^m + x^k +1
	 */
	public int trinomial_k;
	/**
	 * x^m + x^k3 + x^k2 + x^k1 +1
	 */
	public int pentanomial_k3;
	/**
	 * x^m + x^k3 + x^k2 + x^k1 +1
	 */
	public int pentanomial_k2;
	/**
	 * x^m + x^k3 + x^k2 + x^k1 +1
	 */
	public int pentanomial_k1;

	// Parameters for Curves over Prime Fields

	/**
	 * The modulus
	 */
	public BigInteger p;

	// Common Parameters
	public ECurve E; // Binary Case :- E: y^2 + xy = x^3 + ax^2 + b
	// Prime Case :-  E: y^2 = x^3 - 3x^2 + b

	/**
	 * A positive prime integer dividing the number of points on E
	 */
	public BigInteger r;
	/**
	 * A point on E of order r
	 */
	public ECPoint G;

	/**
	 * A positive prime integer, k = #E/r
	 */
	public BigInteger k;

	/**
	 * The type of field (0=binary, 1=prime)
	 */
	protected int type;

	/**
	 * Constructor
	 */
	public ECDomainParameters() {
	}

	/**
	 * Constructor
	 */
	public ECDomainParameters(ECDomainParameters dp) {
		this.m = dp.m;
		this.basis = dp.basis;
		this.trinomial_k = dp.trinomial_k;
		this.pentanomial_k3 = dp.pentanomial_k3;
		this.pentanomial_k2 = dp.pentanomial_k2;
		this.pentanomial_k1 = dp.pentanomial_k1;
		this.p = dp.p;
		this.E = dp.E;
		this.r = dp.r;
		this.G = dp.G;
		this.k = dp.k;
		this.type = dp.type;
	}

	/**
	 * Constructor
	 */
	public ECDomainParameters(
		int m,
		int trinomial_k,
		ECurveF2m E,
		BigInteger r,
		ECPointF2m G,
		BigInteger k) {
		this.m = m;
		this.basis = 2;
		this.trinomial_k = trinomial_k;
		this.E = (ECurve) E.clone();
		this.r = r;
		this.G = (ECPoint) G.clone();
		this.k = k;
		this.type = 0;
	}

	/**
	 * Constructor
	 */
	public ECDomainParameters(
		int m,
		int pentanomial_k3,
		int pentanomial_k2,
		int pentanomial_k1,
		ECurveF2m E,
		BigInteger r,
		ECPointF2m G,
		BigInteger k) {
		this.m = m;
		this.basis = 3;
		this.pentanomial_k3 = pentanomial_k3;
		this.pentanomial_k2 = pentanomial_k2;
		this.pentanomial_k1 = pentanomial_k1;
		this.E = (ECurve) E.clone();
		this.r = r;
		this.G = (ECPoint) G.clone();
		this.k = k;
		this.type = 0;
	}

	protected boolean MOV_Condition(int m, BigInteger r) {
		int B;

		if (m <= 142)
			B = 6;
		else if (m <= 165)
			B = 7;
		else if (m <= 186)
			B = 8;
		else if (m <= 206)
			B = 9;
		else if (m <= 226)
			B = 10;
		else if (m <= 244)
			B = 11;
		else if (m <= 262)
			B = 12;
		else if (m <= 280)
			B = 13;
		else if (m <= 297)
			B = 14;
		else if (m <= 313)
			B = 15;
		else if (m <= 330)
			B = 16;
		else if (m <= 346)
			B = 17;
		else if (m <= 361)
			B = 18;
		else if (m <= 376)
			B = 19;
		else if (m <= 391)
			B = 20;
		else if (m <= 406)
			B = 21;
		else if (m <= 420)
			B = 22;
		else if (m <= 434)
			B = 23;
		else if (m <= 448)
			B = 24;
		else if (m <= 462)
			B = 25;
		else if (m <= 475)
			B = 26;
		else if (m <= 488)
			B = 27;
		else if (m <= 501)
			B = 28;
		else
			B = 29;

		BigInteger t = BigInteger.valueOf(1);
		BigInteger q = BigInteger.valueOf(1).shiftLeft(m);
		for (int i = 1; i <= B; i++) {
			t = t.multiply(q).mod(r);
			if (t.compareTo(BigInteger.valueOf(1)) == 0)
				return false;
		}

		return true;
	}

	/**
	 * A partial implementation (steps 6.4 to 7) of A.16.8 in P1363
	 * <P>
	 * 6.4 Check that a6 != 0 in GF (2 m ).
	 * <BR>
	 * 6.5 Check that G != O. Let G = (x, y).
	 * <BR>
	 * 6.6 Check that x and y are elements of GF (2 m ).
	 * <BR>
	 * 6.7 Check that y^2 + xy = x^3 + ax^2 + b in GF (2 m ).
	 * <BR>
	 * 6.8 Check that rG = O.
	 * <BR>
	 * 6.9 Check that the curve is not an instance of the following 
	 * <BR>
	 *     excluded case:
	 * <BR>
	 * 6.9.1 If the output of the algorithm given in A.12.1 is "False"
	 * <BR>
	 *       then the curve is excluded because it is subject to the MOV
	 * <BR>
	 *       reduction attack described in [MOV93].
	 * <BR>
	 * 7. Output "True" if the checks given in Steps 4 through 6 work, 
	 * <BR>
	 *    and "False" otherwise.
	 */
	public boolean isValid() {
		if (E.a6.isZero())
			return false;
		if (G.isZero())
			return false;
		if ((G.y.mul(G.y).add(G.x.mul(G.y)))
			.compareTo(
				(G.x.mul(G.x).mul(G.x).add(E.a4.mul(G.x).mul(G.x).add(E.a6))))
			!= 0)
			return false;
		if (!E.mul(r, G).isZero())
			return false;
		if (!MOV_Condition(m, r))
			return false;
		return true;
	}

	public String toString() {
		String str = new String("\n");
		if (type == 0) {
			str = str.concat("x^").concat(String.valueOf(m)).concat(" + ");
			if (basis == 1) {
			} else if (basis == 2) {
				str =
					str.concat("x^").concat(
						String.valueOf(trinomial_k)).concat(
						" + 1\n");
			} else if (basis == 3) {
				str =
					str.concat("x^").concat(
						String.valueOf(pentanomial_k3)).concat(
						" + ");
				str =
					str.concat("x^").concat(
						String.valueOf(pentanomial_k2)).concat(
						" + ");
				str =
					str.concat("x^").concat(
						String.valueOf(pentanomial_k1)).concat(
						" + 1\n");
			}
		} else if (type == 1) {
			str = str.concat("p:").concat(p.toString()).concat("\n");
		}
		str = str.concat("E:\n").concat(E.toString()).concat("\n");
		str = str.concat("r: ").concat(r.toString()).concat("\n");
		str = str.concat("G: x:").concat(G.x.toString()).concat("\n");
		str = str.concat("   y:").concat(G.y.toString()).concat("\n");
		str = str.concat("k(#E/r): ").concat(k.toString()).concat("\n");
		return str;
	}

	protected Object clone() {
		return new ECDomainParameters(this);
	}
}
