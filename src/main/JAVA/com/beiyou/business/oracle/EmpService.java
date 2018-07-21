package com.beiyou.business.oracle;

import com.beiyou.mapper.oracle.scott.EmpMapper;
import com.beiyou.pojo.oracle.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpService {

    @Autowired

    private EmpMapper empMapper;



    public List<Emp> selectAllEmp(){

        return empMapper.selectEmpAll();

    }



}