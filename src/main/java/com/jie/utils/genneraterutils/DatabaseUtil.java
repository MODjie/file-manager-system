package com.jie.utils.genneraterutils;

import com.jie.domain.codegenerater.ColunmModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatabaseUtil {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://192.168.0.173:3306/4.1_dev_jlt_saas?useUnicode=true&characterEncoding=utf8";
	private static final String USERNAME = "fmt_root";
	private static final String PASSWORD = "sino123456";

	private static final String SQL = "SELECT * FROM ";// 数据库操作

	private static Pattern linePattern = Pattern.compile("_(\\w)");

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取数据库连接
	 *
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 关闭数据库连接
	 * @param conn
	 */
	public static void closeConnection(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取数据库下的所有表名
	 */
	public static List<String> getTableNames() {
		List<String> tableNames = new ArrayList<>();
		Connection conn = getConnection();
		ResultSet rs = null;
		try {
			//获取数据库的元数据
			DatabaseMetaData db = conn.getMetaData();
			//从元数据中获取到所有的表名
			rs = db.getTables(null, null, null, new String[] { "TABLE" });
			while(rs.next()) {
				tableNames.add(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				closeConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tableNames;
	}

	/**
	 * 获取表中所有字段名称
	 * @param tableName 表名
	 * @return
	 */
	public static List<String> getColumnNames(String tableName) {
		List<String> columnNames = new ArrayList<>();
		//与数据库的连接
		Connection conn = getConnection();
		PreparedStatement pStemt = null;
		String tableSql = SQL + tableName;
		try {
			pStemt = conn.prepareStatement(tableSql);
			//结果集元数据
			ResultSetMetaData rsmd = pStemt.getMetaData();
			//表列数
			int size = rsmd.getColumnCount();
			for (int i = 0; i < size; i++) {
				columnNames.add(rsmd.getColumnName(i + 1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pStemt != null) {
				try {
					pStemt.close();
					closeConnection(conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return columnNames;
	}

	/**
	 * 获取表中所有字段类型
	 * @param tableName
	 * @return
	 */
	public static List<String> getColumnTypes(String tableName) {
		List<String> columnTypes = new ArrayList<>();
		//与数据库的连接
		Connection conn = getConnection();
		PreparedStatement pStemt = null;
		String tableSql = SQL + tableName;
		try {
			pStemt = conn.prepareStatement(tableSql);
			//结果集元数据
			ResultSetMetaData rsmd = pStemt.getMetaData();
			//表列数
			int size = rsmd.getColumnCount();
			for (int i = 0; i < size; i++) {
				columnTypes.add(rsmd.getColumnTypeName(i + 1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pStemt != null) {
				try {
					pStemt.close();
					closeConnection(conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return columnTypes;
	}

	/**
	 * 获取表中字段的所有注释
	 * @param tableName
	 * @return
	 */
	public static List<String> getColumnComments(String tableName) { List<String> columnTypes = new ArrayList<>();
		//与数据库的连接
		Connection conn = getConnection();
		PreparedStatement pStemt = null;
		String tableSql = SQL + tableName;
		List<String> columnComments = new ArrayList<>();//列名注释集合
		ResultSet rs = null;
		try {
			pStemt = conn.prepareStatement(tableSql);
			rs = pStemt.executeQuery("show full columns from " + tableName);
			while (rs.next()) {
				columnComments.add(rs.getString("Comment"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
					closeConnection(conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return columnComments;
	}

	/**
	 * 获取表结构并存储在PropertyModel里
	 * @return
	 */
	public static List<ColunmModel> getPropertyModels(String tableName){

		List<ColunmModel> colunmModels = new ArrayList<>();

		Connection conn = getConnection();
		PreparedStatement pStemt = null;
		ResultSet rs = null;
		String tableSql = SQL + tableName;
		try {
			pStemt = conn.prepareStatement(tableSql);
			//结果集元数据
			ResultSetMetaData rsmd = pStemt.getMetaData();
			List<String> columnComments = new ArrayList<>();//列名注释集合
			rs = pStemt.executeQuery("show full columns from " + tableName);
			while (rs.next()) {
				columnComments.add(rs.getString("Comment"));
			}
			//表列数
			int size = rsmd.getColumnCount();
			for (int i = 0; i < size; i++) {
				ColunmModel colunmModel = new ColunmModel();
				colunmModel.setPropertyName(rsmd.getColumnName(i + 1));
				colunmModel.setPropertyType(rsmd.getColumnTypeName(i + 1));
				colunmModel.setNotes(columnComments.get(i));
				colunmModels.add(colunmModel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
					closeConnection(conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pStemt != null) {
				try {
					pStemt.close();
					closeConnection(conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return colunmModels;
	}

	/**
	 * 转换数据库类型为java类型
	 */
	public static void convertDbTypeToJava(ColunmModel colunmModel){
		switch (colunmModel.getPropertyType()){
			case "VARCHAR" :
				colunmModel.setPropertyType("String");
				break;
			case "BIGINT" :
				colunmModel.setPropertyType("Long");
				break;
			case "DECIMAL" :
				colunmModel.setPropertyType("String");
				break;
			case "INT" :
				colunmModel.setPropertyType("Integer");
				break;
			case "TINYINT" :
				colunmModel.setPropertyType("Integer");
				break;
			case "CHAR" :
				colunmModel.setPropertyType("String");
				break;
			case "INTEGER" :
				colunmModel.setPropertyType("Long");
				break;
			case "SMALLINT" :
				colunmModel.setPropertyType("Integer");
				break;
			case "MEDIUMINT" :
				colunmModel.setPropertyType("Integer");
				break;
			case "FLOAT" :
				colunmModel.setPropertyType("float");
				break;
			case "DOUBLE" :
				colunmModel.setPropertyType("double");
				break;
			case "DATE" :
				colunmModel.setPropertyType("String");
				break;
			case "DATETIME" :
				colunmModel.setPropertyType("String");
				break;
		}
	}
	/**
	 * 下划线转驼峰
	 * */
	public static String lineToHump(String str){
		str = str.toLowerCase();
		Matcher matcher = linePattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while(matcher.find()){
			matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
}
