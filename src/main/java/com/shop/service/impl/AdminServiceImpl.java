package com.shop.service.impl;

import com.shop.dao.AdminDao;
import com.shop.dao.UserDao;
import com.shop.model.Admin;
import com.shop.model.User;
import com.shop.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZGY
 */
@Transactional(rollbackFor = Exception.class)
@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminDao adminDao;
    @Resource
    private UserDao userDao;

    /**
     *  更新
     * @param user user
     */
    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }

    /**
     * 根據用戶名和密碼查詢
     * @param adminUser 用戶名和密碼
     * @return function
     */
    @Override
    public Admin checkUser(Admin adminUser) {
        return adminDao.findByAdminnameAndPassword(
                adminUser.getUsername(), adminUser.getPassword());
    }

    // 根据用户的uid删除用户
    @Override
    public void deleteUser(Integer uid) {
        userDao.delete(uid);
    }

    // 查询所有的用户
    @Override
    public List<User> findUser(Integer page) {
        return userDao.findAll(page);
    }

    // 统计有多少页的用户
    @Override
    public Integer countUser() {
        Integer count = userDao.countUser();
        return (count % 20 == 0 ? (count / 20) : (count / 20 + 1));
    }

    // 根据用户的uid查询用户信息
    @Override
    public User findUserByUid(Integer uid) {
        return userDao.findOne(uid);
    }

    // 根据管理员的aid查询管理员信息
    @Override
    public Admin findAdminByAid(Integer aid) {

        return adminDao.findOne(aid);
    }
}
