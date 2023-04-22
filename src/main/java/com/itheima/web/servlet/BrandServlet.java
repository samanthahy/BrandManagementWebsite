package com.itheima.web.servlet;


import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {
    private BrandService brandService = new BrandServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
//      1. 调用service查询
        List<Brand> brands = brandService.selectAll();

//      2. java对象转为json
        String jsonString = JSON.toJSONString(brands);

//      3. 数据写入response
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
//      1. 接收品牌数据
        BufferedReader br = request.getReader();
        String params = br.readLine();   //json字符串

//      2. 转成brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

//      3. 调用service查询，如果不报错可以执行下一步，否则不会响应
        brandService.add(brand);

//      4. 响应成功的标识
        response.getWriter().write("success");
    }

    /*
    *
    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //1. 获取参数
        String strId = request.getParameter("id");
        System.out.println(strId);
        int id = Integer.parseInt(strId);

        //2. 调用service删除，如果不报错可以执行下一步，否则不会响应
        brandService.deleteById(id);

        //3. 响应成功的标识
        response.getWriter().write("success");
    }
    */


    /*
    * 批量删除
    * */
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws IOException {
//      1. 获取参数，json字符串[1，2，3]
        BufferedReader br = request.getReader();
        String params = br.readLine();   //json字符串

//      2. 转成int[]对象
        int[] ids = JSON.parseObject(params, int[].class);
        System.out.println(ids);

//      2. 调用service删除，如果不报错可以执行下一步，否则不会响应
        brandService.deleteByIds(ids);

//      3. 响应成功的标识
        response.getWriter().write("success");
    }


    // 分页查询
    public void selectByPage(HttpServletRequest request, HttpServletResponse response ) throws IOException {
        //1. 接收”当前页码“和”每页展示条数“参数   get模式发送:url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //2. 调用service查询
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);

        //3. java对象转为json
        String jsonString = JSON.toJSONString(pageBean);

        //4. 数据写入response
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }


    // 分页条件查询
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response ) throws IOException {
        //1. 接收”当前页码“和”每页展示条数“参数   get模式发送:url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        // 获取查询对象条件
        BufferedReader br = request.getReader();
        String params = br.readLine();   //json字符串

//      2. 转成brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        //2. 调用service查询
        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage, pageSize, brand);

        //3. java对象转为json
        String jsonString = JSON.toJSONString(pageBean);

        //4. 数据写入response
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

}
