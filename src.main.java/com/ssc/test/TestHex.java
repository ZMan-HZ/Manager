package com.ssc.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;


public class TestHex {

	/**
	 * @param args
	 * 2018-05-20
	 */
	public static void main(String[] args) {
		//System.out.println(new String(hexStringToByteArray("616161")));
		
		//System.out.println(byteArrayToHex(hexStringToByteArray("61")));
		
		try {
//			generateTextFileByByteFile("C:/workFile/toVDI/WCBInSight.zip", "C:/workFile/toVDI/WCBInSight.txt");
			generateTextFileByByteFile("C:/cc_view/125.dat", "C:/workFile/toVDI/BGRADM.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	
	public static String byteArrayToHex(byte[] a) {
		   StringBuilder sb = new StringBuilder(a.length * 2);
		   for(byte b: a)
		      sb.append(String.format("%02x", b));
		   return sb.toString();
	}
	
	public static void generateTextFileByByteFile(String byteFilePath, String resultTextFilePath) throws Exception {
		BufferedInputStream bin = null;
		
		BufferedWriter out = null;
		try{
			
			int byteLength = 16;
			byte[] tempByte = new byte[byteLength];
			
			bin = new BufferedInputStream(new FileInputStream(byteFilePath));    
			out = new java.io.BufferedWriter(new java.io.FileWriter(resultTextFilePath));
			String lineSeparator = System.getProperty("line.separator");
			
			int readByteLength = 0;
			while((readByteLength=bin.read(tempByte))!=-1) {    
				if(byteLength==readByteLength) {
//					System.out.println(byteArrayToHex(tempByte));
					out.write(byteArrayToHex(tempByte));
					out.write(lineSeparator);
				} else {
					byte[] lastLineByte = new byte[readByteLength];
					for(int i=0; i<lastLineByte.length; i++) {
						lastLineByte[i] = tempByte[i];
					}
//					System.out.println(byteArrayToHex(lastLineByte));
					out.write(byteArrayToHex(lastLineByte));
					out.write(lineSeparator);
				}
			}
			out.flush();
			   
		} finally {
			try {
				bin.close();    
				out.close(); 
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
	}
	
	
	public static void generateByteFileByTextFile(String textFilePath, String resultByteFilePath) throws Exception {
		BufferedReader in = null;
		BufferedOutputStream out = null;
		
		try{
			in = new BufferedReader(new FileReader(textFilePath));    
			out = new BufferedOutputStream(new java.io.FileOutputStream(resultByteFilePath));
			
			String line;
			while ((line = in.readLine()) != null) {
				out.write(hexStringToByteArray(line));
			}
			out.flush();  
		} finally {
			try {
				in.close();    
				out.close(); 
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
	}
}
