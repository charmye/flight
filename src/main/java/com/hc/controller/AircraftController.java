package com.hc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hc.pojo.Aircraft;
import com.hc.service.impl.AircraftServiceImpl;

@Controller
public class AircraftController {

@Autowired AircraftServiceImpl aircraftServiceImpl;
	
	
	
	@RequestMapping("/listAircraft")
	public ModelAndView listAircraft(@RequestParam(value="start",defaultValue="1")int start,
			@RequestParam(value = "size", defaultValue = "5")int size){
		//System.out.println("start"+start+"  size"+size);
		if(start<1) {
			start=1;
		}
		List<Aircraft> airpors = aircraftServiceImpl.findAllAircraft();
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
        PageHelper.startPage(start,size,"aircraftId");
        List<Aircraft> as = aircraftServiceImpl.findAllAircraft();
        //3. 因为PageHelper的作用，这里就会返回当前分页的集合了
        //4. 根据返回的集合，创建PageInfo对象
        PageInfo<Aircraft> page = new PageInfo<>(as);
        //5. 把PageInfo对象扔进model，以供后续显示
        mav.addObject("page", page);
        //6. 跳转到listaircraft.jsp
        mav.setViewName("manage/aircraftManage/aircraftManage");
		return mav;
    }
	
	@RequestMapping("/goAircraftManageAdd")
	public String goAircraftManageAdd() {
		return "manage/aircraftManage/aircraftManageAdd";
	}
	
	@RequestMapping("/goAircraftManageUpdate")
	public ModelAndView goAircraftManageUpdate(int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("manage/aircraftManage/aircraftManageUpdate");
		mav.addObject("aircraftId", id);
		return mav;
	}
	
    @RequestMapping("/addAircraft")
    public String addaircraft(Aircraft aircraft)throws Exception{
        aircraftServiceImpl.save(aircraft);
        return "redirect:listAircraft";  //添加成功,重定向到分类查询页面
    }
    
    @RequestMapping("/deleteAircraft")
    public String deleteaircraft(int id)throws Exception{
        aircraftServiceImpl.delete(id);
        return "redirect:listAircraft";  //删除成功,重定向到分类查询页面
    }
    
    @PostMapping("/updateAircraft")//修改方法
    public String updateaircraft(Aircraft aircraft)throws Exception{
        aircraftServiceImpl.update(aircraft); 
        return "redirect:listAircraft";  //修改成功,重定向到分类查询页面
    }
    
	
}
