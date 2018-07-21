package com.beiyou.business.MYSQL;

import com.beiyou.mapper.systemlogmessageMapper;
import com.beiyou.pojo.systemlogmessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemLogService {
    @Autowired
    private systemlogmessageMapper mapper;
    public void insert(systemlogmessage systemlogMessage){
        mapper.insertSelective(systemlogMessage);
    }
}
