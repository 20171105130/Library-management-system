package com.lyq.bean;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lyq.bean.Book;
import com.lyq.bean.BookDao;
/**
 * Servlet implementation class FindPage
 */
@WebServlet("/FindPage")
public class FindPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
//		System.out.println("aaaaaa");
		request.getRequestDispatcher("book_list.jsp").forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
