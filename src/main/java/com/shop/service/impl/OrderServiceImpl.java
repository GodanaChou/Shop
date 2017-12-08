package com.shop.service.impl;

import com.shop.dao.OrderDao;
import com.shop.model.Order;
import com.shop.model.OrderItem;
import com.shop.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Override
    public Order findByOid(Integer oid) {
        return orderDao.findByOid(oid);
    }

    @Override
    public List<Order> findAll(Integer page) {
        int rows = 10;
        return orderDao.findByPage(page, rows);
    }


    @Override
    public List<Order> findByUid(Integer uid, Integer page) {
        int rows = 10;
        return orderDao.findPageByUid(uid, page, rows);
    }


    @Override
    public List<OrderItem> findOrderItem(Integer oid) {
        return orderDao.findOrderItem(oid);
    }


    @Override
    public void save(Order order) {
        orderDao.save(order);

    }


    @Override
    public void update(Order order) {
        orderDao.update(order);

    }

    @Override
    public Integer findCountByUid(Integer uid) {
        Integer count = orderDao.findCountByUid(uid);
        return (count % 5 == 0 ? (count / 5) : (count / 5 + 1));
    }


}
