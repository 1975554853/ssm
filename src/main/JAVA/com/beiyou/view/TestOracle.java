package com.beiyou.view;

import com.beiyou.business.oracle.EmpService;
import com.beiyou.pojo.oracle.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestOracle {
    @Autowired
    EmpService empService;
    @GetMapping("/test")
    public boolean select(){
        List<Emp> list = empService.selectAllEmp();
        if (list!=null){
            return true;
        }else {
            return false;
        }
    }
}
