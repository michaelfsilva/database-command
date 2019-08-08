package br.com.databasecommand.dao;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import br.com.databasecommand.exception.DaoException;
import br.com.databasecommand.model.Label;
import br.com.databasecommand.model.LabelComp;
import br.com.databasecommand.model.LabelPart;
import br.com.databasecommand.util.Util;

/**
 * Dao Class: create the connection to database based on informed data. Execute
 * tasks in database connected.
 * 
 * @author michael_silva
 *
 */

public class DbCommandDao {
	private Connection connection;
	private Properties prop;

	/**
	 * Dao Constructor: call getConnection method to create a connection based
	 * on received parameters and use the util method to initialize the property
	 * attribute.
	 * 
	 * @param server
	 *            Name of server to create the connection
	 * @param database
	 *            Name of database to create the connection
	 * @throws ConnectException
	 */
	public DbCommandDao(String server, String database) throws RuntimeException {
		try {
			this.connection = new ConnectionFactory().getConnection(server, database, "postgres", "postgres");

		} catch (RuntimeException e) {
			throw e;
		}

		Util util = new Util();
		prop = util.getProperty();
	}

	/**
	 * Dao Constructor: call getConnection method to create a connection based
	 * on received parameters and use the util method to initialize the property
	 * attribute.
	 * 
	 * @param server
	 *            Name of server to create the connection
	 * @param database
	 *            Name of database to create the connection
	 * @param user
	 *            Name to create the connection
	 * @param password
	 *            of user to create the connection
	 * @throws ConnectException
	 */
	public DbCommandDao(String server, String database, String username, String password) throws RuntimeException {
		try {
			this.connection = new ConnectionFactory().getConnection(server, database, username, password);

		} catch (RuntimeException e) {
			throw e;
		}

		Util util = new Util();
		prop = util.getProperty();
	}

	/**
	 * Get and Compare Label table informations between database from current
	 * connection and informed database on parameter.
	 * 
	 * @param tgtSvrInfo
	 *            Target server.database string for internal connection.
	 * @return List<Label> labels
	 * @throws SQLException
	 * @throws DaoException
	 */
	public List<Label> compareLabel(String tgtSvrInfo) throws SQLException, DaoException {
		List<Label> labels = new ArrayList<Label>();

		PreparedStatement stmt = null;
		ResultSet rs = null; 
				
		try {
			stmt = this.connection.prepareStatement(
					this.prop.getProperty("GET_LBL_DATA").replaceAll("_server", tgtSvrInfo.split("[:]")[0])
							.replaceAll("_dbname", tgtSvrInfo.split("[:]")[1]));
			stmt.execute();
			stmt.close();
			
			stmt = this.connection.prepareStatement(this.prop.getProperty("COMPARE_LABEL"));
			rs = stmt.executeQuery();

			while (rs.next()) {
				Label label = new Label();

				label.setReleasedLabel(rs.getBoolean("released_label"));
				label.setModificationTS(rs.getString("modification_ts"));
				label.setLabelType(rs.getInt("label_type"));
				label.setAltInitString(rs.getString("alt_init_string"));
				label.setProcedureName(rs.getString("procedure_name"));
				label.setPartKey(rs.getInt("part_key"));
				label.setFormatName(rs.getString("format_name"));
				label.setPrintQuantity(rs.getInt("print_quantity"));
				label.setTemplateKey(rs.getInt("template_key"));
				label.setUseTemplate(rs.getBoolean("use_template"));
				label.setTemplateFlag(rs.getBoolean("template_flag"));
				label.setA1(rs.getString("a1"));
				label.setI1(rs.getInt("i1"));
				label.setL1(rs.getBoolean("l1"));
				label.setOutputDestination(rs.getString("output_destination"));
				label.setGeneric(rs.getBoolean("is_generic"));

				labels.add(label);
			}

//			stmt.close();

		} catch (SQLException e) {
			this.connection.close();

			throw new DaoException();
		}finally{
			//Potential resource leak removed when close stmt and rs on finally
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
		}

		return labels;
	}

