package com.ctl.activiti.controller;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.activiti.engine.FormService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.User;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ctl.activiti.helper.CreateUser;
import com.ctl.activiti.helper.UserInfo;
import com.ctl.activiti.login.DefaultLoginHandler;
import com.ctl.activiti.login.LoggedInUser;
import com.ctl.activiti.login.LoggedInUserImpl;
@Controller
public class LoginController {
	private static final Logger logger = Logger.getLogger(LoginController.class);
	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	@Qualifier("taskService")
	private TaskService taskService;
	
	@Autowired
	@Qualifier("taskServicewithoutldap")
	private TaskService taskServicewithoutldap;
	@Autowired
	@Qualifier("processEngine")
	private ProcessEngineFactoryBean processEngine;
	@Autowired
	RepositoryService repositoryService;
	@Autowired
	@Qualifier("identityService")
	IdentityService identityService;
	@Autowired
	DefaultLoginHandler defaultLoginHandler;
	@Autowired
	FormService formService;
	@Autowired
	@Qualifier("identityServicewithoutldap")
	IdentityService identityServicewithoutldap;
	
	/*@ExceptionHandler(Exception.class)
    public ModelAndView handleEmployeeNotFoundException(Exception exception,HttpSession session){
		ModelAndView model = new ModelAndView("login");
		LoggedInUser user = (LoggedInUser) session.getAttribute("user");

		defaultLoginHandler.logout(user);
		session.removeAttribute("user");
		session.invalidate();
		UserInfo userinfo = new UserInfo();
		model.addObject("userForm", userinfo);
		return model;
    }   */

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String viewLogin(Map<String, Object> model)  throws Exception{
		UserInfo user = new UserInfo();
		model.put("userForm", user);
		return "login";
	}
	@RequestMapping(value = "/loginother", method = RequestMethod.GET)
	public String viewLoginOther(Map<String, Object> model)  throws Exception{
		UserInfo user = new UserInfo();
		model.put("userForm", user);
		return "loginother";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView doLogin(@ModelAttribute("userForm") UserInfo userForm,@RequestParam(value = "error", required = false) String error,
			 HttpSession session)  throws Exception {
		ModelAndView model = null;
		LoggedInUser user=null;
	
		try {
			logger.info("error value======"+error);
			//isValidUser(userForm.getEmail(), userForm.getPassword());
			user = defaultLoginHandler.authenticate(
					userForm.getEmail(), userForm.getPassword());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
logger.error("exception==="+e);
			model = new ModelAndView("login");
			UserInfo userinfo = new UserInfo();
			model.addObject("userForm", userinfo);
				model.addObject("error", "Invalid Credentials!");
			

			
				
		}
	
		
		if (user == null) {
			if (identityServicewithoutldap.checkPassword(userForm.getEmail(), userForm.getPassword())) {
				
				 User usernonldap = identityServicewithoutldap.createUserQuery().userId(userForm.getEmail()).singleResult();
				 user=new LoggedInUserImpl(usernonldap,userForm.getPassword());
				 userForm = null;
				session.setAttribute("user", user);
				session.setAttribute("ldapuser", "false");
				model = new ModelAndView("redirect:taskList");
				return model;
				
			} else {
				
				model = new ModelAndView("login");
				UserInfo userinfo = new UserInfo();
				model.addObject("userForm", userinfo);
				model.addObject("error", "Invalid Credentials!");
				userForm = null;
				return model;
			}
				
		} else {
			userForm = null;
			session.setAttribute("user", user);
			session.setAttribute("ldapuser", "true");
			
			model = new ModelAndView("redirect:taskList");
			return model;
		}

	}
	@RequestMapping(value = "/loginother", method = RequestMethod.POST)
	public ModelAndView doLoginOther(@ModelAttribute("userForm") UserInfo userForm,@RequestParam(value = "error", required = false) String error,
			 HttpSession session)  throws Exception {
		ModelAndView model = new ModelAndView();
		/*
		try {
			logger.info("error value======"+error);
			//isValidUser(userForm.getEmail(), userForm.getPassword());
			identityServicewithoutldap.checkPassword(
					userForm.getEmail(), userForm.getPassword());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
logger.error("exception==="+e);

		
				model.addObject("error", "Invalid Credentials!");
			

			
			model.setViewName("login");
		}
	*/
		
		if (!identityServicewithoutldap.checkPassword(userForm.getEmail(), userForm.getPassword())) {
			model.addObject("error", "Invalid Credentials!");
		

			
			model.setViewName("loginother");
			userForm = null;
			return model;
		} else {
			 User usernonldap = identityServicewithoutldap.createUserQuery().userId(userForm.getEmail()).singleResult();
			LoggedInUser user=new LoggedInUserImpl(usernonldap,userForm.getPassword());
			 
			session.setAttribute("user", user);
			model.setViewName("taskList");
			return model;
		}

	}
	
	private boolean isValidUser(String ldapUsername, String ldapPassword)  throws Exception{
		boolean isValidUser = false;
		final String ldapAdServer = "ldaps://ldap.qintra.com:1636";
        final String ldapSearchBase = "uid="+ldapUsername+",ou=People,dc=mnet,dc=qintra,dc=com";
        Hashtable<String, Object> env = new Hashtable<String, Object>();
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        if(ldapUsername != null) {
            env.put(Context.SECURITY_PRINCIPAL, ldapSearchBase);
        }
        if(ldapPassword != null) {
            env.put(Context.SECURITY_CREDENTIALS, ldapPassword);
        }
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, ldapAdServer);
        DirContext  ctx = new InitialDirContext(env);
        logger.info(ctx);
		return isValidUser;
		
	}
	@RequestMapping(value = "/createUser", method = RequestMethod.GET)
	public String createUser(Map<String, Object> model)  throws Exception{
		HashMap<String, Object> data = new HashMap<String, Object>();
		CreateUser user = new CreateUser();
		model.put("user", user);
		return "createUser";
		

	}
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public ModelAndView createUser(@ModelAttribute("user") CreateUser user, HttpSession session)  throws Exception{
		ModelAndView modelview = null;
		   try {
			   
			User newUser = (User) identityServicewithoutldap.newUser(user.getFirstName());
			newUser.setId(user.getVendorName()); 
			newUser.setEmail(user.getEmail());
			   newUser.setFirstName(user.getFirstName());
			   newUser.setLastName(user.getLastName());
			   newUser.setPassword(user.getPassword());
			   identityServicewithoutldap.saveUser(newUser);
			
			UserInfo user2 = new UserInfo();
			
			modelview = new ModelAndView("sucess");
			modelview.addObject("message", "Vendor Profile Has Been Created Sucess Fully");
			return modelview;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
logger.error("exception==="+e);
			modelview = new ModelAndView("createUser");
			
			modelview.addObject("error", "Vendor Profile with Same Vendor Name already Exists!");
			return modelview;
		}
		
		

	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView dologout(HttpSession session)
			 throws Exception
	{
		ModelAndView model = new ModelAndView("login");
		LoggedInUser user = (LoggedInUser) session.getAttribute("user");

		defaultLoginHandler.logout(user);
		session.removeAttribute("user");
		session.invalidate();
		UserInfo userinfo = new UserInfo();
		model.addObject("userForm", userinfo);
		return model;

	}
}
