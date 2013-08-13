package net.slipp.dao.bbs;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import net.slipp.domain.bbs.Bbs;

import org.junit.Test;

public class BbsDaoTest {

	@Test
	public void insert() throws SQLException {
		
		String bbsIdx = Long.toString(System.currentTimeMillis());
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date now = new Date(System.currentTimeMillis());
		
		format.format(now);
		String writeDate = format.format(now);	
		
		Bbs newbbs = new Bbs( bbsIdx, "user1", "1111", "subject", "content", writeDate);
		
		JdbcBbsDao bbsDao = new JdbcBbsDao();
		bbsDao.insert(newbbs);
		
		//입력된 값이 존재하는지 체크함.
		Bbs check_bbs = bbsDao.findBybbsIdx(newbbs.getBbsIdx());
		
		assertThat(check_bbs, is(newbbs));
		
	}
}
