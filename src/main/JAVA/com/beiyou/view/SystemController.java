package com.beiyou.view;

import com.beiyou.business.MYSQL.SystemPermissionService;
import com.beiyou.pojo.Permissiontb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/system", name = "系统模块")
public class SystemController {

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Autowired
    private SystemPermissionService systemPermissionService;

    @GetMapping(value = "/update", name = "更新系统权限")
    public Object updateSystemPermission() {
        Integer K = (Integer) this.modifySystemPermission();
        return "更新了" + K + "条权限";
    }
    /**
     * 更新系统权限
     *
     * @return
     */
    private synchronized Object modifySystemPermission() {
        //系统中已经存在的权限集合
        List<String> systemAlReady = systemPermissionService.queryAll();
        // 创建权限集合
        List<Permissiontb> permissionList = new ArrayList<>();
        Map<RequestMappingInfo, HandlerMethod> mappingInfoHandlerMethodMap =
                requestMappingHandlerMapping.getHandlerMethods();
//        得到所有被requestMapping所修饰的方法
        Collection<HandlerMethod> handlerMethods = mappingInfoHandlerMethodMap.values();
        // 如果系统中没有被requestMapping所修饰的方法，结束
        if (handlerMethods == null || handlerMethods.isEmpty()) {
            return null;
        }
        // 遍历所有方法
        for (HandlerMethod method : handlerMethods) {
            //得到方法的requestMapping注解
            RequestMapping methodRequestMapping = method.getMethodAnnotation(RequestMapping.class);
            //如果方法含有name属性
            if (!"".equals(methodRequestMapping.name())) {
                // 得到访问该方法的URL地址
                String methodURL = methodRequestMapping.value()[0];
                // 得到该方法所在类的requestMapping注解
                RequestMapping classRequestMapping =
                        method.getBeanType().getAnnotation(RequestMapping.class);
                // 如果类含有name属性，则表明该类拥有模块名称
                String module = "".equals(classRequestMapping.name()) ? "" : classRequestMapping.name();
                // 得到访问该类的URL地址
                String classURL = classRequestMapping.value()[0];
                // file:{download}{id}  -->> 文件系统  下载权限
                String permissionURL = (classURL + ":" + methodURL).replace("/", "");
                if(systemAlReady!=null){
                    if(systemAlReady.contains(permissionURL)){
                        continue;
                    }
                }
                Permissiontb permission = new Permissiontb();
                permission.setPermissionValue(permissionURL);
                permission.setPermissionModule(module);
                permission.setPermissionName(methodRequestMapping.name());

                permissionList.add(permission);
            }
        }
        if (permissionList.size()==0){
            return 0;
        }
        return systemPermissionService.insertSystemPermission(permissionList);
    }
}
