#SpringBoot中使用filter
##注解方式配置
###方式1：
`1.直接实现Filter接口，并使用@Component注解标注为组件自动注入bean`
~~~java
    @Component
    public class filterDemo implements Filter {
    @Override
        public void init(FilterConfig filterConfig) throws ServletException {
    
        }
    
        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
    
            System.out.println("TestFilter,"+request.getRequestURI());
    
            //执行
            filterChain.doFilter(servletRequest, servletResponse);
        }
    
        @Override
        public void destroy() {
    
        }
    }
~~~
###方式二
`1.实现Filter接口，用@WebFilter注解，指定拦截路径以及一些参数，同时需要在启动类使用@ServletComponentScan扫描带@WebFilter、@WebServlet、@WebListener并将帮我们注入bean`
~~~java
    @WebFilter(filterName = "testFilter",urlPatterns = {"/login"})
    public class filterDemo implements Filter {
        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
    
        }
    
        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
    
            System.out.println("TestFilter,"+request.getRequestURI());
    
            //执行
            filterChain.doFilter(servletRequest, servletResponse);
        }
    
        @Override
        public void destroy() {
    
        }
    }
~~~
`2.在启动类中配置filter注解扫描路径@ServletComponentScan`
~~~java
@ServletComponentScan(basePackages = {"cn.itsource.filter_demo.filter"})
@SpringBootApplication
public class FilterDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(FilterDemoApplication.class, args);
    }
}
~~~

##filter的生命周期
1. init 新建周期 
`在服务器启动后，会创建Filter对象，然后调用init方法`
~~~java 
        @Override
        public void init(FilterConfig filterConfig) throws ServletException {}
~~~
2. doFilter 执行
`每一次请求被拦截时，执行`
~~~java
@Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {}
~~~
3. destroy 销毁
`在服务器关闭后，filter对象销毁。如果服务器是正常关闭，则执行destroy方法`
~~~java
    @Override
            public void destroy() {}
~~~

##拦截访问的方式
注解配置
`设置@WebFilter注解中dispatchertypes属性`
1. REQUEST：默认值。浏览器直接请求资源
2. FORWARD：转发访问资源
3. INCLUDE：包含访问资源
4. ERROR：错误跳转资源
5. ASYNC：异步访问

##过滤器链(配置多个过滤器)
###执行顺序
  如果有两个过滤器：过滤器1和过滤器2
  1. 过滤器1
  2. 过滤器2
  3. 资源执行
  4. 过滤器2
  5. 过滤器1
###过滤器执行先后顺序问题
  1. 注解配置：按照类名的字符串规则比较，值小的先执行

