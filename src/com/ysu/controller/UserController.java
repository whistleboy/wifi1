package com.ysu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.mbeans.UserMBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ysu.dao.UserDao;
import com.ysu.model.UserBean;
import com.ysu.util.Constants;
import com.ysu.util.Json;




//@Repository
//@Service
@Controller
@RequestMapping("/user/")
//1212321312
@SessionAttributes(value="username")
public class UserController {
	
	@Autowired
	private UserDao ud;

	@RequestMapping("login.action")
	public ModelAndView login(String userName,String pwd,HttpServletRequest request){
		System.out.println(userName);
		System.out.println(pwd);
		//UserDao ud=new UserDao();
		UserBean userBean=new UserBean();
		boolean result=ud.login(userName, pwd);
		ModelAndView mv=new ModelAndView();
		if(result){
			mv.addObject("str", "success");
			mv.setViewName("index");
			userBean.setUserId(ud.getIdByName(userName));
			userBean.setUserName(userName);
			userBean.setUserPassWord(pwd);
		}
		else{
			mv.addObject("str", "defeated");
			mv.setViewName("login");
		}
		//mv.addObject("userBean", userName);
		request.getSession().setAttribute(Constants.SESSION_USER_BEAN, userBean);
		System.out.println(userBean);
		mv.addObject("username", userName);

		
		return mv;//代表的是，跳转的视图名   /success.jsp
	}
	@RequestMapping("register.action")
	public ModelAndView register(String username,String password){
		//System.out.println(u);
		//UserDao ud=new UserDao();
		boolean result=ud.insert(username,password);
		
		ModelAndView mv=new ModelAndView();
		mv.addObject("message", username);
		mv.addObject("str1", "success");
		mv.setViewName("sign-up3");
		
		return mv;
	}
	@RequestMapping("checkName.action")
	@ResponseBody
	public String checkName(String username){
		System.out.println(username);
		//UserDao ud=new UserDao();

		boolean result=ud.checkName(username);
		System.out.println(result);
		if(result==false){
		return "success";
		}
		else{
			return "error";
		}
	}
}
