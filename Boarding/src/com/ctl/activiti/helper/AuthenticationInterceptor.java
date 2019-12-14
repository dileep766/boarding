package com.ctl.activiti.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ctl.activiti.login.LoggedInUser;

@Component
public class AuthenticationInterceptor implements  HandlerInterceptor  
{
 
@Override
public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1,
		Object arg2, Exception arg3) throws Exception {
	// TODO Auto-generated method stub
	
}

@Override
public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
		Object arg2, ModelAndView arg3) throws Exception {
	// TODO Auto-generated method stub
	
}

@Override
public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
		Object arg2) throws Exception {
	 LoggedInUser user = (LoggedInUser) request.getSession().getAttribute("user");
	 String uri = request.getRequestURI();
	 System.out.println("the uri"+uri);
	 System.out.println("inside interceptor");
	 if(!uri.endsWith("/") && !uri.endsWith("logout") && !uri.endsWith("/Boarding/") && !uri.endsWith("/Boarding") && !uri.endsWith("/exitClearenceForm") && !uri.endsWith("/login"))
	  {
	 if(user==null || user.equals(null))
  {
   
    response.sendRedirect("/Boarding/logout");
    return false;
  
  }
	  }
  return true;
}
}
