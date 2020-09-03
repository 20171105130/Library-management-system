package com.lyq.bean;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FindServlet
 */
@WebServlet("/FindServlet")
public class FindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/db_database10";
		
			String username = "root";
			
			String password = "123456";
			
			Connection conn = DriverManager.getConnection(url,username,password);
			
			Statement stmt = conn.createStatement();
			
			String sql = "select * from tb_books";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			List<Book> list = new ArrayList<Book>();
			
			while(rs.next()){
				
				Book book = new Book();
				
				book.setId(rs.getInt("id"));
				
				book.setName(rs.getString("name"));
				
				book.setPrice(rs.getDouble("price"));
				
				book.setBookCount(rs.getInt("bookCount"));
				
				book.setAuthor(rs.getString("author"));
				
				list.add(book);
			}
			
			request.setAttribute("list", list);
			rs.close();		
			stmt.close();	
			conn.close();	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		int currPage = 1;
		if(request.getParameter("page") != null){
			currPage = Integer.parseInt(request.getParameter("page"));
		}
		BookDao dao = new BookDao();
		List<Book> list = dao.find(currPage);
		request.setAttribute("list", list);
		int pages ;
		int count = dao.findCount();
		if(count % Book.PAGE_SIZE == 0){
			pages = count / Book.PAGE_SIZE;
		}else{
			pages = count / Book.PAGE_SIZE + 1;
		}
		StringBuffer sb = new StringBuffer();
		for(int i=1; i <= pages; i++){
			if(i == currPage){
				sb.append(" " + i + " ");
			}else{
				sb.append("<a href='FindPage?page=" + i + "'>" + i + "</a>");
			}
			sb.append(" ");
		}
		request.setAttribute("bar", sb.toString());
		System.out.println("aaaaaa");
		//request.getRequestDispatcher("book_list.jsp").forward(request, response);
		
		
		
		request.getRequestDispatcher("book_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

}
