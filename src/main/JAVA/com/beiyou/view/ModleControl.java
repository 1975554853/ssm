package com.beiyou.view;

import com.beiyou.business.MYSQL.SystemModleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/modle" ,name = "模块管理")
@CrossOrigin
@RestController
public class ModleControl {
    @Autowired
    SystemModleService systemModleService;
    @PostMapping(value = "/query",name = "查询模块")
    public Object queryModleId(@RequestParam("modules")List<Integer> modules){

        Object o = systemModleService.queryModleById(modules);
        return  systemModleService.queryModle(modules);


    }
}
