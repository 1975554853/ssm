package com.beiyou.view;

import com.beiyou.business.MYSQL.StuBus;
import com.beiyou.pojo.Student;
import com.beiyou.util.poiUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/stu")
@CrossOrigin
/**
 * @author fanliyang
 */
public class File{
@Autowired
private StuBus stuBus;
//
@Autowired

private poiUtils poiUtils;
/**
 * 下载excel表格
 * @param  response
 * @throws IOException
 */
@GetMapping("/download")
public void download(HttpServletResponse response) throws IOException {
        // 定义表头
        String[] tableHeader = {"id","姓名","描述","座位","性别"};
        // 创建工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 设置单元格样式
        HSSFCellStyle style = workbook.createCellStyle();
        //居中
        style.setAlignment(HorizontalAlignment.CENTER);
        // 创建第一个工作表
        HSSFSheet sheet = workbook.createSheet("学生表");
        // 创建第一行
        HSSFRow row = sheet.createRow(0);
        for (int i = 0 ; i<tableHeader.length;i++){
        // 创建单元格
        HSSFCell cell = row.createCell(i);
        // 给单元格设置内容
        cell.setCellValue(tableHeader[i]);
        //将单元格居中
        cell.setCellStyle(style);
        //自动添加列
        sheet.autoSizeColumn(i);
        //列宽
        sheet.setColumnWidth(i,50*100);
        }
        // 获取要导出的所有学生
        List<Student> list = stuBus.selectAll();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < list.size(); i++) {
        // 从第二行开始
        HSSFRow hssfRow = sheet.createRow(i+1);
        // 每一行对应的学生
        Student students = list.get(i);
        // 给每个单元格赋值
        hssfRow.createCell(0).setCellValue(students.getId());
        hssfRow.createCell(1).setCellValue(students.getStu_name());
        hssfRow.createCell(2).setCellValue(students.getStu_desc());
        hssfRow.createCell(3).setCellValue(students.getStu_seat());
        hssfRow.createCell(4).setCellValue(students.getStu_sex());
        }
        // 设置excel文件名称
        String fileName = "学生.xls";
        //避免下载文件名出现乱码
        fileName = URLEncoder.encode(fileName,"UTF8");
        //开始输出工作簿
        OutputStream outputStream = response.getOutputStream();
        // 重置response设置
        response.reset();
        response.setHeader("Content-disposition", "attachment;filename="+fileName);
        response.setContentType("application/vnd.ms-excel");
        // 发送工作簿
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
}


/**

 * 用户上传excel,解析excel,将excel数据加入到数据库中

 * @param file

 * @return

 */

@PostMapping("/upload")

public boolean uploadStuByExcel(@RequestParam("file")MultipartFile file){
        List<String[]> strings = poiUtils.getWorkbookValue(file);
        List<Student> students = new ArrayList<>();
        for (String[] str : strings ) {
        Student student = new Student();

        student.setStu_name(str[1]);
        student.setStu_sex(str[2]);
        student.setStu_seat(Integer.valueOf(str[3]));
        student.setStu_desc(str[4]);
        students.add(student);
        }
        return stuBus.batchStudents(students);
        }
}