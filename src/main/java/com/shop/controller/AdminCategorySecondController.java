package com.shop.controller;

import com.shop.model.Category;
import com.shop.model.CategorySecond;
import com.shop.service.AdminCategorySecondService;
import com.shop.service.AdminCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author ZGY
 * @Date 2017年12月8日11:32:13
 */
@Controller
public class AdminCategorySecondController {

    @Resource
    private AdminCategorySecondService adminCategorySecondService;
    @Resource
    private AdminCategoryService adminCategoryService;

    /**
     *  更新二级分类
     * @param csid 二级分类ID
     * @param csname 二级分类名称
     * @param cid 一级分类ID
     * @return redirect:/listCategorySecond/1
     */
    @RequestMapping(value = "/updateCategorySecond", method = RequestMethod.POST)
    public ModelAndView updateCategorySecond(@RequestParam("csid") Integer csid, @RequestParam("csname") String csname,
                                             @RequestParam("cid") Integer cid) {
        Category category = adminCategoryService.findCategory(cid);
        CategorySecond categorySecond = adminCategorySecondService.findCategorySecond(csid);
        categorySecond.setCategory(category);
        categorySecond.setCsname(csname);
        adminCategorySecondService.updateCategorySecond(categorySecond);
        ModelAndView modelAndView = new ModelAndView("redirect:/listCategorySecond/1");
        return modelAndView;
    }


    /**
     * 跳转到修改二级分类界面
     *
     * @param csid 二级分类ID
     * @param map 容器
     * @return
     */
    @RequestMapping(value = "/gotoEditCategorySecond/{csid}")
    public String gotoEditCategorySecond(@PathVariable("csid") Integer csid,
                                         Map<String, Object> map) {
        System.out.println("传入二级分类ID" + csid);
        //查找对应的二级分类
        CategorySecond categorySecond = adminCategorySecondService.findCategorySecond(csid);
        map.put("categorySecond", categorySecond);
        //查找对应的一级分类
        List<Category> categorys = adminCategoryService.findCategory();
        map.put("categorys", categorys);
        return "admin/categorysecond/edit";
    }

    /**
     * 删除二级分类
     * @param csid 二级分类ID
     * @param page 页
     * @return "redirect:/listCategorySecond/" + page
     */
    @RequestMapping(value = "/deleteCategorySecond/{csid}/{page}")
    public ModelAndView deleteCategorySecond(@PathVariable("csid") Integer csid, @PathVariable("page") Integer page) {
        adminCategorySecondService.deleteCategorySecond(csid);
        ModelAndView modelAndView = new ModelAndView("redirect:/listCategorySecond/" + page);
        return modelAndView;
    }

    /**
     * 添加二级分类
     *
     * @param csname 二级分类名称
     * @param cid 一级分类ID
     * @return redirect:listCategorySecond/1
     */
    @RequestMapping(value = "/addCategorySecond")
    public ModelAndView addCategorySecond(@RequestParam("csname") String csname, @RequestParam("cid") Integer cid) {
        //查找一级分类对象
        Category category = adminCategoryService.findCategory(cid);
        //创建二级分类对象
        CategorySecond categorySecond = new CategorySecond();
        //设置一级分类和二级分类的关联关系
        categorySecond.setCategory(category);
        categorySecond.setCsname(csname);
        //保存对象
        adminCategorySecondService.addCategorySecond(categorySecond);
        ModelAndView modelAndView = new ModelAndView("redirect:listCategorySecond/1");
        return modelAndView;
    }

    /**
     * 跳转到添加二级分类的界面
     *
     * @param map 容器
     * @return admin/categorysecond/add
     */
    @RequestMapping(value = "/gotoAddCategorySecond")
    public String gotoAddCategorySecond(Map<String, Object> map) {
        List<Category> categorys = adminCategoryService.findCategory();
        map.put("categorys", categorys);
        return "admin/categorysecond/add";
    }


    /**
     * 按分页显示二级分类
     *
     * @param page 页
     * @param map 容器
     * @return admin/categorysecond/list
     */
    @RequestMapping(value = "/listCategorySecond/{page}")
    public String listCategorySecond(@PathVariable("page") Integer page, Map<String, Object> map) {
        List<CategorySecond> categorySeconds = adminCategorySecondService.listCategorySecond(page);
        map.put("categorySeconds", categorySeconds);
        map.put("page", page);
        //统计二级分类的页数
        Integer count = adminCategorySecondService.countCategorySecond();
        map.put("count", count);
        return "admin/categorysecond/list";
    }
}