	/**
	 * Compare Label Component table informations between database from current
	 * connection.
	 * 
	 * @return List<LabelComp> lblcomps
	 * @throws DaoException
	 * @throws SQLException
	 */
	public List<LabelComp> compareLblComp() throws DaoException, SQLException {
		List<LabelComp> lblcomps = new ArrayList<LabelComp>();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = this.connection.prepareStatement(this.prop.getProperty("COMPARE_LBLCOMP"));
			rs = stmt.executeQuery();

			while (rs.next()) {
				LabelComp lblcomp = new LabelComp();

				lblcomp.setPrinterType(rs.getString("printer_type"));
				lblcomp.setPrintCmdTitle(rs.getString("print_cmd_title"));
				lblcomp.setPrintCmdString(rs.getString("print_cmd_string"));
				lblcomp.setPrintCmdType(rs.getInt("print_cmd_type"));
				lblcomp.setPrintRowPosition(rs.getInt("print_row_position"));
				lblcomp.setPrintColumnPosition(rs.getInt("print_column_position"));
				lblcomp.setFontCmdString(rs.getString("font_cmd_string"));
				lblcomp.setRotation(rs.getString("rotation"));
				lblcomp.setLabelType(rs.getInt("label_type"));
				lblcomp.setDataId(rs.getString("data_id"));
				lblcomp.setDownloadGraphic(rs.getBoolean("download_graphic"));
				lblcomp.setMandatoryFlag(rs.getBoolean("mandatory_flag"));
				lblcomp.setMandatoryFailMsg(rs.getString("mandatory_fail_msg"));
				lblcomp.setA1(rs.getString("a1"));
				lblcomp.setI1(rs.getInt("i1"));
				lblcomp.setL1(rs.getBoolean("l1"));

				lblcomps.add(lblcomp);
			}

			stmt.close();

		} catch (SQLException e) {
			this.connection.close();
			throw new DaoException();
		}finally{
			//Potential resource leak removed when close stmt and rs on finally
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
		}

