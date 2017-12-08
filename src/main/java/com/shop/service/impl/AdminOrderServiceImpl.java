package com.shop.service.impl;

import com.shop.dao.OrderDao;
import com.shop.model.Order;
import com.shop.service.AdminOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service("adminOrderService")
public class AdminOrderServiceImpl implements AdminOrderService {

    @Resource
    private OrderDao orderDao;

    @Override
    public Integer countOrder() {
        Integer count = orderDao.findCount();
        return (count % 10 == 0 ? (count / 10) : (count / 10 + 1));
    }

    @Override
    public Order findOrder(Integer oid) {
        return orderDao.findByOid(oid);
    }

    @Override
    public List<Order> listOrder(Integer page, Integer rows) {
        return orderDao.findByPage(page, rows);
    }

    @Override
    public void saveOrUpdateOrder(Order order) {
        orderDao.saveOrUpdate(order);
    }

    @Override
    public void deleteOrder(Order order) {
        orderDao.delete(order);
    }

}
