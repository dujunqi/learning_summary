package it.source.filter_demo.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @desciption:用途
 * @author: 杜俊圻
 * @date: 2020/12/23 11:46
 */
/*第一种方式：直接实现Filter接口，并使用@Component注解标注为组件自动注入bean
 * 查看日志可以发现，SpringBoot已经帮我们注入了一个filter，拦截路径是/*，拦截所有，如果我们需要进一步拦截具体的则需要我们自己在代码里控制*/
/*第二种方式：实现Filter接口，用@WebFilter注解，指定拦截路径以及一些参数，同时需要在启动类使用@ServletComponentScan扫描带@WebFilter、@WebServlet、@WebListener并将帮我们注入bean
 * */
@WebFilter(filterName = "testFilter",urlPatterns = {"/*"})
public class filterDemo implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        System.out.println("TestFilter,"+request.getRequestURI());

        //放行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
