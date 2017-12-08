package com.shop.service.impl;

import com.shop.dao.CategoryDao;
import com.shop.model.Category;
import com.shop.service.AdminCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZGY
 */
@Transactional(rollbackFor = Exception.class)
@Service("adminCategoryService")
public class AdminCategoryServiceImpl implements AdminCategoryService {

    @Resource
    private CategoryDao categoryDao;

    @Override
    public void addCategory(Category category) {
        categoryDao.save(category);
    }

    @Override
    public void deleteCategory(Integer cid) {
        categoryDao.delete(cid);
    }

    @Override
    public void updateCategory(Category category) {
        categoryDao.update(category);
    }

    @Override
    public Integer countCategory() {
        Integer count = categoryDao.countCategory();
        return (count % 10 == 0 ? (count / 10) : (count / 10 + 1));
    }

    @Override
    public Category findCategory(Integer cid) {
        return categoryDao.findOne(cid);
    }

    @Override
    public List<Category> findCategory() {
        return categoryDao.findAll();
    }

    @Override
    public List<Category> listCategory(Integer page) {
        return categoryDao.findAll(page);
    }

}
