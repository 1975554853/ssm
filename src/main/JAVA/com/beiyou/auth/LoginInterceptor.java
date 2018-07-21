package com.beiyou.auth;

import com.beiyou.auth.Exception.NOLoginException;
import com.beiyou.auth.Exception.NoPermissionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;



public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws NOLoginException, NoPermissionException {

        String url = request.getServletPath();
        if(url.matches(SystemUtils.STATIC_NO_PERMISSION_PATH)){
            return true;
        }
        HttpSession session = request.getSession();
        List<String> userPermission = (List<String>) session.getAttribute(SystemUtils.PERMISSION_NAME);
        if(userPermission==null){
            throw new NOLoginException("请先登陆");
        }
        if(handler instanceof HandlerMethod){
            String permissionURL = SystemUtils.getMethodOfPermission((HandlerMethod) handler);
            if(!userPermission.contains(permissionURL)){
                throw new NoPermissionException("你没有访问该资源的权限,请联系我！！！");
            }
        }
        return true;
    }

}
