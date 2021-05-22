package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckGroup;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {
    public void add(CheckGroup checkGroup);
    public void setCheckGroupAndCheckItem(Map map);
    public Page<CheckGroup> findByCondition(String queryString);
    public CheckGroup findById(Integer id);
    public List<Integer> findCheckItemIdsByGroupItemId(Integer id);
    public void edit(CheckGroup checkGroup);
    public void deleteCheckItem_CheckGroup(Integer id);
    public void delete(Integer id);
    public List<CheckGroup> findAll();
}
