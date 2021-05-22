package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.constant.MessageConstant;
import com.itheima.dao.CheckGroupDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 检查组服务
 */

@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    private CheckGroupDao checkGroupDao;

    //新增检查组,同时需要让检查组关联检查项
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        //新增检查组，操作t_checkgroup
        checkGroupDao.add(checkGroup);
        //设置检查组和检查项的多对多关系，操作t_checkgroup_checkitem
        Integer checkGroupId = checkGroup.getId();
        this.setCheckGroupAndCheckItem(checkGroupId, checkitemIds);
    }

    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        PageHelper.startPage(currentPage, pageSize);
        Page<CheckGroup> page = checkGroupDao.findByCondition(queryString);

        return new PageResult(page.getTotal(), page.getResult());
    }

    //根据ID查询检查组
    public CheckGroup findById(Integer id) {
        return checkGroupDao.findById(id);
    }

    public List<Integer> findCheckItemIdsByGroupItemId(Integer id) {
        return checkGroupDao.findCheckItemIdsByGroupItemId(id);
    }

    public void edit(CheckGroup checkGroup, Integer[] checkitemIds) {
        //新增检查组，操作t_checkgroup
        checkGroupDao.edit(checkGroup);
        //先删除原来的关联关系，之后再新增
        Integer checkGroupId = checkGroup.getId();
        checkGroupDao.deleteCheckItem_CheckGroup(checkGroupId);
        this.setCheckGroupAndCheckItem(checkGroupId, checkitemIds);
    }

    public void delete(Integer id) {
        //先删除对应关系
        checkGroupDao.deleteCheckItem_CheckGroup(id);
        //删除检查组
        checkGroupDao.delete(id);

    }

    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();
    }

    //建立检查组和检查项多对多关系
    public void setCheckGroupAndCheckItem(Integer checkGroupId, Integer[] checkitemIds) {
        if (checkitemIds != null && checkitemIds.length > 0) {
            for (Integer checkitemId : checkitemIds) {
                Map<String, Integer> map = new HashMap<>();
                map.put("checkgroupId", checkGroupId);
                map.put("checkitemId", checkitemId);

                checkGroupDao.setCheckGroupAndCheckItem(map);
            }
        }
    }
}
