package com.beiyou.pojo;

public class File_system {
    private Integer idfileSystem;

    private String fileSystemOldname;

    private String fileSystemNewname;

    private String fileSystemType;

    private String fileSystemUploaddate;

    private String fileSystemPath;

    public Integer getIdfileSystem() {
        return idfileSystem;
    }

    public void setIdfileSystem(Integer idfileSystem) {
        this.idfileSystem = idfileSystem;
    }

    public String getFileSystemOldname() {
        return fileSystemOldname;
    }

    public void setFileSystemOldname(String fileSystemOldname) {
        this.fileSystemOldname = fileSystemOldname == null ? null : fileSystemOldname.trim();
    }

    public String getFileSystemNewname() {
        return fileSystemNewname;
    }

    public void setFileSystemNewname(String fileSystemNewname) {
        this.fileSystemNewname = fileSystemNewname == null ? null : fileSystemNewname.trim();
    }

    public String getFileSystemType() {
        return fileSystemType;
    }

    public void setFileSystemType(String fileSystemType) {
        this.fileSystemType = fileSystemType == null ? null : fileSystemType.trim();
    }

    public String getFileSystemUploaddate() {
        return fileSystemUploaddate;
    }

    public void setFileSystemUploaddate(String fileSystemUploaddate) {
        this.fileSystemUploaddate = fileSystemUploaddate == null ? null : fileSystemUploaddate.trim();
    }

    public String getFileSystemPath() {
        return fileSystemPath;
    }

    public void setFileSystemPath(String fileSystemPath) {
        this.fileSystemPath = fileSystemPath == null ? null : fileSystemPath.trim();
    }
}