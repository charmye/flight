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
import com.hc.pojo.Airport;
import com.hc.service.impl.AirportServiceImpl;

@Controller
public class AirportController {

	@Autowired AirportServiceImpl airportServiceImpl;
	
	
	
	@RequestMapping("/listAirport")
	public ModelAndView listAirport(@RequestParam(value="start",defaultValue="1")int start,
			@RequestParam(value = "size", defaultValue = "5")int size){
		//System.out.println("start"+start+"  size"+size);
		if(start<1) {
			start=1;
		}
		List<Airport> airpors = airportServiceImpl.findAllAirport();
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
        PageHelper.startPage(start,size,"airportId");
        List<Airport> as = airportServiceImpl.findAllAirport();
        //3. 因为PageHelper的作用，这里就会返回当前分页的集合了
        //4. 根据返回的集合，创建PageInfo对象
        PageInfo<Airport> page = new PageInfo<>(as);
        //5. 把PageInfo对象扔进model，以供后续显示
        mav.addObject("page", page);
        //6. 跳转到listAirport.jsp
        mav.setViewName("manage/airportManage/airportManage");
		return mav;
    }
	
	@RequestMapping("/goAirportManageAdd")
	public String goAirportManageAdd() {
		return "manage/airportManage/airportManageAdd";
	}
	@RequestMapping("/goAirportManageUpdate")
	public ModelAndView goAirportManageUpdate(int id) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("airport", airportServiceImpl.getById(id));
		mav.setViewName("manage/airportManage/airportManageUpdate");
		return mav;
	}
	
    @RequestMapping("/addAirport")
    public String addAirport(Airport airport)throws Exception{
        airportServiceImpl.save(airport);
        return "redirect:listAirport";  //添加成功,重定向到分类查询页面
    }
    
    @RequestMapping("/deleteAirport")
    public String deleteAirport(int id)throws Exception{
        airportServiceImpl.delete(id);
        return "redirect:listAirport";  //删除成功,重定向到分类查询页面
    }
    
    @PostMapping("/updateAirport")//修改方法
    public String updateAirport(Airport airport)throws Exception{
        airportServiceImpl.update(airport); 
        return "redirect:listAirport";  //修改成功,重定向到分类查询页面
    }
    
    @RequestMapping("/editAirport")
    public String editAirport(int id ,Model m)throws Exception{
        Airport airport = airportServiceImpl.getById(id); //根据id查询
        m.addAttribute("airport", airport); //查到展示到修改页面
        return "editAirport";  //跳转到修改页面
    } 
}
