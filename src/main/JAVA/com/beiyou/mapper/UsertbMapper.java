package com.beiyou.mapper;

import com.beiyou.pojo.Usertb;
import org.apache.ibatis.annotations.Param;

public interface UsertbMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(Usertb record);

    int insertSelective(Usertb record);

    Usertb selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(Usertb record);

    int updateByPrimaryKey(Usertb record);

    Usertb selectUserByNameAndPass(@Param("username") String username,@Param("password") String password);
}