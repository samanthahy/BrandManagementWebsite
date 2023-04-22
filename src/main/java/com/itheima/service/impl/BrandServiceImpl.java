package com.itheima.service.impl;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService {

//  1. 创建SqlSessionFactory工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /*
    * 查询所有
    * */
    @Override
    public List<Brand> selectAll() {
//      2. 获取SqlSession对象
        SqlSession session = factory.openSession();

//      3. 获取BrandMapper
        BrandMapper mapper = session.getMapper(BrandMapper.class);

//      4. 调用方法
        List<Brand> brands = mapper.selectAll();

//      5. 释放session
        session.close();

        return brands;

    }

    /*
     * 新增
     * */
    @Override
    public void add(Brand brand) {
//      2. 获取SqlSession对象
        SqlSession session = factory.openSession();

//      3. 获取BrandMapper
        BrandMapper mapper = session.getMapper(BrandMapper.class);

//      4. 调用方法，提交事务
        mapper.add(brand);
        session.commit();

//      5. 释放session
        session.close();

    };

    /*
    public void deleteById(int id) {
        //2. 获取SqlSession对象
        SqlSession session = factory.openSession();

        //3. 获取BrandMapper
        BrandMapper mapper = session.getMapper(BrandMapper.class);

        //4. 调用方法，提交事务
        mapper.deleteById(id);
        session.commit();

        //5. 释放session
        session.close();
    }*/

    /*
    * 批量删除
    * */
    @Override
    public void deleteByIds(int[] ids) {
        //2. 获取SqlSession对象
        SqlSession session = factory.openSession();

        //3. 获取BrandMapper
        BrandMapper mapper = session.getMapper(BrandMapper.class);

        //4. 调用方法，提交事务
        mapper.deleteByIds(ids);
        session.commit();

        //5. 释放session
        session.close();
    }

    /*
     * 分页查询
     * */
    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize){
        //2. 获取SqlSession对象
        SqlSession session = factory.openSession();

        //3. 获取BrandMapper
        BrandMapper mapper = session.getMapper(BrandMapper.class);

        //4. 计算开始索引
        int begin = (currentPage - 1) * pageSize;
        //计算查询条目数
        int size = pageSize;

        //5. 查询当前页数据
        List<Brand> rows = mapper.selectByPage(begin, size);

        //6. 查询总记录数
        int totalCount = mapper.selectTotalCount();

        //7. 封装PageBean
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        //8. 释放session
        session.close();

        return pageBean;
    }


    /*
     * 分页条件查询
     * */
    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand){
        //2. 获取SqlSession对象
        SqlSession session = factory.openSession();

        //3. 获取BrandMapper
        BrandMapper mapper = session.getMapper(BrandMapper.class);

        //4. 计算开始索引
        int begin = (currentPage - 1) * pageSize;
        //计算查询条目数
        int size = pageSize;

        // 处理brand条件，模糊表达式
        String brandName = brand.getBrandName();
        if (brandName != null && brandName.length() > 0) {
            brand.setBrandName("%"+brandName+"%");
        }
        String companyName = brand.getCompanyName();
        if (companyName != null && companyName.length() > 0) {
            brand.setCompanyName("%"+companyName+"%");
        }

        //5. 查询当前页数据
        List<Brand> rows = mapper.selectByPageAndCondition(begin, size, brand);

        //6. 查询总记录数
        int totalCount = mapper.selectTotalCountByCondition(brand);

        //7. 封装PageBean
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        //8. 释放session
        session.close();

        return pageBean;
    }
}
