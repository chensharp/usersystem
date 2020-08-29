package com.chenzy.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.chenzy.demo.domain.*;
import com.chenzy.demo.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	protected UserRepository userRepository;
	
	@RequestMapping("/test")
	public String index () {
		
		System.out.println("ss");
	   return "index";
	}
	
	@GetMapping("/userlist")
	//@RequestMapping(value = "/userlist", method = RequestMethod.GET)
	public ModelAndView userList(Model model) {
		model.addAttribute("userList",userRepository.findAll());
		model.addAttribute("title","用户管理");
		return new ModelAndView("user/list","userModel",model);
	}
	
	//根据id 查询用户
    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Long id, Model model){
        Optional<User> user= userRepository.findById(id);
        model.addAttribute("user",user.get());
        model.addAttribute("title","查看用户");
        return new ModelAndView("user/view" ,"userModel",model);
    }

    //获取创建表单页面
    @GetMapping("/form")
    public ModelAndView createForm(Model model){
        model.addAttribute("user",new User(null,null,null));
        model.addAttribute("title","创建用户");
        return new ModelAndView("user/form","userModel",model);
    }

    //保存用户
    @PostMapping
    public ModelAndView saveOrUpdateUser(User user){
        user =userRepository.save(user);
        System.out.println("add user success!");
        return new ModelAndView("redirect:/user/userlist");
    }

    //根据id删除用户
    @GetMapping(value = "/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id){
        userRepository.deleteById(id);
        return new ModelAndView("redirect:/user/userlist");
    }

    //修改用户界面
    @GetMapping(value = "/edit/{id}")
    public ModelAndView editForm(@PathVariable("id") Long id,Model model){
    	System.out.println("edit + id="+id);
        Optional<User> user = userRepository.findById(id);
        model.addAttribute("user",user.get());
        model.addAttribute("title","编辑用户");
        return new ModelAndView("user/form" ,"userModel",model);
    }
	

}
