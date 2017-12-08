package com.shop.service;

import com.shop.model.Admin;
import com.shop.model.User;

import java.util.List;

/**
 * @author ZGY
 */
public interface AdminService {

    void updateUser(User user);

    Admin checkUser(Admin admin);

    void deleteUser(Integer uid);

    List<User> findUser(Integer page);

    Integer countUser();

    User findUserByUid(Integer uid);

    Admin findAdminByAid(Integer aid);
}
