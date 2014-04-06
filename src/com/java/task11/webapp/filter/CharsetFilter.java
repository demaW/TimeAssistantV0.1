package com.java.task11.webapp.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class CharsetFilter
 */
@WebFilter("/CharsetFilter")
public class CharsetFilter implements Filter {

	private String encoding;

	   public void init(FilterConfig config) throws ServletException
	   {
	    encoding = config.getInitParameter("requestEncoding");

	    if( encoding==null ) encoding="UTF-8";
	   }

	   public void doFilter(ServletRequest request, ServletResponse response, FilterChain       next)
	   throws IOException, ServletException
	   {
<<<<<<< 03de9564aa2a01ee2979b0c85fa2546ee15f6065
	   
=======
	    // Respect the client-specified character encoding
	    // (see HTTP specification section 3.4.1)
>>>>>>> 49dc31138aa78094f07b82ca218bf38f24271542
	    if(null == request.getCharacterEncoding())
	      request.setCharacterEncoding(encoding);


	    /**
	 * Set the default response content type and encoding
	 */
	 response.setContentType("text/html; charset=UTF-8");
	 response.setCharacterEncoding("UTF-8");


	    next.doFilter(request, response);
	   }

	    public void destroy(){}

}
