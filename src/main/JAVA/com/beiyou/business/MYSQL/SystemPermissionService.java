package com.beiyou.business.MYSQL;

import com.beiyou.mapper.PermissionMapper;
import com.beiyou.pojo.Permissiontb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemPermissionService {
    @Autowired
    PermissionMapper permissionMapper;

//    @System(describe = "获得系统所有权限")
    public List<String> queryAll(){
        return permissionMapper.queryAll();
    }
    public Object insertSystemPermission(List<Permissiontb> permissionList) {
        return permissionMapper.batchInsert(permissionList);
    }
//通过id查权限
    public List<String> queryPermissionByUserID(Integer userid) {
    return permissionMapper.queryPermissionById(userid);
    }
}
