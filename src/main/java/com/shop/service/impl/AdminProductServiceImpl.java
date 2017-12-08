package com.shop.service.impl;

import com.shop.dao.ProductDao;
import com.shop.model.Product;
import com.shop.service.AdminProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service("adminProductService")
public class AdminProductServiceImpl implements AdminProductService {

    @Resource
    private ProductDao productDao;

    @Override
    public Integer countProduct() {
        Integer count = productDao.CountProduct();
        return (count % 8 == 0 ? (count / 8) : (count / 8 + 1));
    }

    @Override
    public void deleteProduct(Product product) {
        productDao.delete(product);
    }

    @Override
    public Product findProduct(Integer pid) {
        return productDao.findOne(pid);
    }

    @Override
    public List<Product> listProduct(Integer page) {
        return productDao.findAll(page);
    }

    @Override
    public void saveProduct(Product product) {
        productDao.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        productDao.update(product);
    }

}
