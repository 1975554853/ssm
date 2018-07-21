package com.beiyou.mapper;

import com.beiyou.pojo.systemlogmessage;
import org.springframework.stereotype.Repository;

@Repository
public interface systemlogmessageMapper {
    int deleteByPrimaryKey(Integer idsystemlogmessage);

    int insert(systemlogmessage record);

    int insertSelective(systemlogmessage record);

    systemlogmessage selectByPrimaryKey(Integer idsystemlogmessage);

    int updateByPrimaryKeySelective(systemlogmessage record);

    int updateByPrimaryKeyWithBLOBs(systemlogmessage record);

    int updateByPrimaryKey(systemlogmessage record);
}