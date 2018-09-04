/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kezuo.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @date 2018-05-17 00:00:00
 * @Description:
 * @author zyyan Copyright（C） 2010~2020 深圳市宏电技术股份有限公司
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

	protected static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}

	public static Object getBean(Class<?> clazz) {
		return context.getBean(clazz);
	}

	public static Object getBean(String beanName) {
		return context.getBean(beanName);
	}
}
