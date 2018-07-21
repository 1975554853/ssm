package com.beiyou.view;

import com.beiyou.auth.Exception.NOLoginException;
import com.beiyou.auth.SystemUtils;
import com.beiyou.auth.token.JSON_WEB_TOKEN;
import com.beiyou.auth.token.Token;
import com.beiyou.business.MYSQL.SystemModleService;
import com.beiyou.business.MYSQL.SystemPermissionService;
import com.beiyou.business.MYSQL.SystemRoleIdService;
import com.beiyou.business.MYSQL.SystemUserService;
import com.beiyou.page.Page;
import com.beiyou.pojo.Usertb;
import com.beiyou.util.LoginResult;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@CrossOrigin
@RestController
@RequestMapping(value = "/login",name = "登录系统")
public class LoginController {
    @Autowired
    HttpSession session;

    @Autowired
    SystemPermissionService systemPermissionService;

    @Autowired
    SystemUserService systemUserService;
    @Autowired
    SystemRoleIdService systemRoleIdService;
    @Autowired
    SystemModleService systemModleService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    Token token;
    @PostMapping("/confirm")
    public Page login(String name, String pass) throws NOLoginException, UnsupportedEncodingException {
        Usertb  systemUser = systemUserService.selectUserByNameAndPass(name,pass);
        if(systemUser==null){
            return new Page(1,"用户名或密码错误");
        }
        //得到用户所有权限
        List<String> permissions = systemPermissionService.queryPermissionByUserID(systemUser.getUserid());
        //得到用户的角色id
        List<Integer> user_rolesID = systemRoleIdService.selectRoleid(systemUser.getUserid());
        //得到模块id
        List<Integer> user_modleId = systemModleService.selectModleId(user_rolesID);
        JSON_WEB_TOKEN json_web_token = new JSON_WEB_TOKEN(systemUser.getUserid(),permissions);
        String toke =token.createToken(json_web_token,12*60*60*1000);
        redisTemplate.opsForValue().set(systemUser.getUsername(),toke,4,TimeUnit.HOURS);
        LoginResult loginResult = new LoginResult(systemUser.getUserid(),systemUser.getUsername(),user_rolesID,user_modleId,toke);
        System.out.println(loginResult.getToken());
        return new Page(0,loginResult);
    }


    @PutMapping("/user")
    public String hello(){
        List<String> permissions = new ArrayList<>();
        permissions.add("file:select");
        permissions.add("system:update");
        session.setAttribute(SystemUtils.PERMISSION_NAME,permissions);
        return "我登录了";
    }

    @GetMapping("/token")
    public String token() throws UnsupportedEncodingException {
        Gson gson = new Gson();
        String json =  token.createToken(systemPermissionService.queryAll(),
                1000000);
        List list = token.unCreateToken(List.class,json);
        return gson.toJson(list);

    }
}
