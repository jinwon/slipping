package net.slipp.service.bbs;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import net.slipp.dao.bbs.BbsDao;
import net.slipp.dao.user.UserDao;
import net.slipp.domain.bbs.Bbs;
import net.slipp.service.user.ExistedUserException;
import net.slipp.service.user.UserService;

import org.junit.*;
import org.junit.Test.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class BbsServiceTest {
	
	private BbsService bbsService;
	
	private String bbsIdx;
	private String writeDate;
	
	@Mock private BbsDao bbsDao;
	
	@Before
	public void setup() {
		bbsService = new BbsService();
		bbsService.setBbsDao(bbsDao);
		
		bbsIdx = Long.toString(System.currentTimeMillis());
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date now = new Date(System.currentTimeMillis());
		
		format.format(now);
		writeDate = format.format(now);		
	}	
	
	@Test
	public void new_bbs() throws SQLException, ExistedBbsException {
		//신규 추가
		Bbs newbbs = new Bbs( bbsIdx, "user1", "1111", "subject", "content", writeDate);
		bbsService.insert(newbbs);
		
		when(bbsDao.findBybbsIdx(bbsIdx)).thenReturn(newbbs);		
	}

	@Test
	public void check_exist_key() throws SQLException, ExistedBbsException {
		//존재하는지 체크 
		Bbs newbbs = new Bbs( bbsIdx, "user1", "1111", "subject", "content", writeDate);
		
		bbsService.insert(newbbs);
		
		when(bbsDao.findBybbsIdx(bbsIdx)).thenReturn(newbbs);		
		
		Bbs existbbs = bbsService.findBybbsIdx(bbsIdx);
	
		assertEquals(newbbs.getSubject(), existbbs.getSubject());
		assertEquals(newbbs.getContent(), existbbs.getContent());
		assertEquals(newbbs.getWriteDate(), existbbs.getWriteDate());
		
	}

	@Test
	public void check_update() throws SQLException, ExistedBbsException, PasswordMismatchException {
		// 업데이트 체크 
		Bbs newbbs = new Bbs( bbsIdx, "user1", "1111", "subject", "content", writeDate);
		
		bbsService.insert(newbbs);
		
		when(bbsDao.findBybbsIdx(bbsIdx)).thenReturn(newbbs);		
		
		Bbs existbbs = bbsService.findBybbsIdx(bbsIdx);	
		
		Bbs updatebbs = new Bbs( bbsIdx, "user1", "1111", "subject11", "content22", writeDate);
		bbsService.update(bbsIdx, updatebbs);
		
		Bbs checkbbs = bbsService.findBybbsIdx(bbsIdx);
		
		assertEquals(updatebbs.getSubject(), checkbbs.getSubject());
		assertEquals(updatebbs.getContent(), checkbbs.getContent());
	}

	@Test(expected=PasswordMismatchException.class)
	public void check_비밀번호오류() throws SQLException, ExistedBbsException, PasswordMismatchException {
		Bbs newbbs = new Bbs( bbsIdx, "user1", "1111", "subject", "content", writeDate);
		
		bbsService.insert(newbbs);

		when(bbsDao.findBybbsIdx(bbsIdx)).thenReturn(newbbs);
		
		Bbs updatebbs = new Bbs( bbsIdx, "user1", "222", "subject11", "content22", writeDate);
		bbsService.update(bbsIdx, updatebbs);
		
	}

	@Test
	public void check_삭제() throws SQLException, ExistedBbsException {
		Bbs newbbs = new Bbs( bbsIdx, "user1", "1111", "subject", "content", writeDate);
		
		bbsService.insert(newbbs);

		bbsService.remove(newbbs.getBbsIdx());
		
		verify(bbsDao).remove(newbbs.getBbsIdx());
		
		assertNull(bbsService.findBybbsIdx(newbbs.getBbsIdx()));
		
	}
}
