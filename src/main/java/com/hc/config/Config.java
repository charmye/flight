package com.hc.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.github.pagehelper.PageHelper;
import com.hc.interceptor.ManageInterceptor;
import com.hc.interceptor.TicketCheckInterceptor;
import com.hc.interceptor.UserInterceptor;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class Config implements WebMvcConfigurer{

	
	@Bean
	public TicketCheckInterceptor ticketCheckInterceptor() {
		return new TicketCheckInterceptor();
	}
	@Bean
	public UserInterceptor myInterceptor() {
		return new UserInterceptor();
		
	}
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor()).addPathPatterns("/purchase","/historicalOrder","/reservation");
		registry.addInterceptor(new TicketCheckInterceptor()).addPathPatterns("/TicketCheck");
		registry.addInterceptor(new ManageInterceptor()).addPathPatterns("/goManage");
    }
	
	
	
	@Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        //1.offsetAsPageNum:设置为true时，会将RowBounds第一个参数offset当成pageNum页码使用.
        p.setProperty("offsetAsPageNum", "true");
        //2.rowBoundsWithCount:设置为true时，使用RowBounds分页会进行count查询.
        p.setProperty("rowBoundsWithCount", "true");
        //3.reasonable：启用合理化时，如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页。
        p.setProperty("reasonable", "true");
        pageHelper.setProperties(p);
        return pageHelper;
    }
	
	
}
