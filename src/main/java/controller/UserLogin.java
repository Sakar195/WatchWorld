
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import appConstant.MyConstants;
import service.UserDao;
import utils.DatabaseConnectivity;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Login" })
public class UserLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDao = new UserDao();
    }

    public UserLogin() {
        super();
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(MyConstants.LOGIN_PAGE).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String loginClick = request.getParameter("login-button");

        if (loginClick == null) {
            doGet(request, response);
            return;
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            List<Integer> userDetails = userDao.userLogin(username, password);
            int login_value = userDetails.get(0);
            int role_id = userDetails.get(1);
            int id = userDetails.get(2);

            if (login_value == 1) { // Successful login
                HttpSession session = request.getSession();
                session.setAttribute("userId", id);
                session.setAttribute("username", username);
                session.setAttribute("role_id", role_id);
                session.setAttribute("login_value", login_value);
                session.setMaxInactiveInterval(30 * 60);

                if (role_id == 1) { // Admin
                    response.sendRedirect(request.getContextPath() + "/Profile");
                    return;
                } else if (role_id == 2) { // User
                    response.sendRedirect(request.getContextPath() + "/Home");
                    return;
                }
            } else if (login_value == 0) { // Incorrect password
                request.setAttribute("error", "Invalid password");
                request.setAttribute("username", username);
                doGet(request, response);
                return;
            } else { // Account doesn't exist
                request.setAttribute("error", "Account Doesn't exist");
                doGet(request, response);
                return;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            if (!response.isCommitted()) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error during login");
            }
        }
    }
}

