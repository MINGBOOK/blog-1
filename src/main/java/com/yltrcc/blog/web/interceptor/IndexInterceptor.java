package com.yltrcc.blog.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yltrcc.blog.model.dto.BlogConst;
import com.yltrcc.blog.util.Commons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 前台拦截器
 * 
 * @author yltrcc
 * @createDate : 2018年12月6日
 */
@Component
public class IndexInterceptor implements HandlerInterceptor {
	@Autowired
	private Commons commons;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String requestType = request.getHeader("X-Requested-With");
		//非ajax请求
		if(!"XMLHttpRequest".equals(requestType)){
			// 工具类
			request.setAttribute("commons", commons);
			// 设置项
			request.setAttribute("options", BlogConst.OPTIONS);
			// 菜单
			request.setAttribute("menus", BlogConst.MENUS);
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
