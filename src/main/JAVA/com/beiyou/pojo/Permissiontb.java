package com.beiyou.pojo;


public class Permissiontb {

  private long permissionId;
  private String permissionValue;
  private String permissionModule;
  private String permissionName;
  private java.sql.Date permissionTime;


  public long getPermissionId() {
    return permissionId;
  }

  public void setPermissionId(long permissionId) {
    this.permissionId = permissionId;
  }


  public String getPermissionValue() {
    return permissionValue;
  }

  public void setPermissionValue(String permissionValue) {
    this.permissionValue = permissionValue;
  }


  public String getPermissionModule() {
    return permissionModule;
  }

  public void setPermissionModule(String permissionModule) {
    this.permissionModule = permissionModule;
  }


  public String getPermissionName() {
    return permissionName;
  }

  public void setPermissionName(String permissionName) {
    this.permissionName = permissionName;
  }


  public java.sql.Date getPermissionTime() {
    return permissionTime;
  }

  public void setPermissionTime(java.sql.Date permissionTime) {
    this.permissionTime = permissionTime;
  }

  @Override
  public String toString() {
    return "Permissiontb{" +
            "permissionId=" + permissionId +
            ", permissionValue='" + permissionValue + '\'' +
            ", permissionModule='" + permissionModule + '\'' +
            ", permissionName='" + permissionName + '\'' +
            ", permissionTime=" + permissionTime +
            '}';
  }
}
