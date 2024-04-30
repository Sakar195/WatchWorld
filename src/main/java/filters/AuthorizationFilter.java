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

/**
 * Servlet Filter implementation class Authorization
 */
@WebFilter("/AuthorizationFilter")
public class AuthorizationFilter implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
//		String reqUrl = request.getRequestURI();
//		
//		Integer role_id = (Integer) request.getSession().getAttribute("role_id");
//		if (role_id != null && role_id == 1) {
//
//			if (reqUrl.endsWith("VProduct")) {
//				
//				request.getRequestDispatcher("VProduct").forward(request, response);
//			}
//			else if (reqUrl.endsWith("Edit")) {
//			
//				request.getRequestDispatcher("Edit").forward(request, response);
//			}
//			else if (reqUrl.endsWith("Update")) {
//				
//				request.getRequestDispatcher("Update").forward(request, response);
//			}
//			else if (!reqUrl.endsWith("admin")) {
//				
//				response.sendRedirect(request.getContextPath() + "/admin");
//			}
//			else
//			{
//				request.getRequestDispatcher("admin").forward(request, response);
//			}
//		} 
//		else if (role_id != null && role_id==2) 
//		{
//			if (reqUrl.endsWith("Logout"))
//			{
//				request.getRequestDispatcher("Logout").forward(request, response);
//			}
//			else if (reqUrl.endsWith("Home"))
//			{
//				request.getRequestDispatcher("Home").forward(request, response);
//			}
//			else if (!reqUrl.endsWith("Profile")) {
//
//				response.sendRedirect(request.getContextPath() + "/Profile");
//			}
//			else 
//			{
//
//				request.getRequestDispatcher("Profile").forward(request, response);
//			}
//			
//		}
//		else
//
//		{
//			chain.doFilter(request, response);
//		}
		chain.doFilter(request, response);
		}
		
		

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
