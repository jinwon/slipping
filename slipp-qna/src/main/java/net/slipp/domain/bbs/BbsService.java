package net.slipp.domain.bbs;


import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.slipp.domain.user.User;
import net.slipp.repository.bbs.BbsRepository;



@Service("bbsService")
@Transactional
public class BbsService {

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
	
}
