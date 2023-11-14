package user.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import user.bean.UserDTO;
import user.service.UserService;

@CrossOrigin
@RestController
@RequestMapping(path = "user")
public class UserController {
	@Autowired
	public UserService userService;
	
	/*
	@GetMapping(path = "writeForm")
	public String writeForm() {
		
		return "/WEB-INF/user/writeForm.jsp";
	} 한 번도 사용하지 않아서 삭제 writeForm에서 모두 작성해서 부를 필요 없음*/ 
	
	//axios.get()를 썼기 때문에 getMapping으로 변경
	@GetMapping(path = "isExistId")
	public String isExistId(@RequestParam String id) {
		return userService.isExistId(id);
	}
	
	@PostMapping(path = "write")
	public void write(@ModelAttribute UserDTO userDTO) {
		userService.write(userDTO);
	}
	
	/*list.jsp 창만 띄운다.
	@GetMapping(path = "list")
	public String list(@RequestParam(required = false, defaultValue = "0") String page, Model model) {
		model.addAttribute("page", page);
		return "/WEB_INF/user/list.jsp";
	} 바로 띄웠기 때문에 필요 없어서 삭제*/
	
	//axios.get()를 썼기 때문에 getMapping으로 변경
	@GetMapping(path = "getUserList")
	public Page<UserDTO> getUserList(
			//page는 0부터 시작, 0이면 1페이지, 1이면 2페이지.............
			@PageableDefault(page=0, size=3, sort="name", direction =  Sort.Direction.DESC) Pageable pageable) {
		return userService.getUserList(pageable);
	}
	
	/*
	@GetMapping(path = "updateForm")
	public String updateForm(@RequestParam String id, @RequestParam String pg, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("pg", pg);
		return "/user/updateForm";
	} 부른 적이 없음 사용하지 않아서 삭제*/
	
	@GetMapping(path = "getUser")
	public Optional<UserDTO> getUser(@RequestParam String id) {
		return userService.getUser(id);
	}
	
	@PutMapping(path = "update")
	public void update(@ModelAttribute UserDTO userDTO) {
		userService.update(userDTO);
	}
	
	@DeleteMapping(path = "delete")
	public void update(@RequestParam String id) {
		userService.delete(id);
	}
	
	@GetMapping(path = "getUserSearchList")
	public Page<UserDTO> getUserSearchList(@RequestParam String columnName,
										   @RequestParam String keyword,
										   @PageableDefault(page=0, size=3, sort="name", direction =  Sort.Direction.DESC) Pageable pageable){
		return userService.getUserSearchList(columnName, keyword, pageable);
	}
}
