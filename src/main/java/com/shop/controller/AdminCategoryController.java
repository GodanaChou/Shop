package com.shop.controller;

import com.shop.model.Category;
import com.shop.service.AdminCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ZGY
 * @Date 2017年12月8日11:31:07
 */

@Controller
public class AdminCategoryController {

    @Resource
    private AdminCategoryService adminCategoryService;

    /**
     * 查询某个一级分类
     *
     * @param cid  一级分类ID
     * @param map 容器
     */
    @ModelAttribute("category")
    public void getCategory(@RequestParam(value = "cid", required = false) Integer cid, Map<String, Object> map) {
        if (cid != null) {
            Category category = adminCategoryService.findCategory(cid);
            map.put("category", category);
        }
    }


    /**
     * 修改一级分类
     * @param category 一级分类
     * @return redirect:/listCategory/1
     */
    @RequestMapping(value = "/updateCategory")
    public ModelAndView updateCategory(@ModelAttribute("category") Category category) {
        adminCategoryService.updateCategory(category);
        ModelAndView modelAndView = new ModelAndView("redirect:/listCategory/1");
        return modelAndView;
    }


    /**
     * 跳转到修改一级分类
     *
     * @param cid 一级分类ID
     * @param map 容器
     * @return admin/category/edit
     */
    @RequestMapping(value = "/gotoEditCategory/{cid}")
    public String gotoEditCategory(@PathVariable("cid") Integer cid, Map<String, Object> map) {
        Category category = adminCategoryService.findCategory(cid);
        map.put("category", category);
        return "admin/category/edit";
    }


    /**
     * 删除一级分类
     *
     * @param cid 一级分类ID
     * @param page 页
     * @return redirect:/listCategory/ + page
     */
    @RequestMapping(value = "/deleteCategory/{cid}/{page}")
    public ModelAndView deleteCategory(@PathVariable("cid") Integer cid, @PathVariable("page") Integer page) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            adminCategoryService.deleteCategory(cid);
        }catch (Exception e){
            String error = "该目录下还有其他目录不能删除!";
            modelAndView.addObject("error",error);
        }
        modelAndView.setViewName("redirect:/listCategory/" + page);

        return modelAndView;
    }


    /**
     * 添加一级分类
     *
     * @param cname 前台传过来的一级分类名称
     * @return redirect:listCategory/1
     */
    @RequestMapping(value = "/addCategory")
    public ModelAndView addCategory(@RequestParam(value = "cname", required = true) String cname) {
        //创建一级分类的对象
        Category category = new Category();
        category.setCname(cname);
        category.setPrivilegeTime(new Date(System.currentTimeMillis()));
        adminCategoryService.addCategory(category);
        ModelAndView modelAndView = new ModelAndView("redirect:listCategory/1");
        return modelAndView;
    }

    /**
     * 跳转到添加一级分类
     *
     * @return admin/category/add
     */
    @RequestMapping(value = "/gotoAddCategory")
    public String gotoAddCategory() {
        return "admin/category/add";
    }


    /**
     * 查询一级分类
     *
     * @param page 页
     * @param map 容器
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return admin/category/list
     */
    @RequestMapping(value = "/listCategory/{page}")
    public String listCategory(@PathVariable("page") Integer page, Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        List<Category> categorys = adminCategoryService.listCategory(page);
        String error = request.getParameter("error");
        map.put("categorys", categorys);
        map.put("page", page);
        map.put("error",error);
        //查询一级分类的页数
        Integer count = adminCategoryService.countCategory();
        map.put("count", count);
        return "admin/category/list";
    }
}