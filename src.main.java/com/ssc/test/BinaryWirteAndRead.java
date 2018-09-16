package com.ssc.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class BinaryWirteAndRead {

	
	
	public static void main(String[] args){
		
		try {
//			generateTextFileByBinaryFile("C:/workFile/toVDI/BGRADM.zip", "C:/workFile/toVDI/BGRADMsss.txt");
			generateTextFileByBinaryFile("C:/cc_view/125.dat", "C:/workFile/toVDI/BGRADMsss.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void generateTextFileByBinaryFile(String sourceAbsolutePath,String targetAbsolutePath) throws Exception {
		
		DataInputStream readBinaryFile = new DataInputStream(new FileInputStream(sourceAbsolutePath)); 
//		DataOutputStream writeTextFile = new DataOutputStream(new FileOutputStream(targetAbsolutePath));
		FileOutputStream writeTextFile = new FileOutputStream(targetAbsolutePath);
		String lineSeparator = System.getProperty("line.separator");
		int lineLength = 0;
		while(readBinaryFile.available() > 0){
			Integer ss = readBinaryFile.readUnsignedByte();
			System.out.print(String.format("%02x", ss));
//			lineLength++;
//			if(lineLength > 63){
//				lineLength = 0;
				System.out.print(lineSeparator);
//			}
		}
		
		readBinaryFile.close();
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
