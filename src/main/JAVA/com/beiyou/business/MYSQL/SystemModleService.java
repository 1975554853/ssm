package com.beiyou.business.MYSQL;

import com.beiyou.mapper.SystemModuletbMapper;
import com.beiyou.pojo.SystemModuletb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SystemModleService {
    @Autowired
    SystemModuletbMapper mapper;
    public List<Integer> selectModleId(List<Integer> user_rolesID) {
         List<Integer> list = mapper.selectModleId(user_rolesID);
          return list;
    }

    public Object queryModleById(List<Integer> modles) {
        List<SystemModuletb> list = mapper.queryModleById(0,modles);
        System.out.println(list);
        queryChildrenModles(list);
        return list;
    }
    private  void  queryChildrenModles(List<SystemModuletb> modles){
        if (modles!=null && modles.size()>0){
            for (SystemModuletb moduletb :modles){
                List<SystemModuletb> list = mapper.queryChilrenByFather(moduletb.getModuleid());
                if (list!=null){
                    moduletb.setChildren(list);
                    queryChildrenModles(list);
                }else {
                    break;
                }
            }
        }
    }

    public Object queryModle(List<Integer> modules) {
        List<SystemModuletb> list = mapper.query(modules);
        return list;
    }
}
