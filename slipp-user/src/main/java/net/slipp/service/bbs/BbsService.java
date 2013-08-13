package net.slipp.service.bbs;

import java.sql.SQLException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import net.slipp.dao.bbs.BbsDao;
import net.slipp.dao.user.UserDao;
import net.slipp.domain.bbs.Bbs;
import net.slipp.service.bbs.PasswordMismatchException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class BbsService {
	private static Logger log = LoggerFactory.getLogger(BbsService.class);
	
	@Resource(name="JdbcBbsDao")
	private BbsDao bbsDao;
	
	public void setBbsDao(BbsDao bbsDao) {
		this.bbsDao = bbsDao;
	}
	
	@PostConstruct
	public void initialize() {
		log.debug("initialize");
	}
	
	@PreDestroy
	public void destroy() {
		log.debug("destroy");
		
	}
	
	
	public Bbs insert(Bbs bbs) throws SQLException, ExistedBbsException {
		//신규 추가 처리 
		log.debug("Bbs : {}", bbs);
						
		Bbs existed = bbsDao.findBybbsIdx(bbs.getBbsIdx());
		if (existed != null) {
			throw new ExistedBbsException(bbs.getBbsIdx());
		}

		log.debug("Bbs insert call");
		
		bbsDao.insert(bbs);
		return bbs;
	}
	
	public Bbs findBybbsIdx(String bbsIdx) throws SQLException {
		return bbsDao.findBybbsIdx(bbsIdx);
	}
	
	
	public Map getList() {
		// 전체 리스트를 구한다.
		return bbsDao.getBbsmap();
	}

	public void update(String bbsIdx, Bbs updateBbs) throws SQLException, PasswordMismatchException{
		// TODO Auto-generated method stub
		Bbs bbs = findBybbsIdx(bbsIdx);
		
		if (bbs == null) {
			throw new NullPointerException(bbsIdx + " user doesn't existed.");
		}
		
		//패스워드 체크 
		bbs.update(updateBbs);
		
		//데이타베이스 업데이트.
		bbsDao.update(bbsIdx,  updateBbs);
	}

	public void remove(String bbsIdx) throws SQLException {
		bbsDao.remove(bbsIdx);
	}
	
	
}
