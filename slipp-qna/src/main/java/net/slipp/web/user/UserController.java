package net.slipp.web.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.slipp.domain.user.ExistedUserException;
import net.slipp.domain.user.PasswordMismatchException;
import net.slipp.domain.user.User;
import net.slipp.domain.user.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/user")
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource(name = "userService")
	private UserService userService;

	@RequestMapping("")
	public String index(Model model) {
		// 전체 계정을 불러온다.
		model.addAttribute("users", userService.finds());
		return "user/index";
	}

	@RequestMapping("/form")
	public String createForm(Model model) {
		//사용자 추가 폼.
		model.addAttribute("user", new User());
		return "user/form";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String create(User user, Model model) {
		try {
			
			logger.debug("사용자_ Controller : " + user.getUserId());
			// 사용자 입력 처리.
			userService.create(user);
		} catch (ExistedUserException e) {
		}
		return "redirect:user";
	}

	@RequestMapping("/{userId}/form")
	public String updateForm(@PathVariable String userId, Model model) {
		model.addAttribute("user", userService.findUser(userId));
		return "user/form";
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public String update(User user, Model model) {
		userService.update(user);
		return "redirect:user";
	}
	
	@RequestMapping("/login/form")
	public String loginForm(Model model) {
		return "user/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(String userId, String password, HttpSession session) {
		try {
			userService.login(userId, password);
			session.setAttribute("user", userService.findUser(userId));
		} catch (PasswordMismatchException e) {
			return "user/login";
		}
		return "redirect:/";
	}
}
