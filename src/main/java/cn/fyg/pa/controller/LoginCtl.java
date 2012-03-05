package cn.fyg.pa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.fyg.pa.bpmn.MyTask;
import cn.fyg.pa.page.LoginPage;
import cn.fyg.pa.page.LoginRet;
import cn.fyg.pa.service.PersonService;
import cn.fyg.pa.tool.Dispatcher;
import cn.fyg.pa.tool.SessionUtil;




@Controller
@RequestMapping("/login")
public class LoginCtl {

	private static final Logger logger = LoggerFactory.getLogger(LoginCtl.class);
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private MyTask task;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView getLogin() {
		logger.info("getLogin");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		task.init();
		mav.addObject("task",task);

		return mav;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ModelAndView postLogin(LoginPage loginPage,HttpServletRequest request, HttpServletResponse response) {
		logger.info("postLogin");
		
		LoginRet loginRet=personService.checkLoginPerson(loginPage);
		
		if(loginRet.isPass()){
			new SessionUtil(request).setValue("loginRet",loginRet);
			return dispatcherMav(loginRet);
		}
				
		return reLoginMav(loginPage);
	}

	private ModelAndView dispatcherMav(LoginRet loginRet) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName(Dispatcher.dispatcher(loginRet));
		return mav;
	}
	
	private ModelAndView reLoginMav(LoginPage loginPage) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("login");
		mav.addObject("loginPage",loginPage);
		mav.addObject("msg","用户名或者密码错误!");
		return mav;
	}

}
