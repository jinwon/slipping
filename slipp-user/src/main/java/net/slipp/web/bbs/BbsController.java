package net.slipp.web.bbs;

import java.awt.List;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import net.slipp.domain.bbs.Bbs;
import net.slipp.domain.user.User;
import net.slipp.service.bbs.BbsService;
import net.slipp.service.bbs.PasswordMismatchException;
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

	@RequestMapping("")
	public String init(HttpSession session, Model model) throws Exception {
				
		User user = (User) session.getAttribute("loginUser");
		model.addAttribute("user", user);		

		//전체 게시판 목록을 구한다.	
		Map<String, Bbs> bbslist = bbsService.getList();
		
		int count = bbslist.size();
		
		Bbs lists[] = new Bbs[count];
		
		//리스트 신규가 먼저 정렬
		
		ArrayList<String> key_lists = new ArrayList<String>();
		
		for (String str : bbslist.keySet())
		{
			key_lists.add(str);
		}
		
		Collections.sort(key_lists, Collections.reverseOrder());

		int s_idx = 0;
	
		for (String key : key_lists)
		{
			lists[s_idx] = bbslist.get(key);
			s_idx++;
		}
				
			
		model.addAttribute("lists", lists);
		model.addAttribute("count", count);
		
		return "bbs/list";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String create(String userId, String bbsPassword, String subject, String content, HttpSession session) throws Exception {
		
		log.debug("bbs insert");
		log.debug("bbs : userId =" + userId);
		log.debug("bbs : bbsPassword =" + bbsPassword);
		log.debug("bbs : subject =" + subject);
		log.debug("bbs : content =" + content);
		
		//bbs.bbsIdx의 값을 지정한다.

		String bbsIdx = Long.toString(System.currentTimeMillis());
		
		Date now = new Date(System.currentTimeMillis());
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		format.format(now);
		String writeDate = format.format(now);
		
		log.debug("bbs : bbsIdx =" + bbsIdx);
		log.debug("bbs : writeDate =" + writeDate);
		
		Bbs new_bbs = new Bbs(bbsIdx, userId, bbsPassword, subject, content, writeDate);
		
		try {
			bbsService.insert(new_bbs);
			return "redirect:/bbs/";
		}
		catch (Exception e)
		{
			log.debug("bbs : insert error");
			return "bbs/insert";
		}
		
	}
	
	@RequestMapping("/form")
	public String createForm(HttpSession session, Model model) throws Exception {
		//새 글쓰기 폼 

		//사용자 정보를 가져옴.
		User user = (User) session.getAttribute("loginUser");
		model.addAttribute("user", user);		
				
		model.addAttribute("bbs", new Bbs());
		return "bbs/form";
	}

	@RequestMapping("/{bbsIdx}")
	public String show(@PathVariable String bbsIdx, Model model, HttpSession session) throws Exception {
		//상세 보기  

		//사용자 정보를 가져옴.
		User user = (User) session.getAttribute("loginUser");
		model.addAttribute("user", user);
		
		//게시물을 가져옴
		Bbs v_bbs = bbsService.findBybbsIdx(bbsIdx); 
		
		model.addAttribute("bbs", v_bbs);
		return "bbs/show";

	}

	@RequestMapping("/{bbsIdx}/form")
	public String updateForm(@PathVariable String bbsIdx, Model model, HttpSession session) throws Exception {
		//수정 폼 

		//사용자 정보를 가져옴.
		User user = (User) session.getAttribute("loginUser");
		model.addAttribute("user", user);
		
		//게시물을 가져옴
		Bbs v_bbs = bbsService.findBybbsIdx(bbsIdx); 
		
		model.addAttribute("bbs", v_bbs);
		return "bbs/form";

	}
	
	@RequestMapping(value = "/{bbsIdx}", method = RequestMethod.POST)
	public String update(@PathVariable String bbsIdx, Bbs bbs, Model model, HttpSession session) throws Exception {
		
		log.debug("bbs update");
		
		log.debug("bbs : {}", bbs);		
		log.debug("bbs : bbsIdx =" + bbsIdx);
		
		
		// 게시판 수정 

		Date now = new Date(System.currentTimeMillis());		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		format.format(now);
		String writeDate = format.format(now);
		
		// 일자를 수정한다.
		bbs.setWriteDate(writeDate);
			
		try {
			bbsService.update(bbsIdx, bbs);
			return "redirect:/bbs/";
		}
		catch (PasswordMismatchException e)
		{
			log.debug("bbs : update error");
			
			//사용자 정보를 가져옴.
			User user = (User) session.getAttribute("loginUser");
			model.addAttribute("user", user);
			
			//게시물을 가져옴
			Bbs v_bbs = bbsService.findBybbsIdx(bbsIdx); 			
			model.addAttribute("bbs", v_bbs);
			
			model.addAttribute("errorMessage", "수정시 오류가 발생하였습니다.");
			
			return "bbs/form";			
		}
		
	}
	
	
	@RequestMapping("/{bbsIdx}/delete")
	public String delete(@PathVariable String bbsIdx, Model model, HttpSession session) throws Exception {
		// 삭제 처리.
		log.debug("bbs : delete");
		log.debug("bbs : bbsIdx =" + bbsIdx);
		
		try {		
			Bbs v_bbs = bbsService.findBybbsIdx(bbsIdx); 
			
			if (v_bbs != null) bbsService.remove(bbsIdx);
			
			return "redirect:/bbs/";
		}
		catch (Exception e) {
			log.debug("bbs : delete error");
			return "redirect:/bbs/";
		}

	}
	
	//delete

}
