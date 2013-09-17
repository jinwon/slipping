package net.slipp.web.bbs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.slipp.domain.bbs.Bbs;
import net.slipp.domain.bbs.BbsService;
import net.slipp.domain.user.User;
import net.slipp.support.web.argumentresolver.LoginUser;
import net.slipp.web.bbs.BbsController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/bbs")
public class BbsController {
	private static final Logger logger = LoggerFactory.getLogger(BbsController.class);
	
	private static final int DEFAULT_PAGE_NO = 1;
	private static final int DEFAULT_PAGE_SIZE = 10;

	@Resource(name = "bbsService")
	private BbsService bbsService;
	
	@RequestMapping("")
	public String index(Model model, HttpServletRequest request, HttpSession session) throws NullPointerException {

		String s_title = "%";
		
		String s_type = (String) session.getAttribute("s_type");
		String s_value = (String) session.getAttribute("s_value");
				
		String check_type = "title";
		
		if ((s_type != null) && (s_value != null) )
		{
			if (s_type.equals(check_type)) {
				if (s_value != "") { s_title = s_value + "%";}
				else {
					logger.debug("value 값이 없습니다.");
				}
			}
			else {
				logger.debug("title 값이 없습니다.");
			}
		}
		
		logger.debug("title = " + s_title);
		
		// 목록 리스트를 표시한다.
		if (request.getParameter("page") == null)
		{
			if (s_title == "") {
				model.addAttribute("bbs", bbsService.findsBbs(createPageableForList(DEFAULT_PAGE_NO, DEFAULT_PAGE_SIZE)));
			} else {	
				model.addAttribute("bbs", bbsService.findByTitleLike(s_title, createPageableForList(DEFAULT_PAGE_NO, DEFAULT_PAGE_SIZE)));
				model.addAttribute("s_type", s_type);
				model.addAttribute("s_value", s_value);
			}	
		}
		else {
			
			int page = Integer.parseInt(request.getParameter("page"));

			if (s_title == "") {
				model.addAttribute("bbs", bbsService.findsBbs(createPageableForList(page, DEFAULT_PAGE_SIZE)));
			} else {	
				model.addAttribute("bbs", bbsService.findByTitleLike(s_title, createPageableForList(page, DEFAULT_PAGE_SIZE)));
				model.addAttribute("s_type", s_type);
				model.addAttribute("s_value", s_value);
			}
		}
		
		return "bbs/list";
	}
	

	private Pageable createPageableForList(int page, int size) {
		if (page <= 0) {
			page = 0;
		} else {
			page--;
		}

		Sort sort = new Sort(Direction.DESC, "createdDate");
		return new PageRequest(page, size, sort);
	}
	
	@RequestMapping("/form")
	public String createForm(@LoginUser User user, Model model) {
		
		//신규 게시물을 추가한다.
		
		model.addAttribute(new Bbs());
		return "bbs/form";
	}

	@RequestMapping("{id}/download")
	public void download(@LoginUser User user, @PathVariable Long id, Model model, HttpServletResponse response, HttpServletRequest request) throws IOException {

		if (user == null) {
			//유저 정보가 없을때 로그인 폼으로 이동한다.
			PrintWriter output = response.getWriter();
			output.println("로그인 후 이용하세요.");		
			return;
		}

		Bbs bbs =  bbsService.findByBbsId(id);
		
		String path = "/upload/" + bbs.getFileName();
		
		File dfile = new File(path);
	
		response.setContentType("application/x-download");
		response.setContentLength((int) dfile.length());
		
		response.setHeader("Content-Desposition", "attachment; filename=\"" + bbs.getFileName() + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		OutputStream out = response.getOutputStream();
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(dfile);
			FileCopyUtils.copy(fis, out);
		} finally {
			if (fis != null)
			try {
				fis.close();
			} catch (IOException ex) {
				
			}
		}
		
		out.flush();
		out.close();
	}	

	@RequestMapping("/{id}/form")
	public String updateForm(@LoginUser User user, @PathVariable Long id, HttpServletRequest request, Model model) {
		
		logger.debug("Question : 게시판 수정. ");
		
		if (user == null) {
			//유저 정보가 없을때 로그인 폼으로 이동한다.
			return "redirect:/user/login/form";
		}
		
		logger.debug("id = " + id);
		
		model.addAttribute("bbs", bbsService.findByBbsId(id));
		
		
		return "bbs/form";
	}
	
	@RequestMapping(value = "{id}/update")
	public String update(@LoginUser User user, @PathVariable Long id, Bbs bbs) {
		
		logger.debug("수정 bbsId = " + bbs.getBbsId());

		// 질문 수정시 로그인 체크.
		bbsService.updateBbs(user, bbs);
		
		return "redirect:/bbs/";
	}

	@RequestMapping(value = "{id}/delete")
	public String delete(@LoginUser User user, @PathVariable Long id) {
		//삭제 처리.
		
		logger.debug("삭제 bbsId = " + id);
		
		//첨부파일을 찾아 지운다.
		
		Bbs bbs = bbsService.findByBbsId(id);
		
		String path = "/upload/" + bbs.getFileName();
		
		File dfile = new File(path);
		dfile.delete();
		
		bbsService.delete(id);

		return "redirect:/bbs/";
	}
 	
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String create( @LoginUser User user, HttpServletRequest request, Bbs bbs, @RequestParam("file") MultipartFile upfile) {
		
		// 게시판 추가 처리.
		
		String o_filename = "";
		
		if (upfile.isEmpty())
		{
			return "redirect:/bbs/form?upload=fail";
		}
		
		
		//첨부파일 정보를 입력한다.
		
		o_filename = upfile.getOriginalFilename();
		
		//logger.debug("file : " + upfile.getName());
		//logger.debug("file : " + upfile.getSize());
		//logger.debug("file : " + upfile.getOriginalFilename());
		
		bbs.setFileName(o_filename);
		
		try {
		
		String path = "/upload/" + o_filename;
		
		upfile.transferTo(new File(path));

		} catch (Exception e) {
			logger.debug("file save error!! ");
			return "redirect:/bbs/form?upload=fail";
		}
		
		//신규 추가 처리.
		logger.debug("Bbs : {}", bbs);
		bbsService.createBbs(user, bbs, o_filename);
		
		return "redirect:/bbs/";
	}

	@RequestMapping("{id}")
	public String show(@PathVariable Long id, Model model) {
		
		//상세 보기.
		model.addAttribute("bbs", bbsService.findByBbsId(id));

		return "bbs/show";
	}

	
}
