package bvv.util.mailLog;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;

import bvv.web.hibernate.DaoBean;


public class MyServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4994686112729626359L;
	
	//@Autowired
	 // private B B;
	
	String s = "";
	
	@Override
	public void init(ServletConfig servletConfig) throws ServletException  {
        WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletConfig.getServletContext());
        B B = (B)applicationContext.getBean("B", B.class);

		super.init(servletConfig);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);		    
		    
			s= servletConfig.getInitParameter("s");
			  if (s == null) {
			    s = "Hello";
			  }
		  System.out.println("MyServlet INIT - "+s);
	}
	
	public void service(HttpServletRequest req,
		       HttpServletResponse res) throws IOException
		{

		
			System.out.println("MyServlet");
		
		}

}
