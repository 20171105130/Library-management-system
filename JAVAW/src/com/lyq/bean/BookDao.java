package com.lyq.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
public Connection getConnection(){
		
		Connection conn = null;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/db_database10";
			
			String username = "root";
			
			String password = "123456";
			
			conn = DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
}
		public List<Book> find(int page){
			// 创建List
			List<Book> list = new ArrayList<Book>();
			// 获取数据库链接
			Connection conn = getConnection();
			// 分页查询语句
			String sql = "select * from tb_books order by id desc limit ?,?";
			try {
				// 获取PreparedStatement
				PreparedStatement ps = conn.prepareStatement(sql);
				// 对SQ语句第一个参数赋值（查询起始位置）
				ps.setInt(1, (page - 1) * Book.PAGE_SIZE);
				// 对SQ语句第二个参数赋值（每一页记录数）
				ps.setInt(2, Book.PAGE_SIZE);
			
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()){
					
					Book p = new Book();
					
					p.setId(rs.getInt("id"));
					
					p.setName(rs.getString("name"));
					
					
					p.setPrice(rs.getDouble("price"));
					
					p.setBookCount(rs.getInt("bookCount"));
					
					p.setAuthor(rs.getString("author"));
					
					list.add(p);
				}
				
				rs.close();
				
				ps.close();
				
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
		

		public int findCount(){
			
			int count = 0;
			Connection conn = getConnection();
			String sql = "select count(*) from tb_books";
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				if(rs.next()){
					count = rs.getInt(1);
				}
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return count;
		}

}
