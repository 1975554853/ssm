package com.beiyou.mapper;

import com.beiyou.pojo.Permissiontb;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PermissionMapper {
    List<String> queryAll();
    Integer batchInsert(List<Permissiontb> list);
    List<String> queryPermissionById(Integer userid);
}
