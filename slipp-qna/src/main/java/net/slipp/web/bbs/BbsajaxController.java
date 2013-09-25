package net.slipp.web.bbs;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/bbsajax")
public class BbsajaxController {

	private static final Logger logger = LoggerFactory.getLogger(BbsajaxController.class);
	
	@RequestMapping("list")
	public String listajax(Model model, @RequestParam("s_value") String s_value, 
			@RequestParam("s_type") String s_type, HttpSession session ) throws IOException {
		// 내용을 세션으로 저장한다.
		
		logger.debug("========= ");
		logger.debug("세션 s_value = " +s_value);
		logger.debug("세션 s_type = " +s_type);
		
		session.setAttribute("s_value", s_value);
		session.setAttribute("s_type", s_type);
		
		
		return "/bbs/ajax";
		
	}
	
}
