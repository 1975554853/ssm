package com.beiyou.business.MYSQL;

import com.beiyou.mapper.StudentInfoMapper;
import com.beiyou.page.Page;
import com.beiyou.pojo.Student;
import com.beiyou.util.SystemProperties;
import com.beiyou.util.Systemrizhi;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuBus {

    @Autowired
    private StudentInfoMapper mapper;
@Systemrizhi(desc = SystemProperties.SELECT_STUDENT)
    public Page selectStudent(){
        PageInfo info = new PageInfo(mapper.SelectAll());
        Page page = new Page();
        page.setCode(0);
        page.setCount(info.getTotal());
        page.setMsg("成功");
        page.setData(mapper.SelectAll());
        return page;
    }
    @Systemrizhi(desc = SystemProperties.SELECT_STUDENT)
    public Student sselectsingle(Integer id){
        Student student = mapper.SelectSingle(id);

       return student;
    }
    public List selectAll(){
        List<Student> student = mapper.SelectAll();

        return student;
    }
    public boolean batchStudents(List<Student> students) {
        boolean b = true;
          for (Student s:students) {
           int i = mapper.batchStudents(s);
            if (i<1){
               b= false;
               }else {
            b= true;
        }
    }
return b;
    }

























    public StudentInfoMapper getMapper() {
        return mapper;
    }

    public void setMapper(StudentInfoMapper mapper) {
        this.mapper = mapper;
    }


}
