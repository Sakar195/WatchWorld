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

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);

		String requestUrl = request.getRequestURI();

		// If the request is for a static resource, proceed
		if (requestUrl.endsWith(".css") || requestUrl.endsWith(".js") || requestUrl.endsWith(".png")
				|| requestUrl.endsWith(".jpg") || requestUrl.endsWith(".gif")) {
			chain.doFilter(req, res); // Proceed without additional checks
			return;
		}

		// URLs that don't require authentication
		String[] publicUrls = { "/", "/Login", "/Home", "/Product", "/Details", "/Registration" };

		boolean isPublicUrl = false;
		for (String url : publicUrls) {
			if (requestUrl.contains(url)) {
				isPublicUrl = true;
				break;
			}
		}

		if (isPublicUrl) {
			chain.doFilter(req, res); // Let through
			return;
		}

		// For protected URLs, check if user is logged in
		if (session == null || session.getAttribute("username") == null) {
			request.getRequestDispatcher("Login").forward(request, response);
			response.sendRedirect(request.getContextPath() + "/Login"); // Redirect to login if not logged in
			return;
		}

		chain.doFilter(req, res); // Continue the chain for authenticated users
	}

//@Override
//	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
//			throws IOException, ServletException {
//		HttpServletRequest request = (HttpServletRequest) req;
//		HttpServletResponse response = (HttpServletResponse) res;
//
//		String requestUrl = request.getRequestURI();
//		HttpSession session = request.getSession(false);
//		if (requestUrl.endsWith(".css") || requestUrl.endsWith(".js") || requestUrl.endsWith(".png")
//				|| requestUrl.endsWith(".jpg")) {
//			System.out.println("sakar1");
//			chain.doFilter(request, response);
//			return;
//		}
//
//		// URLs that requires to be logged in
//		String[] Urls = { "/Logout", "/Profile", "/admin", "/CPassword", "/AddProduct", "/Orders", "/Delete", "/Edit",
//				"/Update", "/VProduct", "/User" };
//
//		String[] publicUrls = { "/", "/Login", "/Home", "/Product", "/Details", "/Registration" };

	// Not allowin the urls that require login
//			for (String url : Urls) {
//				if (requestUrl.contains(url)) {
//					if (session == null) {
//
//						response.sendRedirect(request.getContextPath() + "/Login");
//						return;
//					}
//
//					else if (session.getAttribute("username") != null) {
//
//						chain.doFilter(request, response);
//					}
//				}
//			}
//
//		// allowing public pages
//		if (session == null || session.getAttribute("username") == null) {
//
//			for (String url : publicUrls) {
//				if (requestUrl.endsWith(url)) {
//					response.sendRedirect(request.getContextPath() + url);
//					return;
//				} else {
//					response.sendRedirect(request.getContextPath() + "/Login");
//				}
//			}
//		}
//
//		else {
//			request.getRequestDispatcher("Error").forward(request, response);
//		}
//
//}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
