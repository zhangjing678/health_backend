package com.itheima.service;

import com.itheima.entity.CheckItemDeleteFailException;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;

import java.util.List;

public interface CheckItemService {
    public void add(CheckItem checkItem);
    //声明一个分页查询的方法(service接口)
    public PageResult pageQuery(QueryPageBean queryPageBean);
    public void deleteById(Integer id) throws CheckItemDeleteFailException;
    public void edit(CheckItem checkItem);
    public CheckItem findById(Integer id);
    public List<CheckItem> findAll();
}
