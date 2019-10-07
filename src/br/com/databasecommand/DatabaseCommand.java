package br.com.databasecommand;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import br.com.databasecommand.dao.DbCommandDao;
import br.com.databasecommand.exception.DaoException;
import br.com.databasecommand.model.Label;
import br.com.databasecommand.model.LabelComp;
import br.com.databasecommand.model.LabelPart;
import br.com.databasecommand.util.Util;

/**
 * Class File: DatabaseCommand.java
 * Description: Application Main Class - receive and check informed parameters and execute the
 * properly rule.
 *
 * @author michael_silva
 * 
 */

public class DatabaseCommand {
	public static void main(String[] args) throws IOException, SQLException, DaoException {
//		args = new String[]{"-MDS", "-getprintqty", "p88mdb1.dev1", "detailed", "2016-01-04", "2016-02-05"};
//		args = new String[]{"-LE", "-CIRRUS", "-checkgenattribute", "p88mdb1.dev1"};
		
		if (args.length < 1) {
			System.out.println("No parameters informed. \nTry '. dbcmd --help' for more information.");
			
		} else {
			String param1 = args[0];
	
			switch (param1.toUpperCase()) {
			case "-MDS":
				if (args.length < 3) {
					System.out.println("Invalid options. \nTry '. dbcmd --help' for more information.");
					
				} else {
					String param2 = args[1];
					
					Util util = new Util();
					String srcSvrInfo;
					String tgtSvrInfo;
					DbCommandDao dao = null;
					
					switch (param2) {
					case "-comparedb":
						if (!util.checkInput(args, "comparedb")) {
							System.out.println("Invalid input parameters.");
							break;
						}
						
						srcSvrInfo = args[2];
						tgtSvrInfo = args[3];
						
						try {
							dao = new DbCommandDao(srcSvrInfo.split("\\:")[0], srcSvrInfo.split("\\:")[1]);
							
						} catch (RuntimeException e) {
							System.out.println("Invalid database parameters.");
							break;
						}
						
						List<Label> labels = dao.compareLabel(tgtSvrInfo);
						List<LabelComp> lblcomps = dao.compareLblComp();
						List<LabelPart> lblparts = dao.compareLblPart();
								
						System.out.println("Source database: " + srcSvrInfo);
						System.out.println("Target database: " + tgtSvrInfo);
						
						System.out.println("\n- Labels missing on server/db \"" + tgtSvrInfo + "\": " + labels.size() + " row(s) \n");
						
						for (Label lbl: labels) {
							System.out.println(lbl.toString());
						}
						
						System.out.println("\n\n- Label components missing on server/db \"" + tgtSvrInfo + "\": " + lblcomps.size() + " row(s) \n");
						
						for (LabelComp lbl: lblcomps) {
							System.out.println(lbl.toString());
						}
						
						System.out.println("\n\n- Label parts missing on server/db \"" + tgtSvrInfo + "\": " + lblparts.size() + " row(s) \n");
						
						for (LabelPart lbl: lblparts) {
							System.out.println(lbl.toString());
						}
						
						System.out.println();
						break;
						
					case "-checkfunction":
						if (!util.checkInput(args, "checkfunction")) {
							System.out.println("Invalid input parameters.");
							break;
						}
						
						tgtSvrInfo = args[2];
						
						try {
							dao = new DbCommandDao(tgtSvrInfo.split("\\:")[0], tgtSvrInfo.split("\\:")[1]);
							
						} catch (RuntimeException e) {
							System.out.println("Invalid database parameters.");
							break;
						}
						
						if (args.length == 3) {
							List<String> functions = dao.checkFunc();
							
							if (functions.size() > 0) {
								System.out.println("Label functions missing on " + tgtSvrInfo + ": " + functions.size() + " function(s) \n");

								System.out.println("LT" + "\t" + "Function Name");
								System.out.println("----------------------");
								
								for (String func : functions) {
									System.out.println(func);
								}
								
							} else {
								System.out.println("No functions missing on " + tgtSvrInfo);
							}
							
						} else if (args.length == 6) {
							List<String> functions = dao.checkFuncSVN(args[3], args[4], args[5]);
							
							if (functions.size() > 0) {
								System.out.println("Label functions missing on " + tgtSvrInfo + ": " + functions.size() + " function(s) \n");
								
								System.out.println("LT" + "\t" + "in_SVN " + "Function Name");
								System.out.println("-----------------------------");
								
								for (String func : functions) {
									System.out.println(func);
								}
								
							} else {
								System.out.println("No functions missing on " + tgtSvrInfo);
							}
							
						} else if (args.length == 7) {
							List<String> functions = dao.checkFuncSVN(args[3], args[4], args[5], args[6]);
							
							if (functions.size() > 0) {
								System.out.println("Functions installed on " + tgtSvrInfo + ": " + functions.size() + " function(s) \n");
								
								System.out.println("Function Name                  " + "Branch Size  " + "Trunk Size");
								System.out.println("-------------------------------------------------------");

								for (String func : functions) {
									System.out.println(func);
								}
								
							} else {
								System.out.println("No functions installed on " + tgtSvrInfo);
							}
							
						} else{
							System.out.println("Invalid number of parameters.");
							break;
						}
						
						System.out.println();
						break;
						
					case "-checkfiles":
						if (!util.checkInput(args, "checkfiles")) {
							System.out.println("Invalid input parameters.");
							break;
						}
						
						tgtSvrInfo = args[2];
						
						try {
							dao = new DbCommandDao(tgtSvrInfo.split("\\:")[0], tgtSvrInfo.split("\\:")[1]);
							
						} catch (RuntimeException e) {
							System.out.println("Invalid database parameters.");
							break;
						}
						
						//checking environment
						List<String> dirs = dao.checkEnv();

						System.out.println("Checking environment...");
						
						for (String dir : dirs) {
							System.out.println(dir);
						}
						
						//get files missing
						List<String> lblfiles = dao.checkFiles();
						
						if (lblfiles.size() > 0) {
							System.out.println("\nLabel files missing on " + tgtSvrInfo + ": " + lblfiles.size() + "\n");
							System.out.println("LT" + "\t" + "file_name");
							System.out.println("------------------");
							
							for (String files : lblfiles) {
								System.out.println(files);
							}
						}
						
						System.out.println();
						break;
						
					case "-checklabcomp":
						if (!util.checkInput(args, "checklabcomp")) {
							System.out.println("Invalid input parameters.");
							break;
						}
						
						tgtSvrInfo = args[2];
						
						try {
							dao = new DbCommandDao(tgtSvrInfo.split("\\:")[0], tgtSvrInfo.split("\\:")[1]);
							
						} catch (RuntimeException e) {
							System.out.println("Invalid database parameters.");
							break;
						}
						
						List<String> issues = dao.checkCompIssues();
						
						if (issues.size() > 0) {
							System.out.println("Labels with component issues on " + tgtSvrInfo + ": " + issues.size() + "\n");
							System.out.println("LT" + "\t" + "lbl_comp_key " + "comp_name                     " + "comp_string");
							System.out.println("------------------------------------------------------------");

							for (String lbl : issues) {
								System.out.println(lbl);
							}
						}
						
						System.out.println();
						break;
						
					case "-getprintqty":
						if (!util.checkInput(args, "getprintqty")) {
							System.out.println("Invalid input parameters.");
							break;
						}
						
						tgtSvrInfo = args[3];
						String option  = args[2];
						String iniDate = args[4];
						String endDate = args[5];
						
						if (option.equalsIgnoreCase("-resume")) {
							try {
								dao = new DbCommandDao(tgtSvrInfo.split("\\:")[0], tgtSvrInfo.split("\\:")[1]);
								
							} catch (RuntimeException e) {
								System.out.println("Invalid database parameters.");
								break;
							}
							
							List<String> quantity = dao.getLblQty(iniDate, endDate, option, tgtSvrInfo);
							
							if (quantity.size() > 0) {
								System.out.println("Date Range: " + iniDate + " - " + endDate);
								System.out.println("Server: " + tgtSvrInfo.split("\\:")[0]);
								System.out.println("\nDatabase | Quantity");
								System.out.println("-----------------------------------");
								
								for (String qty : quantity) {
									System.out.println(qty);
								}
							}
							
							System.out.println();
							break;
							
						} else if (option.equalsIgnoreCase("-detailed")) {
							try {
								dao = new DbCommandDao(tgtSvrInfo.split("\\:")[0], tgtSvrInfo.split("\\:")[1]);
								
							} catch (RuntimeException e) {
								System.out.println("Invalid database parameters.");
								break;
							}
							
							List<String> quantity = dao.getLblQty(iniDate, endDate, option, tgtSvrInfo);
							
							if (quantity.size() > 0) {
								System.out.println("Date Range: " + iniDate + " - " + endDate);
								System.out.println("Server: " + tgtSvrInfo.split("\\:")[0]);
								
								for (String qty : quantity) {
									System.out.println(qty);
								}
							}
							
							System.out.println();
							break;
							
						} else {
							System.out.println("dbcmd: invalid option '" + option + "' \nTry '. dbcmd --help' for more information.");
							break;
						}
						
					case "-wordsearch":
						if (!util.checkInput(args, "wordsearch")) {
							System.out.println("Invalid input parameters.");
							break;
						}
						
						tgtSvrInfo = args[2];
						
						try {
							dao = new DbCommandDao(tgtSvrInfo.split("\\:")[0], tgtSvrInfo.split("\\:")[1]);
							
						} catch (RuntimeException e) {
							System.out.println("Invalid database parameters.");
							break;
						}
						
						List<String> comp = dao.wordSearch(args[3]);
						
						if (comp.size() > 0) {
							System.out.println("Labels that is using \"" + args[3] + "\" on " + tgtSvrInfo.split("\\:")[0] + "\n");
							System.out.println("LT" + "\t" + "comp_name                      " + "comp_string");
							System.out.println("---------------------------------------------------");
							
							for (String lbl : comp) {
								System.out.println(lbl);
							}
						}
						
						System.out.println();
						break;
						
					default:
						System.out.println("dbcmd: invalid option '" + param2 + "' \nTry '. dbcmd --help' for more information.");
						break;
					}
				}
				
				break;
				
			case "-LE":
				if (args.length < 3) {
					System.out.println("Invalid options. \nTry '. dbcmd --help' for more information.");
					
				} else {
					String param2 = args[1];
					
					Util util = new Util();
					String srcSvrInfo;
					String tgtSvrInfo;
					DbCommandDao dao  = null;
						
					switch (param2) {
					case "-checkgenattribute":
						if (!util.checkInput(args, "checkgenattribute")) {
							System.out.println("Invalid input parameters.");
							break;
						}
						
						srcSvrInfo = args[2];
						
						try {
							dao = new DbCommandDao(srcSvrInfo.split("\\:")[0], srcSvrInfo.split("\\:")[1]);
							
						} catch (RuntimeException e) {
							System.out.println("Invalid database parameters.");
							break;
						}
						
						//get gen attribute data missing
						List<String> genattr = dao.checkGenAttr();
						
						if (genattr.size() > 0) {
							System.out.println("Gen Attribute status on " + srcSvrInfo.split("\\:")[1] + "\n");
							System.out.println("attr_name" + "\t" + "attr_data" + "\t" + "status");
							System.out.println("------------------------------------------------");
							
							for (String attr : genattr) {
								System.out.println(attr);
							}
						}
						
						System.out.println();
						break;
						
					case "-getusedfunctions":
						if (!util.checkInput(args, "getusedfunctions")) {
							System.out.println("Invalid input parameters.");
							break;
						}
						
						srcSvrInfo = args[2];
						String iniDate = args[3];
						String endDate = args[4];
						
						try {
							dao = new DbCommandDao(srcSvrInfo.split("\\:")[0], srcSvrInfo.split("\\:")[1], "postgres", "postgres");
							
						} catch (RuntimeException e) {
							System.out.println("Invalid database parameters.");
							break;
						}
						
						//get functions list
						List<String> functions = dao.getUsedFunctions(iniDate, endDate);
						
						if (functions.size() > 0) {
							System.out.println("Special Functions used on " + srcSvrInfo.split("\\:")[1] + "\n");
							System.out.println("Function Name                                             Label Name");
							System.out.println("--------------------------------------------------------------------");
							
							for (String func : functions) {
								System.out.println(func);
							}
						}
						
						System.out.println();
						break;
						
					case "-checklabels":
						if (!util.checkInput(args, "checklabels")) {
							System.out.println("Invalid input parameters.");
							break;
						}
						
						srcSvrInfo = args[2];
						
						try {
							dao = new DbCommandDao(srcSvrInfo.split("\\:")[0], srcSvrInfo.split("\\:")[1], "postgres", "postgres");
							
						} catch (RuntimeException e) {
							System.out.println("Invalid database parameters.");
							break;
						}
						
						//get functions list
						List<String> labels = dao.CheckLabels();
						
						if (labels.size() > 0) {
							System.out.println("Labels using functions on " + srcSvrInfo.split("\\:")[1] + "\n");
							System.out.println("Label Name                  " + "Function                         " + 
											   "Func Installed  " + "File Template             " + "File Installed");
							System.out.println("---------------------------------------------------------------------------------------------------------------------");
							
							for (String lbl : labels) {
								System.out.println(lbl);
							}
						}
						
						System.out.println();
						break;
						
					case "-checkfunction":
						if (!util.checkInput(args, "checkfunction")) {
							System.out.println("Invalid input parameters.");
							break;
						}
						
						tgtSvrInfo = args[2];
						
						try {
							dao = new DbCommandDao(tgtSvrInfo.split("\\:")[0], tgtSvrInfo.split("\\:")[1], "postgres", "postgres");
							
						} catch (RuntimeException e) {
							System.out.println("Invalid database parameters.");
							break;
						}
						
						if (args.length == 7) {
							List<String> funcList = dao.checkFuncLE(args[3], args[4], args[5], args[6]);
							
							if (funcList.size() > 0) {
								System.out.println("LE Functions installed on " + tgtSvrInfo + ": " + funcList.size() + " function(s) \n");
								
								System.out.println("Function Name                                             " + "Branch Size  " + "Trunk Size");
								System.out.println("---------------------------------------------------------------------------------");

								for (String func : funcList) {
									System.out.println(func);
								}
								
							} else {
								System.out.println("No functions installed on " + tgtSvrInfo);
							}
							
						} else{
							System.out.println("Invalid number of parameters.");
							break;
						}
						
						System.out.println();
						break;
						
					case "-wordsearch":
						if (!util.checkInput(args, "wordsearch")) {
							System.out.println("Invalid input parameters.");
							break;
						}
						
						tgtSvrInfo = args[2];
						String server = tgtSvrInfo.split("\\:")[0];
						String dbName = tgtSvrInfo.split("\\:")[1];
						
						try {
							
							dao = new DbCommandDao(server, dbName);
							
						} catch (RuntimeException e) {
							System.out.println("Invalid database parameters.");
							System.out.println(e.getMessage());
							break;
						}
						
						List<String> comp = dao.wordSearchLe(args[3], dbName);
						
						if (comp.size() > 0) {
							System.out.println("Labels that is using \"" + args[3] + "\" on " + tgtSvrInfo.split("\\:")[0] + "\n");
							System.out.println("Key" + "\t" + "Name                            " + "Element Name");
							System.out.println("----------------------------------------------------------------");
							
							for (String lbl : comp) {
								System.out.println(lbl);
							}
						}
						
						System.out.println();
						break;
					
					default:
						System.out.println("dbcmd: invalid option '" + param2 + "' \nTry '. dbcmd --help' for more information.");
						break;
					}
					
					break;
				}
				
				break;
				
			case "--HELP":
				System.out.println(
						"Command used to provide label information \n\n"
					  + "Usage: . dbcmd -[MDS|LE] -[OPTION] [args...]\n\n"
		
					  + "MDS [OPTION] includes:\n"
					  + "-comparedb     <source_server:db> <target_server:db>                 Compare twodatabases checking what is missing in the target one in terms of label perspective;\n"
					  + "-checkfunction <server:db>                                           Check missing functions forLabels;\n"
					  + "-checkfunction <server:db> <svn_path>               <user> <pwd>     Check missing functions forLabels against some specific SVN directory;\n"
					  + "-checkfunction <server:db> <svn_branch> <svn_trunk> <user> <pwd>     Compare if file size matches in both SVN directories for a specific database for all label functions;\n"
					  + "-checkfiles    <server:db>                                           Check if alllabel files are present in the server and in the proper place;\n"
					  + "-checklabcomp  <server:db>                                           Check iflabel components have issue on their definition;\n"
					  + "-getprintqty   -[detailed/resume] <server:db> <dt_ini> <dt_fin>      GetLabels print quantity in a specific period. Date format <YYYY-MM-DD>;\n"
					  + "-wordsearch    <server:db> <word>                                    Getlabel components from all databases in the server that have the word passed as parameter. \n\n"
		
					  + "LE [OPTION] includes:\n"
					  + "-checkfunction     <server:db> <svn_branch> <svn_trunk> <user> <pwd> Compare if file size matches in both SVN directories for a specific database for all label functions;\n"
					  + "-checkgenattribute <server:db>                                       Check generic attribute values against LE token information for all databases;\n"
					  + "-checklabels       <server:db>                                       Check missing funcions and files from all LE labels in the database;\n"
					  + "-getusedfunctions  <server:db> <dt_ini> <dt_fin>                     Get LE Special Functions used in a specific period.  Date format <YYYY-MM-DD>;\n"
					  + "-wordsearch        <server:db> <word>                                Get label key and label names that have the word passed as parameter for all databases on server passed;\n");
				break;

			default:
				System.out.println("dbcmd: invalid option '" + param1 + "' \nTry '. dbcmd --help' for more information.");
				break;
				
			}
		}
	}
}
