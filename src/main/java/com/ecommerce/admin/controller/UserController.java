package com.ecommerce.admin.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.admin.dto.RoleDto;
import com.ecommerce.admin.dto.UserDto;
import com.ecommerce.admin.entity.User;
import com.ecommerce.admin.service.RoleService;
import com.ecommerce.admin.service.UserService;

@Controller
@RequestMapping("admin/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;
	
	@GetMapping("/add-user")
	public String user(Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("user", userDto);
		List<RoleDto> listRoleDto = roleService.getListRole();
		if(!listRoleDto.isEmpty()) {
			model.addAttribute("listRoleDto", listRoleDto);
		}
		return "user/add-user";
	}
	
	@PostMapping("/add-user")
	public String user(@ModelAttribute("user") UserDto userdto) {
		userService.save(userdto);
		return "redirect:/admin";
	}
	
	@RequestMapping("/list-user")
	public String index(Model model) {
//		int pageSize = 1;
//		Pageable pageable = PageRequest.of(index, pageSize);
//		Page page = userService.findAllPaging(pageable);
//		System.out.print(page.getContent());
//		List<User> userList = new ArrayList<User>(page.getContent());
//		for(User user : userList) {
//			System.out.print(user.getEmail());
//		}
//		model.addAttribute("users", page);
		return "user/index";
	}
}
