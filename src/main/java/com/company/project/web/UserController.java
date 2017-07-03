package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.User;
import com.company.project.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/07/03.
*/
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/manager")
    public String manager(Model model){
        model.addAttribute("userList", userService.findAll());
        model.addAttribute("title", "用户管理");
        return "user-manager";
    }

    @ResponseBody
    @PostMapping("/add")
    public Result add(User user) {
        userService.save(user);
        return ResultGenerator.genSuccessResult();
    }

    @ResponseBody
    @PostMapping("/delete")
    public Result delete(Integer id) {
        userService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ResponseBody
    @PostMapping("/update")
    public Result update(User user) {
        userService.update(user);
        return ResultGenerator.genSuccessResult();
    }

    @ResponseBody
    @PostMapping("/detail")
    public Result detail(Integer id) {
        User user = userService.findById(id);
        return ResultGenerator.genSuccessResult(user);
    }

    @ResponseBody
    @PostMapping("/list")
    public Result list(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<User> list = userService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
