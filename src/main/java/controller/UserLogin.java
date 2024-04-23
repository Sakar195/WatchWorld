
package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.UserDao;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Login" })
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao = new UserDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher(appConstant.MyConstants.LOGIN_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String loginClick=request.getParameter("login-button");
		boolean isLogin=false;
		if(loginClick!=null)
		{
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			
			
			try {
				isLogin = userDao.userLogin(username,password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(isLogin)
			{
				HttpSession session=request.getSession();
				session.setMaxInactiveInterval(3*60);
				session.setAttribute("username", username);
				request.getRequestDispatcher("profile").forward(request, response);
			}
			else
			{
				request.setAttribute("error", "invalid username or password");
				
			}
		}
		doGet(request, response);
	}

}
