package com.beiyou.business.MYSQL;

import com.beiyou.mapper.SystemRoletbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SystemRoleIdService {
    @Autowired
    SystemRoletbMapper Mapper;
    public List<Integer> selectRoleid(Integer userid) {
        return Mapper.selectRoleId(userid);
    }
}
