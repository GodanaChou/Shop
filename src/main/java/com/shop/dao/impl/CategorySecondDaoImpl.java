package com.shop.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.shop.dao.CategorySecondDao;
import com.shop.model.CategorySecond;

@Repository("categorySecondDao")
public class CategorySecondDaoImpl extends BaseDaoImpl<CategorySecond> implements CategorySecondDao {

    @Override
    public Integer countCategorySecond() {
        String hql = "select count(*) from CategorySecond";
        return count(hql);
    }

    @Override
    public List<CategorySecond> findAll(Integer page) {
        String hql = "from CategorySecond";
        int rows = 15;
        int page1 = page;
        return find(hql, page1, rows);
    }

    @Override
    public List<CategorySecond> findAll() {
        String hql = "from CategorySecond";
        return find(hql);
    }

    @Override
    public CategorySecond findOne(Integer csid) {
        String hql = "from CategorySecond cs where cs.csid = ?";
        Query query = this.getCurrentSession().createQuery(hql);
        query.setParameter(0, csid);
        return (CategorySecond) query.uniqueResult();
    }

}
