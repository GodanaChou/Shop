package com.shop.service;

import com.shop.model.Category;

import java.util.List;

/**
 * @author ZGY
 * @ClassName: AdminCategoryService
 * @Description: 商品类别服务
 * @date 2017年12月8日10:24:32
 */
public interface AdminCategoryService {


    /**
     * 分页查询一级分类
     *
     * @param page 页
     * @return List<Category>
     */
     List<Category> listCategory(Integer page);



    /**
     * 查询一级分类的页数
     *
     * @return  Integer page = (count % 10 == 0 ? (count / 10) : (count / 10 + 1))
     */
     Integer countCategory();



    /**
     * 添加一级分类
     *
     * @param category 分类
     */
    void addCategory(Category category);


    /**
     * 删除一级分类
     *
     * @param cid 分类ID cid
     */
    void deleteCategory(Integer cid);


    /**
     * 查询某个具体的一级分类
     *
     * @param cid 分类ID cid
     * @return 一级分类信息Category
     */
    Category findCategory(Integer cid);


    /**
     * 更新一级分类
     *
     * @param category 一级分类category
     */
    void updateCategory(Category category);

    /**
     * 查询所有一级分类
     * @return List<Category>
     */
    List<Category> findCategory();
}
