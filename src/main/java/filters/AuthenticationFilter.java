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

@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
//
//		String requestUrl = request.getRequestURI();
//
//		HttpSession session = request.getSession(false);
//		System.out.println("entering filter");
//
//		if (requestUrl.endsWith(".css")) {
//			chain.doFilter(request, response);
//			System.out.println("css approved");
//			return;
//		} else if (requestUrl.endsWith("/") || requestUrl.endsWith("Cart") || requestUrl.endsWith("Details")
//				|| requestUrl.endsWith("Login") || requestUrl.endsWith("Registration") || requestUrl.endsWith("Profile")
//				|| requestUrl.endsWith("Home") || requestUrl.endsWith("Product") || requestUrl.endsWith("Logout")
//				|| requestUrl.endsWith("AddProduct") || requestUrl.endsWith("Orders") || requestUrl.endsWith("admin")
//				|| requestUrl.endsWith("Delete") || requestUrl.endsWith("Edit") || requestUrl.endsWith("Update")
//				|| requestUrl.endsWith("VProduct") || requestUrl.endsWith("User")) {
//			System.out.println("page enter");
//			if (session == null) {
//				request.getRequestDispatcher("Login").forward(request, response);
//			} else if (session.getAttribute("username") != null) {
//				System.out.println(" logged in");
//				chain.doFilter(request, response);
//			}
//		}
//
//		else if (session.getAttribute("username") == null) {
//			if (requestUrl.endsWith("Profile") || requestUrl.endsWith("Logout") || requestUrl.endsWith("Admin")) {
//				response.sendRedirect(request.getContextPath() + "/Login");
//				System.out.println("Not logged in");
//
//			}
//
//			else {
//
//				chain.doFilter(request, response);
//			}
//		} else
//
//		{
//
//			request.getRequestDispatcher("Error").forward(request, response);
//
//		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
