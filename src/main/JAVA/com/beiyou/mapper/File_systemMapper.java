package com.beiyou.mapper;

import com.beiyou.pojo.File_system;

import java.util.List;

public interface File_systemMapper {
    int deleteByPrimaryKey(Integer idfileSystem);

    int insert(File_system record);

    int insertSelective(File_system record);

    File_system selectByPrimaryKey(Integer idfileSystem);

    int updateByPrimaryKeySelective(File_system record);

    int updateByPrimaryKey(File_system record);
    List<File_system> selectByPage();
}