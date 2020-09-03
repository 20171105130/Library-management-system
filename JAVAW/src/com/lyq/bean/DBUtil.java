package com.lyq.bean;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	static String name="root";
	static String password="123456";
	static String url ="jdbc:mysql://localhost:3306/db_database10?characterEncoding=UTF-8";
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
public static Connection getConnection(){
	Connection conn=null;
	try {
		conn=DriverManager.getConnection(url, name, password);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return conn;
}

public static void closeJDBC(ResultSet result, Statement stmt, Connection conn) {
	// TODO Auto-generated method stub
	if(result!=null){
		try {
			result.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	if(stmt!=null){
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	if(conn!=null){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public static void runTest() throws SQLException,IOException{
	Connection conn=getConnection();
	Statement stmt =conn.createStatement();
	ResultSet result = stmt.executeQuery("select * from tb_company");
	while (result.next()){
		System.out.print(result.getString(2)+"    ");
		System.out.print(result.getString(3)+"    ");
		System.out.print(result.getString(5)+"    ");
		System.out.println(result.getString(6));
	}
	closeJDBC(result,stmt,conn);
}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			runTest();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
