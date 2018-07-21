package com.beiyou.business.MYSQL;

import com.beiyou.mapper.UsertbMapper;
import com.beiyou.pojo.Usertb;
import com.beiyou.util.Systemrizhi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemUserService {
    @Autowired
    UsertbMapper usertb;
@Systemrizhi(desc = "用户登陆")
    public Usertb selectUserByNameAndPass(String username,String password) {
        Usertb usertb1 = usertb.selectUserByNameAndPass(username,password);
        return usertb1;
    }
}
