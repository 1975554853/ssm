package com.beiyou.view;

import com.beiyou.business.MYSQL.FileUploadServlet;
import com.beiyou.page.Page;
import com.beiyou.pojo.File_system;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

@RestController
@CrossOrigin
@RequestMapping(value = "/file",name = "文件上传系统")
public class FileController {
    @Autowired
    FileUploadServlet file1;



    @GetMapping(value = "/download/{id}",name="下载权限")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("id") Integer id) throws IOException {

        File_system fileSystem =file1.selectFilePathById(id);

        String downlaodFilename = URLEncoder.encode(fileSystem.getFileSystemOldname(),"utf-8");
        String filePath = fileSystem.getFileSystemPath();


        File file = new File(filePath);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDispositionFormData("attachment", downlaodFilename);
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(
                FileUtils.readFileToByteArray(file),
                httpHeaders,HttpStatus.OK);

    }

    @GetMapping(value = "/select",name ="查找权限" )
    public Page selectFile(Integer page, Integer limit){

        Page pages = file1.selectFile(page,limit);
        return pages;

    }

    @PostMapping(value = "/upload",name = "上传权限")
    public  boolean upload(MultipartFile[] file) {
        boolean flag = true;
        if (file.length == 0) {
            return false;
        }
        for (int i = 0; i < file.length; i++) {
            if (file[i].isEmpty()) {
                continue;
            }
            if (!file1.insertload1(file[i])){
                flag = false;
            }
        }
        return flag;
    }
}
