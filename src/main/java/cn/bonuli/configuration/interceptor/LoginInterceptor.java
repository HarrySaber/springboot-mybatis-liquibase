package cn.bonuli.configuration.interceptor;

import cn.bonuli.values.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * LoginInterceptor
 *
 * @author D.jin
 * @date 2018/10/22
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        HttpSession session = request.getSession();
        if(session.getAttribute("LoginUser") == null){
            logger.info("------:跳转到login页面！");
            response.sendRedirect(request.getContextPath()+"/user/login");
            return false;
        }
        return  true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
       /* logger.info("response status------:"+response.getStatus());
        logger.info("modelAndView ------:"+modelAndView);
        if(response.getStatus()==500){
            //modelAndView.setViewName("error");
            response.sendRedirect(request.getContextPath()+"/error/500");
        }else if(response.getStatus()==404){
            // modelAndView.setViewName("404");
            response.sendRedirect(request.getContextPath()+"/error/404");
        }*/

        // TODO Auto-generated method stub

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }
}
