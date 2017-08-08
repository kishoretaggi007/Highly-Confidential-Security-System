package com.hcss.cryptutil;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class Decryption implements Serializable {
	ECPrivKey skB;
	ECIES crypt;
	DerIOBuffer der;
	ECIES crypted1;
	int[] crypted;

	public Decryption() {

	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Decryption d = new Decryption();
		DerIOBuffer derIOBuffer = new DerIOBuffer();
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@java28:1521:xe", "wcms", "wcms");
		PreparedStatement ptmt = con
				.prepareStatement("select password from logindetails where loginname='a'");
		ResultSet rs = ptmt.executeQuery();
		
		if(rs.next()){
			String  s = rs.getString(1);
		Collection c = new ArrayList();
		c.add(s);
		Object obj[] = c.toArray();
		for (int i = 0; i < obj.toString().length(); i++) {
			Integer io = (Integer) obj[i];
			int x = io.intValue();
			System.out.println(x);
			// x+=x;
		}}
	}

	public String decrypt(int[] crypt) {
		String decrypteddata = "";
		// int a[]=(int [])crypt;

		try {
			System.out.println("IN DEC" + crypt);
			// crypted1=(DerIOBuffer)crypt;
			ECDomainParameters dp = ECDomainParameters.NIST_B_163();
			ECPrivKey skB = new ECPrivKey(dp, BigInteger.valueOf(230));
			ECPubKey pkB = new ECPubKey(skB);
			der = new DerIOBuffer();
			ECIES decrypt = der.toECIES(crypt);
			System.out.print("(2)Cipher Text: ");
			System.out.println(decrypt.toString());
			byte[] pt2 = decrypt.decrypt(skB); // decrypt the data
			System.out.print("(3)Plain Text: ");
			// System.out.println(new String(pt2));
			decrypteddata = new String(pt2);
			System.out.println(decrypteddata);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return decrypteddata;
	}
}
