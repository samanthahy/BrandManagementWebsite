package com.itheima.service;

import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BrandService {

    /*
     * 查询所有
     * */
    List<Brand> selectAll();

    /*
     * 新增
     * */
    void add(Brand brand);

    /*
    删除指定id
    * */
/*    void deleteById(int id);*/


    /*
    * 批量删除
    * */
    void deleteByIds(int[] ids);


    /*
     * 分页查询
     * 参数-当前页码、每页展示条数
     * */
    PageBean<Brand> selectByPage(int currentPage, int pageSize);


    /*
     * 分页条件查询
     * */
    PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand);
}
