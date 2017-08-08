
package com.hcss.cryptutil;

import java.io.Serializable;
import java.math.BigInteger;


public class Encryption implements Serializable
{
	DerIOBuffer der;
	int data1[];
	public Encryption()
	{
	}
	public Encryption(String check)
	{
		der = new DerIOBuffer();
		if(check.equalsIgnoreCase("Encryption"))
		{
			//ecies_ex();
		}
		else if(check.equalsIgnoreCase("Decryption"))
		{
			//Decryption dec=new Decryption(der);
		}
	}

	public int[] ecies_ex(String data )
	{
		System.out.println("IN SIDE EXEC>>>>>>>");

		// Degree 163 Binary Field from fips186-2
		ECDomainParameters dp = ECDomainParameters.NIST_B_163();

		ECPrivKey skA = new ECPrivKey(dp, BigInteger.valueOf(123));
		//ECPrivKey skA = new ECPrivKey (dp);
		ECPubKey pkA = new ECPubKey(skA);

		ECPrivKey skB = new ECPrivKey(dp, BigInteger.valueOf(230));
		//ECPrivKey skB = new ECPrivKey (dp);
		ECPubKey pkB = new ECPubKey(skB);

		String pt1 = data;
		System.out.print("(1)Plain Text: ");
		System.out.println(pt1);
		ECIES crypt = new ECIES(skA, pkB, pt1.getBytes()); // encrypt the data
		//String crypted = crypt.toString();
		///System.out.println("Crypted Data" + crypted);
		//data = der.DerIOBuffering(crypt);
		data1 = der.DER_Encode(crypt);
		//System.out.println("DER Encoding:" + der.toString());
		//String ccc=crypt.toString();

		//rmic =new rmiclient();
		//rmic.crypteddata(crypt);
		return data1;

	}
	public static void main(String[] args)
	{
		Encryption e = new Encryption(args[0].toString());
	}

}
