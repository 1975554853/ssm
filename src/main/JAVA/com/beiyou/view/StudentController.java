package com.beiyou.view;

import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping(value = "/stu",name = "学生系统")
@RestController
public class StudentController {
    @GetMapping(value = "/{id}",name = "查询学生权限")
    public String getStu(@PathVariable("id") String id){
        return id;
    }

}
