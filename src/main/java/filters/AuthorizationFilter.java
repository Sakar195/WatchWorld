package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class Authorization
 */
@WebFilter("/*")
public class AuthorizationFilter implements Filter {

	public void destroy() {
		// Optional: Cleanup resources here, if needed
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

//		HttpSession session = request.getSession(false);
//		Integer role_id;
//
//		if (session != null) {
//			role_id = (Integer) session.getAttribute("role_id");
//			if (role_id == 1) { // Admin
//				handleAdminRequest(request, response);
//			} else if (role_id == 2) { // User
//				handleUserRequest(request, response);
//			} else {
//				response.sendError(HttpServletResponse.SC_FORBIDDEN, "Unauthorized access");
//				return;
//			}
//		}
//
//		
//	}
//
//	private void handleAdminRequest(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String reqUrl = request.getRequestURI();
//		if (reqUrl.endsWith("VProduct") || reqUrl.endsWith("Edit") || reqUrl.endsWith("Update")) {
//			request.getRequestDispatcher(reqUrl).forward(request, response);
//			return;
//		}
//
//		if (!reqUrl.endsWith("admin")) {
//			response.sendRedirect(request.getContextPath() + "/admin");
//			return;
//		}
//
//		request.getRequestDispatcher("admin").forward(request, response);
//	}
//
//	private void handleUserRequest(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String reqUrl = request.getRequestURI();
//		if (reqUrl.endsWith("Logout") || reqUrl.endsWith("Home")) {
//			request.getRequestDispatcher(reqUrl).forward(request, response);
//			return;
//		}
//
//		if (!reqUrl.endsWith("Profile")) {
//			response.sendRedirect(request.getContextPath() + "/Profile");
//			return;
//		}
//
//		request.getRequestDispatcher("Profile").forward(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// Optional: Initialization code, if needed
	}
}
