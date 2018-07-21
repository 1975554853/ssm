package com.beiyou.mapper;


import com.beiyou.pojo.SystemRoletb;

import java.util.List;

public interface SystemRoletbMapper {
    int deleteByPrimaryKey(Integer roleid);

    int insert(SystemRoletb record);

    int insertSelective(SystemRoletb record);

    SystemRoletb selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(SystemRoletb record);

    int updateByPrimaryKey(SystemRoletb record);
    List<Integer> selectRoleId(Integer userid);
}