package net.slipp.service.bbs;

import java.sql.SQLException;

import net.slipp.dao.bbs.BbsDao;
import net.slipp.domain.bbs.Bbs;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BbsService {
	private static Logger log = LoggerFactory.getLogger(BbsService.class);
	
	public Bbs insert(Bbs bbs) throws SQLException, ExistedBbsException {
		log.debug("Bbs : {}", bbs);
		BbsDao bbsDao = new BbsDao();
		
		
		
		Bbs existed = bbsDao.findBybbsIdx(bbs.getBbsIdx());
		if (existed != null) {
			throw new ExistedBbsException(bbs.getBbsIdx());
		}

		bbsDao.insert(bbs);
		return bbs;
	}

}
