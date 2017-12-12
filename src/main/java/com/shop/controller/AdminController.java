package com.shop.controller;

import com.shop.model.Admin;
import com.shop.model.User;
import com.shop.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


@Controller
public class AdminController {

    @Resource
    private AdminService adminService;

    @ModelAttribute(value = "user")
    public void getUser(@RequestParam(value = "uid", required = false) Integer uid, Map<String, Object> map) {
        if (uid != null) {
            User user = adminService.findUserByUid(uid);
            map.put("user", user);
        }
    }

    /**
     * 更新user
     * @param user user
     * @return redirect:/listUser/1
     */
    @RequestMapping(value = "/updateUser")
    public ModelAndView updateUser(@ModelAttribute("user") User user) {
        adminService.updateUser(user);
        ModelAndView model = new ModelAndView();
        model.setViewName("redirect:/listUser/1");
        return model;
    }

    /**
     * 修改用户
     *
     * @param uid 用户ID
     * @param user user
     * @param map  Map<String, Object>
     * @return
     */
    @RequestMapping(value = "/editUser/{uid}")
    public String editUser(@PathVariable("uid") Integer uid, @ModelAttribute("user") User user, Map<String, Object>
            map) {
        user = adminService.findUserByUid(uid);
        map.put("user", user);
        return "admin/user/edit";
    }


    /**
     * 删除用户
     *
     * @param uid 用户的id
     * @param page 当前第几页
     * @return redirect:/listUser/ + page
     */
    @RequestMapping(value = "/deleteUser/{uid}/{page}")
    public ModelAndView deleteUser(@PathVariable("uid") Integer uid, @PathVariable("page") Integer page) {
        ModelAndView model = new ModelAndView();
        try {
            adminService.deleteUser(uid);
        }catch (Exception e){
            String error = "该用户存在过活动,不能删除";
            model.addObject("error",error);
        }

        model.setViewName("redirect:/listUser/" + page );
        return model;
    }


    /**
     * 管理员查询用户
     *
     * @param page 当前第几页
     * @param map 容器
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return admin/user/list
     */
    @RequestMapping(value = "/listUser/{page}")
    public String listUser(@PathVariable("page") Integer page, Map<String, Object> map,
                           HttpServletRequest request, HttpServletResponse response) {
        //保存所有用户的集合
        List<User> users = adminService.findUser(page);
        String error = request.getParameter("error");
        //查询用多少页
        Integer count = adminService.countUser();
        map.put("count", count);
        map.put("page", page);
        map.put("users", users);
        map.put("error",error);
        return "admin/user/list";
    }


    /**
     *  处理管理员登陆
     * @param admin 管理类
     * @param session HttpSession
     * @return admin/home
     */
    @RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
    public String adminLogin(Admin admin,
                             HttpSession session) {
        Admin checkUser = adminService.checkUser(admin);
        if (null == checkUser) {
            return "admin/index";
        } else {
            session.setAttribute("admin", admin);
        }
        return "admin/home";
    }

    // 跳转到管理员的登陆界面
    @RequestMapping(value = "/adminIndex")
    public String adminIndex() {
        return "admin/index";
    }

    //管理员首页顶部的界面
    @RequestMapping("/adminTop")
    public String adminTop() {
        return "admin/top";
    }

    //管理员首页左侧的界面
    @RequestMapping("/adminLeft")
    public String adminLeft() {
        return "admin/left";
    }

    //管理员登陆成功的右侧的界面
    @RequestMapping("/adminWelcome")
    public String adminWelcome() {
        return "admin/welcome";
    }

    //管理员首页底部的界面
    @RequestMapping("/adminBottom")
    public String adminButtom() {
        return "admin/bottom";
    }
}
