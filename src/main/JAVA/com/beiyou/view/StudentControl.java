package com.beiyou.view;

import com.beiyou.business.MYSQL.StuBus;
import com.beiyou.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController
@RequestMapping("/info")
public class StudentControl {
    @Autowired
     StuBus stuBus;
    @RequestMapping(value ="page",method = RequestMethod.GET)
   public Page getPage(Integer page,Integer limit){
        Page page1 =stuBus.selectStudent();
        return page1;
    }

}
