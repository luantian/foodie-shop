package com.imooc.service;

import com.imooc.pojo.Stu;

public interface StuService {
    public void saveStu();
    public void deleteStu(int id);
    public void updateStu(int id);
    public Stu getStuInfo(int id);
}
