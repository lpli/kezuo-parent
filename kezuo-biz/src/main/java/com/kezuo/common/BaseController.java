package com.kezuo.common;

import com.baomidou.mybatisplus.plugins.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 基础控制器
 *
 * @author pole
 * @date 2018-01-16
 */
public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired //测试类运行，需要注释，非tomcat环境没有加载bean
    protected HttpServletRequest request;

    @Autowired //测试类运行，需要注释，非tomcat环境没有加载bean
    protected HttpServletResponse response;

    @Autowired //测试类运行，需要注释，非tomcat环境没有加载bean
    protected HttpSession session;

    @Autowired //测试类运行，需要注释，非tomcat环境没有加载bean
    protected ServletContext application;

    protected static int DEFAULT_PAGE_SIZE = 20;//默认分页数

    /**
     * 是否为 post 请求
     */
//    protected boolean isPost() {
//        return HttpUtil.isPost(request);
//    }
    /**
     * 是否为 get 请求
     */
//    protected boolean isGet() {
//        return HttpUtil.isGet(request);
//    }
    /**
     * <p>
     * 获取分页对象
     * </p>
     *
     * @param <T>
     * @param pageNumber
     * @return
     */
    protected <T> Page<T> getPage(int pageNumber) {
        return getPage(pageNumber, DEFAULT_PAGE_SIZE);
    }

    /**
     * <p>
     * 获取分页对象
     * </p>
     *
     * @param <T>
     * @param pageNumber
     * @param pageSize
     * @return
     */
    protected <T> Page<T> getPage(int pageNumber, int pageSize) {
        return new Page<T>(pageNumber, pageSize);
    }

    /**
     * 重定向至地址 url
     *
     * @param url 请求地址
     * @return
     */
    protected String redirectTo(String url) {
        StringBuilder rto = new StringBuilder("redirect:");
        rto.append(url);
        return rto.toString();
    }

    /**
     *
     * 返回 JSON 格式对象
     *
     * @param object 转换对象
     * @return
     */
//    protected String toJson(Object object) {
//        return JSON.toJSONString(object, SerializerFeature.BrowserCompatible);
//    }

    /**
     *
     * 返回 JSON 格式对象
     *
     * @param object 转换对象
     * @param format
     * @return
     */
//    protected String toJson(Object object, String format) {
//        if (format == null) {
//            return toJson(object);
//        }
//        return JSON.toJSONStringWithDateFormat(object, format, SerializerFeature.WriteDateUseDateFormat);
//    }

}
