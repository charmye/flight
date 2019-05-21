package com.hc.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hc.pojo.Aircraft;
import com.hc.pojo.Identification;
import com.hc.pojo.Response;
import com.hc.pojo.User;
import com.hc.service.impl.UserServiceImpl;

@Controller
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	
	@ResponseBody
	@PostMapping(value="go")
	public String go() {
		return "aaa";
	}
	
	@GetMapping("register")
	public String register() {
		return "home/register";
	}
	@GetMapping("login")
	public String login() {
		return "home/login";
	}
	
	@PostMapping("doRegist")
	public ModelAndView doRegister(HttpServletRequest request,User user,MultipartFile photo) {
		ModelAndView mav = new ModelAndView();
		if(user==null&&photo==null) {
			mav.addObject("mse", "注册失败");
			mav.setViewName("home/register");
			return mav;
		}
		String fileName = photo.getOriginalFilename();
		user.setFileurl("/images/"+fileName);
		if(userServiceImpl.register(user)) {
			String path = request.getServletContext().getRealPath("/images/");
			File filePath = new File(path,fileName);
			if(!filePath.getParentFile().exists()) {
				filePath.getParentFile().mkdirs();
			}
			try {
				photo.transferTo(new File(path+File.separator+fileName));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			mav.setViewName("home/login");
		}else {
			mav.addObject("mse", "注册失败");
			mav.setViewName("home/register");
		}
		return mav;
	}
	
	@RequestMapping(value="/doLogin",method=RequestMethod.POST)
	public ModelAndView userLogin(User user,HttpServletRequest request) {
		User u = userServiceImpl.login(user);
		ModelAndView mav = new ModelAndView();
		if(u!=null&&u.getUserId()!=null&&u.getFileurl()!=null) {
			request.getSession().setAttribute("user", u);
			mav.setViewName("redirect:/index");
		} else {
			mav.addObject("mse", "登录失败" );
			mav.setViewName("home/login");
		}
		return mav;
	}
	@RequestMapping(value="/doLogout")
	public ModelAndView doLogout(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		if(request.getSession().getAttribute("user")!=null) {
			request.getSession().setAttribute("user", null);
		}
			mav.setViewName("redirect:/index");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping("/checkUsername")
	public Response checkUsername(String username) {
		Response resp = new Response();
		if(userServiceImpl.checkUsername(username)) {
			resp.setCode(-1);//
			resp.setMessage("用户名存在");
		} else {
			resp.setCode(200);
			resp.setMessage("用户名可用");
		}
		return resp;
	}
	
	
	@RequestMapping("/listUser")
	public ModelAndView listuser(@RequestParam(value="start",defaultValue="1")int start,
			@RequestParam(value = "size", defaultValue = "5")int size){
		//System.out.println("start"+start+"  size"+size);
		if(start<1) {
			start=1;
		}
		List<User> airpors = userServiceImpl.findAll();
		int max;
		if((airpors.size()+1)%5>0) {
			max=(airpors.size()+1)/5+1;
		}else {
			max=(airpors.size()+1)/5;
		}
		if(start>max) {
			start=max;
		}
		ModelAndView mav = new ModelAndView();
		//1. 在参数里接受当前是第几页 start ，以及每页显示多少条数据 size。 默认值分别是0和5。
        //2. 根据start,size进行分页，并且设置id 排序
        PageHelper.startPage(start,size,"userId");
        List<User> us = userServiceImpl.findAll();
        //3. 因为PageHelper的作用，这里就会返回当前分页的集合了
        //4. 根据返回的集合，创建PageInfo对象
        PageInfo<User> page = new PageInfo<>(us);
        //5. 把PageInfo对象扔进model，以供后续显示
        mav.addObject("page", page);
        //6. 跳转到listuser.jsp
        mav.setViewName("manage/userManage/userManage");
		return mav;
    }
	
	
	@RequestMapping("/goUserManageUpdate")
	public ModelAndView goUserManageUpdate(int id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("user", userServiceImpl.findUserById(id));
		mav.setViewName("manage/userManage/userManageUpdate");
		return mav;
	}
	
	 @RequestMapping("/deleteUser")
	    public String deleteUser(int id)throws Exception{
		 userServiceImpl.delete(id);
	        return "redirect:listUser";  //删除成功,重定向到分类查询页面
	    }
	    
	    @PostMapping("/updateUser")//修改方法
	    public String updateUser(HttpServletRequest request,User user,MultipartFile photo)throws Exception{
	    	if(!photo.getOriginalFilename().equals("")&&!photo.getOriginalFilename().isEmpty()) {
	    		String fileName = photo.getOriginalFilename();
	    		user.setFileurl("/images/"+fileName);
	    		String path = request.getServletContext().getRealPath("/images/");
	    		File filePath = new File(path,fileName);
	    		if(!filePath.getParentFile().exists()) {
	    			filePath.getParentFile().mkdirs();
	    		}
	    		try {
	    			photo.transferTo(new File(path+File.separator+fileName));
	    		} catch (IllegalStateException e) {
	    			e.printStackTrace();
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	userServiceImpl.update(user); 
	    	request.getSession().setAttribute("user", user);
	        return "redirect:listUser";  //修改成功,重定向到分类查询页面
	    }
	
	
	    /**
	     * 用户中心
	     * */

		 @RequestMapping("/goUser")
		    public String goUser(){
		        return "home/user/user"; 
		    }
		 @RequestMapping("/goUserUpdate")
		 public String goUserUpdate(){
			 return "home/user/userUpdate"; 
		 }
		 @RequestMapping("/goAddCard")
		 public String goAddCard(){
			 return "home/user/addCard"; 
		 }
		 
		 @RequestMapping(value="/goListCard")
		 public ModelAndView goListCard(HttpServletRequest request) {
			 ModelAndView mav = new ModelAndView();
			 User user = (User) request.getSession().getAttribute("user");
			
			 mav.addObject("identifications",userServiceImpl.getListCardByUserId(user.getUserId()));
			 mav.setViewName("home/user/listCard");
			 return mav;
		 } 
		 
		 @PostMapping("/userUpdate")//修改方法
		    public String userUpdate(HttpServletRequest request,User user,MultipartFile photo)throws Exception{
		    	if(!photo.getOriginalFilename().equals("")&&!photo.getOriginalFilename().isEmpty()) {
		    		String fileName = photo.getOriginalFilename();
		    		user.setFileurl("/images/"+fileName);
		    		String path = request.getServletContext().getRealPath("/images/");
		    		File filePath = new File(path,fileName);
		    		if(!filePath.getParentFile().exists()) {
		    			filePath.getParentFile().mkdirs();
		    		}
		    		try {
		    			photo.transferTo(new File(path+File.separator+fileName));
		    		} catch (IllegalStateException e) {
		    			e.printStackTrace();
		    		} catch (IOException e) {
		    			e.printStackTrace();
		    		}
		    	}
		    	userServiceImpl.updateByUsername(user); 
		    	request.getSession().setAttribute("user", user);
		        return "home/user/user"; 
		    }
		 
		 
		 @RequestMapping(value="/addCard")
			public ModelAndView addCard(HttpServletRequest request,Identification identification) {
				ModelAndView mav = new ModelAndView();
				User user = (User) request.getSession().getAttribute("user");
				identification.setUser(user);
				userServiceImpl.saveCard(identification);
					mav.setViewName("redirect:/goListCard");
				return mav;
			}
		 @RequestMapping(value="/deleteCard")
		 public ModelAndView deleteCard(int id) {
			 ModelAndView mav = new ModelAndView();
			 userServiceImpl.deleteCard(id);;
			 mav.setViewName("redirect:/goListCard");
			 return mav;
		 }
		 
		 
}
