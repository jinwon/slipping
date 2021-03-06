package net.slipp.domain.bbs;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.slipp.domain.user.User;
import net.slipp.repository.bbs.BbsRepository;
import net.slipp.web.bbs.BbsController;



@Service("bbsService")
@Transactional
public class BbsService {
	
	private static final Logger logger = LoggerFactory.getLogger(BbsController.class);

	@Resource (name = "bbsRepository")
	private BbsRepository bbsRepository;

	public Iterable<Bbs> findsBbs() {
		return bbsRepository.findAll();
	}

	public Iterable<Bbs> findsBbs(Pageable page) {
		return bbsRepository.findAll(page);
	}
	
	public void createBbs(User user, Bbs bbs, String fileName) {
		bbs.setFileName(fileName);
		bbs.writedBy(user);

		bbsRepository.save(bbs);
	}
	
	public Bbs findByBbsId(Long id) {
		return bbsRepository.findOne(id);
	}
	
	public void updateBbs(User user, Bbs newBbs) {
		Bbs bbs = bbsRepository.findOne(newBbs.getBbsId());

		bbs.writedBy(user);
		bbs.update(newBbs);

		bbs.writedBy(user);
		bbs.update(newBbs);
		
		bbsRepository.save(bbs);
	}
	
	public void delete(Long id) {
	
		bbsRepository.delete(id);	
	}
	
	public Page<Bbs> findByTitleLike(String title, Pageable page) {
	
		logger.debug("title = " + title);
		logger.debug("page_number = " + page.getPageNumber());
		
		return bbsRepository.findByTitleLike(title, page);
	}
	
}
