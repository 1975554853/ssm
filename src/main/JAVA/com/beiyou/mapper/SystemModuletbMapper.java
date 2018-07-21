package com.beiyou.mapper;

import com.beiyou.pojo.SystemModuletb;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemModuletbMapper {
    int deleteByPrimaryKey(Integer moduleid);

    int insert(SystemModuletb record);

    int insertSelective(SystemModuletb record);

    SystemModuletb selectByPrimaryKey(Integer moduleid);

    int updateByPrimaryKeySelective(SystemModuletb record);

    int updateByPrimaryKey(SystemModuletb record);


    List<Integer> selectModleId(List<Integer> list);

    List<SystemModuletb> queryModleById(@Param("father") int father, @Param("modles")List<Integer> modles);

    List<SystemModuletb> queryChilrenByFather(Integer moduleid);

    List<SystemModuletb> query(List<Integer> list);
}