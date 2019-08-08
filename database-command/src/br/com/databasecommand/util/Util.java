package br.com.databasecommand.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Util Class: Used to support on verifications and other utilities.
 * 
 * @author michael_silva
 *
 */

public class Util {
	
	/**
	 * Validate server and database input based on command received.
	 * 
	 * @param args Input string from main method
	 * @param cmd Input command option select by the user. Ex: -comparedb
	 * @return boolean
	 */
	public boolean checkInput(String args[], String cmd) {
		switch (cmd) {
		case "comparedb":
			if (args.length < 4) {
				return false;
			}

			try {
				String srcSvrInfo = args[2];
				String tgtSvrInfo = args[3];

				String srcServer   = srcSvrInfo.split("\\.")[0];
				String srcDatabase = srcSvrInfo.split("\\.")[1];
				String tgtServer   = tgtSvrInfo.split("\\.")[0];
				String tgtDatabase = tgtSvrInfo.split("\\.")[1];
				
				if (srcServer.isEmpty() || srcDatabase.isEmpty() || tgtServer.isEmpty() || tgtDatabase.isEmpty()) {
					return false;
				}
				
			} catch (Exception e) {
				return false;
			
			}

			break;
			
		case "getprintqty":
			if (args.length < 6) {
				return false;
			}
			
			break;
			
		case "getusedfunctions":
			if (args.length < 5) {
				return false;
			}
			
			break;
			
		case "wordsearch":
			if (args.length < 4) {
				return false;
			}
			
			break;
			
		case "checkgenattribute":
			if (args.length < 3) {
				return false;
			}
			
			break;
			
		default:
			if (args.length < 3) {
				return false;
			}

			try {
				String tgtSvrInfo = args[2];

				String tgtServer   = tgtSvrInfo.split("\\.")[0];
				String tgtDatabase = tgtSvrInfo.split("\\.")[1];
			
				if (tgtServer.isEmpty() || tgtDatabase.isEmpty()) {
					return false;
				}
			
			} catch (Exception e) {
				return false;
			}
			
			break;
		}
		
		return true;
	}
	
	/**
	 * Get property address from property file.
	 * 
	 * @return properties
	 */
	public Properties getProperty() {
		InputStream inputStream = null;
		Properties prop = new Properties();
		String propFileName = "sql.properties";
		
		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			System.out.println("property file '" + propFileName
					+ "' not found in the classpath");
		}finally{
			try {
				//Potential resource leak removed when close inputStream on finally
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return prop;
	}
}
