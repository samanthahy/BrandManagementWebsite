package com.itheima.mapper;

import com.itheima.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandMapper {

    /*
    * 查询所有
    * */
    @Select("select * from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();

    /*
    * 新增
    * */
    @Insert("insert into tb_brand values(null, #{brandName}, #{companyName}, #{ordered}, #{description}, #{status})")
    void add(Brand brand);

    /*
    * 删除指定id
    @Delete("delete from tb_brand where id = #{id}")
    void deleteById(int id);
    */

    /*
    * 批量删除
    * 数组参数要用@Param注解
    * */
    void deleteByIds(@Param("ids") int[] ids);


    /*
    * 分页查询
    * */
    @ResultMap("brandResultMap")
    @Select("select * from tb_brand limit #{begin}, #{size}")
    List<Brand> selectByPage(@Param("begin") int begin, @Param("size") int size);


    /*
     * 查询总记录数
     * */
    @Select("select count(*) from tb_brand")
    int selectTotalCount();


    /*
     * 分页条件查询
     * */
    List<Brand> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("brand") Brand brand);

    /*
     * 查询符合条件的总记录数
     * */
    int selectTotalCountByCondition(Brand brand);

}