		return lblcomps;
	}

	/**
	 * Compare Label Part table informations between database from current
	 * connection.
	 * 
	 * @return List<LabelPart> lblparts
	 * @throws DaoException
	 * @throws SQLException
	 */
	public List<LabelPart> compareLblPart() throws DaoException, SQLException {
		List<LabelPart> lblparts = new ArrayList<LabelPart>();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = this.connection.prepareStatement(this.prop.getProperty("COMPARE_LBLPART"));
			rs = stmt.executeQuery();

			while (rs.next()) {
				LabelPart lblpart = new LabelPart();

				lblpart.setLabelType(rs.getInt("label_type"));
				lblpart.setI1(rs.getInt("i1"));
				lblpart.setLabelText1(rs.getString("label_text1"));
				lblpart.setLabelText2(rs.getString("label_text2"));
				lblpart.setLabelText3(rs.getString("label_text3"));
				lblpart.setLabelText4(rs.getString("label_text4"));
				lblpart.setLabelText5(rs.getString("label_text5"));

				lblparts.add(lblpart);
			}

			stmt.close();

		} catch (SQLException e) {
			throw new DaoException();

		} finally {
			this.connection.close();
			//Potential resource leak removed when close stmt and rs on finally
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
		}

		return lblparts;
	}

	/**
	 * Check if all label functions used on current database are installed and
	 * return a list of missing functions.
	 * 
	 * @return List<String> funcList
	 * @throws DaoException
	 * @throws SQLException
	 */
	public List<String> checkFunc() throws DaoException, SQLException {
		List<String> funcList = new ArrayList<>();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = this.connection.prepareStatement(this.prop.getProperty("COMPARE_FUNC"));
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				// concatenate fields to improve the user view
				String fname = rs.getString("label_type") + "\t" + rs.getString("procedure_name");

				funcList.add(fname);
			}

			stmt.close();

		} catch (SQLException e) {
			throw new DaoException();

		} finally {
			this.connection.close();
			//Potential resource leak removed when close stmt and rs on finally
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
		}

		return funcList;
	}

	/**
	 * Check if all label functions used on current database are installed and
	 * return a list of missing functions and if they are in SVN.
	 * 
	 * @param svn_path
	 * @param username
	 * @param password
	 * @return List<String> funcList
	 * @throws DaoException
	 * @throws SQLException
	 */
	public List<String> checkFuncSVN(String svn_path, String username, String password)
			throws DaoException, SQLException {
		List<String> funcList = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = this.connection.prepareStatement(this.prop.getProperty("COMPARE_FUNC_SVN")
					.replaceAll("_svnpath", svn_path).replaceAll("_user", username).replaceAll("_pass", password));
			rs = stmt.executeQuery();

			while (rs.next()) {
				// concatenate fields to improve the user view
				String fname = rs.getString("label_type") + "\t" + String.format("%-7s", rs.getString("svn"))
						+ rs.getString("procedure_name");

				funcList.add(fname);
			}

		} catch (SQLException e) {
			throw new DaoException();

		} finally {
			this.connection.close();
			//Potential resource leak removed when close stmt and rs on finally
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
		}

		return funcList;
	}

	/**
	 * Check if all label functions used on current database are the same in the
	 * branch and the trunk on SVN.
	 * 
	 * @param svn_branch
	 * @param svn_trunk
	 * @param username
	 * @param password
	 * @return List<String> funcList
	 * @throws DaoException
	 * @throws SQLException
	 */
	public List<String> checkFuncSVN(String svn_branch, String svn_trunk, String username, String password)
			throws DaoException, SQLException {
		List<String> funcList = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = this.connection.prepareStatement(this.prop.getProperty("COMPARE_SVN_PATH")
					.replaceAll("_branchpath", svn_branch).replaceAll("_trunkpath", svn_trunk)
					.replaceAll("_user", username).replaceAll("_pass", password));
			stmt.execute();
			stmt.close();
			
			stmt = this.connection.prepareStatement(this.prop.getProperty("GET_SVN_PATH"));
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				// concatenate fields to improve the user view
				String fname = String.format("%-31s", rs.getString("function_name"))
						+ String.format("%-12s", rs.getString("branch_size")) + rs.getString("trunk_size");

				funcList.add(fname);
			}

		} catch (SQLException e) {
			throw new DaoException();

		} finally {
			this.connection.close();
			//Potential resource leak removed when close stmt and rs on finally
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
		}

		return funcList;
	}

	/**
	 * Check if system folders exists on server and return a list of it.
	 * 
	 * @return List<String> dirList
	 * @throws DaoException
	 * @throws SQLException
	 */
	public List<String> checkEnv() throws DaoException, SQLException {
		List<String> dirList = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = this.connection.prepareStatement(this.prop.getProperty("CHECK_ENV"));
			rs = stmt.executeQuery();

			while (rs.next()) {
				String dir = rs.getString("directory");

				if (!dir.contains("No such file or directory")) {
					dir = dir.split(":")[1] + " - ok";

				} else {
					dir = dir.split(":")[1] + " - not found!";
				}

				dirList.add(dir);
			}

		} catch (SQLException e) {
			throw new DaoException();
		} finally{
			//Potential resource leak removed when close stmt and rs on finally
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
		}

		return dirList;

	}

	/**
	 * Check if all label files used on current database are in server folder
	 * and return a list of missing files.
	 * 
	 * @return List<String> fileList
	 * @throws DaoException
	 * @throws SQLException
	 */
	public List<String> checkFiles() throws DaoException, SQLException {

		List<String> fileList = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = this.connection.prepareStatement(this.prop.getProperty("CHECK_FORMAT"));
			rs = stmt.executeQuery();

			while (rs.next()) {
				// concatenate fields to improve the user view
				String fname = rs.getString("label_type") + "\t" + rs.getString("format");

				fileList.add(fname);
			}
			stmt.close();
			rs.close();

			stmt = this.connection.prepareStatement(this.prop.getProperty("CHECK_FONTS"));
			rs = stmt.executeQuery();

			while (rs.next()) {
				// concatenate fields to improve the user view
				String fname = rs.getString("label_type") + "\t" + rs.getString("font");

				fileList.add(fname);
			}
			stmt.close();
			rs.close();

			stmt = this.connection.prepareStatement(this.prop.getProperty("CHECK_GRAPH"));
			rs = stmt.executeQuery();

			while (rs.next()) {
				// concatenate fields to improve the user view
				String fname = rs.getString("label_type") + "\t" + rs.getString("graphic");

				fileList.add(fname);
			}

		} catch (SQLException e) {
			throw new DaoException();

		} finally {
			this.connection.close();
			//Potential resource leak removed when close stmt and rs on finally
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
		}

		return fileList;
	}

	/**
	 * Validate label components that references other tables and return the
	 * list of components with issues.
	 * 
	 * @return List<String> issues
	 * @throws DaoException
	 * @throws SQLException
	 */
	public List<String> checkCompIssues() throws DaoException, SQLException {
		List<String> issues = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = this.connection.prepareStatement(this.prop.getProperty("GET_COMP_ISSUES"));
			stmt.execute();
			stmt.close();
			
			stmt = this.connection.prepareStatement(this.prop.getProperty("CHECK_COMP"));
			rs = stmt.executeQuery();

			while (rs.next()) {
				String fname = rs.getString("issues");

				issues.add(fname);
			}

			stmt.close();

		} catch (SQLException e) {
			throw new DaoException();

		} finally {
			this.connection.close();
			//Potential resource leak removed when close stmt and rs on finally
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
		}

		return issues;
	}

	/**
	 * Get print labels quantity in a desired period from a specific server and
	 * return the total. Options: -resume - Return a list of databases and the
	 * print quantity for each one in the server. -detailed - Return a list of
	 * label types and the print quantity for each one in the database informed.
	 * 
	 * @param iniDate
	 *            Initial date of time period
	 * @param endDate
	 *            Final date of time period
	 * @param option
	 *            Output format (summarized/detailed)
	 * @return List<String> lblQty
	 * @throws DaoException
	 * @throws SQLException
	 */
	public List<String> getLblQty(String iniDate, String endDate, String option, String tgtSvrInfo)
			throws DaoException, SQLException {
		List<String> lblQty = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			if (option.equalsIgnoreCase("-resume")) {
				try {
					stmt = this.connection.prepareStatement(this.prop.getProperty("GET_SUM_QTY")
							.replaceAll("_inidate", iniDate).replaceAll("_enddate", endDate));
					rs = stmt.executeQuery();
	
					while (rs.next()) {
						String fname = rs.getString("sum_qty");
	
						lblQty.add(fname);
					}
	
				} catch (SQLException e) {
					throw new DaoException();
	
				}
			}else if (option.equalsIgnoreCase("-detailed")) {
				try {
					String dbName = "";
	
					stmt = this.connection
							.prepareStatement(this.prop.getProperty("GET_DTL_QTY").replaceAll("_inidate", iniDate)
									.replaceAll("_enddate", endDate).replaceAll("_dbname", tgtSvrInfo.split("[:]")[1]));
					rs = stmt.executeQuery();
	
					while (rs.next()) {
						if (!dbName.equalsIgnoreCase(rs.getString("database"))) {
							lblQty.add("");
							lblQty.add("Database: " + rs.getString("database"));
						}
	
						lblQty.add(
								"Label Type: " + rs.getString("label_type") + " | Quantity: " + rs.getString("quantity"));
	
						dbName = rs.getString("database");
					}
	
				} catch (SQLException e) {
					throw new DaoException();
				}
			}
		} finally {
			this.connection.close();
			//Potential resource leak removed when close stmt and rs on finally
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
		}
		return lblQty;
	}

	/**
	 * Return the list of label components that have weight.
	 * 
	 * @return List<String> comps
	 * @throws DaoException
	 * @throws SQLException
	 */
	public List<String> wordSearch(String word) throws DaoException, SQLException {
		List<String> comps = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = this.connection.prepareStatement(this.prop.getProperty("GET_WORD")
					.replaceAll("_word", word));
//			stmt.execute();
//			stmt.close();
//			
//			stmt = this.connection.prepareStatement(this.prop.getProperty("CHECK_WORD"));
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				// concatenate fields to improve the user view
				String fname = rs.getString("label_type") + "\t"
						+ String.format("%-31s", rs.getString("print_cmd_title")) + rs.getString("print_cmd_string"); 

				comps.add(fname);
			}

		} catch (SQLException e) {
			throw new DaoException();

		} finally {
			this.connection.close();
			//Potential resource leak removed when close stmt and rs on finally
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
		}

		return comps;
	}
	
	
	/**
	 * Compare Gen attribute data between LE configuration data and return the
	 * status.
	 * 
	 * @return List<String> genattr
	 * @throws SQLException
	 * @throws DaoException
	 */
	public List<String> checkGenAttr() throws SQLException, DaoException {
		List<String> genattr = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = this.connection.prepareStatement(this.prop.getProperty("CHECK_GENATTR"));
			stmt.execute();
			stmt.close();
			
			stmt = this.connection.prepareStatement(this.prop.getProperty("COMPARE_GENATTR"));
			rs = stmt.executeQuery();

			while (rs.next()) {
				// concatenate fields to improve the user view
				String gattr = rs.getString("attr_name") + "\t" + rs.getString("attr_data") + "\t"
						+ rs.getString("status");

				genattr.add(gattr);
			}

		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			this.connection.close();
			//Potential resource leak removed when close stmt and rs on finally
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
		}

		return genattr;
	}

	/**
	 * Get LE Special Functions used in the period passed as parameter 
	 * 
	 * @return List<String> functions
	 * @throws SQLException
	 * @throws DaoException
	 */
	public List<String> getUsedFunctions(String iniDate, String endDate) throws SQLException, DaoException {
		List<String> functions = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = this.connection.prepareStatement(this.prop.getProperty("GET_USED_SF")
					.replaceAll("_start_date", iniDate).replaceAll("_end_date", endDate));
			rs = stmt.executeQuery();

			while (rs.next()) {
				String func = String.format("%-58s", rs.getString("function_name")) + rs.getString("label_name");

				functions.add(func);
			}

		} catch (SQLException e) {
			throw new DaoException();
			
		} finally {
			this.connection.close();
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
		}

		return functions;
	}

	/**
	 * Return the list of label key and name that have passed word.
	 * 
	 * @return List<String> labels
	 * @throws DaoException
	 * @throws SQLException
	 */
	public List<String> wordSearchLe(String word, String dbName) throws DaoException, SQLException {
		List<String> labels = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = this.connection.prepareStatement(this.prop.getProperty("GET_WORD_LE")
					.replaceAll("_dbName", dbName).replaceAll("_word", word));
			stmt.execute();
			stmt.close();
			
			stmt = this.connection.prepareStatement(this.prop.getProperty("CHECK_WORD_LE"));
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				// concatenate fields to improve the user view
				String fname = rs.getString("label_key") + "\t" + String.format("%-31s", rs.getString("label_name")) + "\t" + rs.getString("element_name"); 

				labels.add(fname);
			}

		} catch (SQLException e) {
			throw new DaoException();

		} finally {
			this.connection.close();
			//Potential resource leak removed when close stmt and rs on finally
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
		}

		return labels;
	}

	/**
	 * Return the list of labels using functions and template files.
	 * 
	 * @return List<String> labels
	 * @throws DaoException
	 * @throws SQLException
	 */
	public List<String> CheckLabels() throws DaoException, SQLException {
		List<String> labels = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = this.connection.prepareStatement(this.prop.getProperty("CHECK_LABELS"));
			stmt.execute();
			stmt.close();
			
			stmt = this.connection.prepareStatement(this.prop.getProperty("GET_LABELS"));
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				// concatenate fields to improve the user view
				String fname = String.format("%-27s", rs.getString("label_name")) + " " +
							   String.format("%-32s", rs.getString("function_name")) + " " +
							   String.format("%-15s", rs.getString("func_installed")) + " " +
							   String.format("%-25s", rs.getString("file_template")) + " " +
							   rs.getString("file_installed");

				labels.add(fname);
			}

		} catch (SQLException e) {
			throw new DaoException();

		} finally {
			this.connection.close();
			//Potential resource leak removed when close stmt and rs on finally
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
		}
        
		return labels;
	}
	
	/**
	 * Check if all LE label functions used on current database are the same in the
	 * branch and the trunk on SVN.
	 * 
	 * @param svn_branch
	 * @param svn_trunk
	 * @param username
	 * @param password
	 * @return List<String> funcList
	 * @throws DaoException
	 * @throws SQLException
	 */
	public List<String> checkFuncLE(String svn_branch, String svn_trunk, String username, String password)
			throws DaoException, SQLException {

		List<String> funcList = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = this.connection.prepareStatement(this.prop.getProperty("COMPARE_SVN_PATH_LE")
					.replaceAll("_branchpath", svn_branch).replaceAll("_trunkpath", svn_trunk)
					.replaceAll("_user", username).replaceAll("_pass", password));
			stmt.execute();
			stmt.close();
			
			stmt = this.connection.prepareStatement(this.prop.getProperty("GET_SVN_PATH"));
			rs = stmt.executeQuery();

			while (rs.next()) {
				// concatenate fields to improve the user view
				String fname = String.format("%-58s", rs.getString("function_name"))
						+ String.format("%-12s", rs.getString("branch_size")) + rs.getString("trunk_size");

				funcList.add(fname);
			}

		} catch (SQLException e) {
			throw new DaoException();

		} finally {
			this.connection.close();
			//Potential resource leak removed when close stmt and rs on finally
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
		}

		return funcList;
	}
}
