package com.hc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("flight")
public class PageController {

	@RequestMapping("/{i}/{j}")
	public String pageIJ(@PathVariable String i,@PathVariable String j) {
		return i+"/"+j;
	}
	
	@RequestMapping("/{i}/{j}/{k}")
	public String pageIJK(@PathVariable String i,@PathVariable String j,@PathVariable String k) {
		return i+"/"+j+"/"+k;
	}
	
}
