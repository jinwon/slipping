package net.slipp.web.bbs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.slipp.domain.bbs.Bbs;
import net.slipp.domain.bbs.BbsService;
import net.slipp.domain.qna.Question;
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
	public String index(Model model, HttpServletRequest request) {

		// 목록 리스트를 표시한다.
		if (request.getParameter("page") == null)
		{
			model.addAttribute("bbs",
					bbsService.findsBbs(createPageableForList(DEFAULT_PAGE_NO, DEFAULT_PAGE_SIZE)));
		}
		else {
			
			int page = Integer.parseInt(request.getParameter("page"));
			
			model.addAttribute("bbs",
				bbsService.findsBbs(createPageableForList(page, DEFAULT_PAGE_SIZE)));
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
		
		return "redirect:/bbs";
	}
	
	@RequestMapping("{id}")
	public String show(@PathVariable Long id, Model model) {
		
		//상세 보기.
		model.addAttribute("bbs", bbsService.findByBbsId(id));

		return "bbs/show";
	}
	
	
}
