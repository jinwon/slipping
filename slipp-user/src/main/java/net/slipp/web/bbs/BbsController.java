package net.slipp.web.bbs;

import net.slipp.domain.bbs.*;
import net.slipp.domain.user.User;
import net.slipp.service.bbs.BbsService;
import net.slipp.service.user.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/bbs")
public class BbsController {
	private static Logger log = LoggerFactory.getLogger(BbsController.class);
	
	private BbsService bbsService = new BbsService();
	private UserService userService = new UserService();
	
	@RequestMapping("/{userId}")
	public String list(@PathVariable String userId, Model model) throws Exception {
		
		//사용자 정보를 가져옴.
		User user = userService.findByUserId(userId);
		model.addAttribute("user", user);	
		
		return "bbs/list";
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String  create(Bbs bbs) throws Exception {
		log.debug("bbs : {}", bbs);
		
		//bbs.bbsIdx의 값을 지정한다.

		bbsService.insert(bbs);
		return "redirect:/";
	}
	
	@RequestMapping("/form/{userId}")
	public String createForm(@PathVariable String userId, Model model) throws Exception {
		//새 글쓰기 
		
		//사용자 정보를 가져옴.
		User user = userService.findByUserId(userId);
		model.addAttribute("user", user);	
		
		model.addAttribute("bbs", new Bbs());
		return "bbs/form";
	}



}
