package com.api.products.productapi.filter;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@WebFilter("/*")
public class LoggingFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException{
        log.info("Initializing filter: LoggingFilter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String url = httpServletRequest.getRequestURL().toString();
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("url: " + url + ", date and time: " + timeStamp);


        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("powered-by", "springboot");


        chain.doFilter(request, response);
    }


    @Override
    public void destroy(){
        log.info("destroy filter: LoggingFilter");
    }
}
