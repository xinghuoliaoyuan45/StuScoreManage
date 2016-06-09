package com.dao;
import java.util.*;

import com.entity.Stu;
public interface StuDao {
public List<Stu> findAll() throws Exception;
public boolean delById(long id) throws Exception;
public Stu findById(long id)throws Exception;
public boolean update(Stu stus)throws Exception;
public boolean delByName(String name) throws Exception;
public boolean add(Stu stu) throws Exception;
public boolean judgeidexist(long id)throws Exception;


}
