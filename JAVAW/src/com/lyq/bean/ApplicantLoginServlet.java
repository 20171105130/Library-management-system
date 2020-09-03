package com.lyq.bean;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lyq.bean.Applicant;
import com.lyq.bean.ApplicantDAO;
import com.lyq.bean.DBUtil;

/**
 * Servlet implementation class ApplicantLoginServlet
 */
@WebServlet("/ApplicantLoginServlet")
public class ApplicantLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplicantLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		// 获取请求参数
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		// 登录验证
		ApplicantDAO dao = new ApplicantDAO();
		int applicantID = dao.login(email,password);
		if(applicantID != 0 ){
			Applicant applicant = new Applicant(applicantID, email, password);
			request.getSession().setAttribute("SESSION_APPLICANT", applicant);
			response.sendRedirect("index.jsp");
		}else{
			// 用户登录信息错误，进行错误提示
			out.print("<script type='text/javascript'>");
			out.print("alert('用户名或密码错误，请重新输入！');");
			out.print("window.location='Login.jsp';");
			out.print("</script>");
		}
	}

	}


