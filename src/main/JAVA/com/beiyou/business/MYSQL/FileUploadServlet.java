package com.beiyou.business.MYSQL;

import com.beiyou.mapper.File_systemMapper;
import com.beiyou.page.Page;
import com.beiyou.pojo.File_system;
import com.beiyou.util.Systemrizhi;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FileUploadServlet {
    private static final String path = "D:/upload/";

    @Autowired
    private File_systemMapper mapper;

    private static String getUploadTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date data = new Date();
        String time = simpleDateFormat.format(data);
        return time;
    }
    private static String getFileSuffix(String fileName) {
        int index = fileName.lastIndexOf(".");
        String suffix = fileName.substring(index);
        return suffix;
    }
    public static String getFileNewName() {
        String fileName = System.currentTimeMillis() + "";
        return fileName;
    }
    public static File_system saveFileOnDisk(MultipartFile file) throws IOException {
        File_system fileSystem = new File_system();
        String fileOldName = file.getOriginalFilename();
        fileSystem.setFileSystemOldname(fileOldName);
        String fileType = getFileSuffix(fileOldName);
        fileSystem.setFileSystemType(fileType);
        String fileUploadTime = getUploadTime();
        fileSystem.setFileSystemUploaddate(fileUploadTime);
        String fileNewName = getFileNewName();
        fileSystem.setFileSystemNewname(fileNewName);
        String fileUploadPath = path + fileUploadTime + "/" + fileNewName + fileType;
        fileSystem.setFileSystemPath(fileUploadPath);
        File file1 = new File(fileUploadPath);
        if (!file1.exists()){
            file1.mkdirs();
        }
        file.transferTo(file1);
        return fileSystem;
    }
    @Systemrizhi(desc = "文件上传操作",isWrite = false)
    public boolean insertload1(MultipartFile file) {
        if(file.isEmpty()){
            return false;
        }
        try {
            File_system fileSystem = saveFileOnDisk(file);
            mapper.insertSelective(fileSystem);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Page selectFile(Integer page, Integer limit) {

        PageHelper.startPage(page,limit);
        Page pages = new Page(new PageInfo(mapper.selectByPage()),0);
        return pages;
    }

    public File_system selectFilePathById(Integer id) {

        File_system fileSystem = mapper.selectByPrimaryKey(id);
        return fileSystem ;

    }
}
