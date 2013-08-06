package net.slipp.dao.bbs;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Repository;

import net.slipp.dao.user.UserDao;
import net.slipp.domain.bbs.Bbs;

@Repository("jdbcBbsDao")
public class JdbcBbsDao implements BbsDao{

	@Override
	public void insert(Bbs bbs) throws SQLException {
		//
	}

	@Override
	public void remove(String bbsIdx) throws SQLException {
		//
	}
	
	@Override
	public Bbs findBybbsIdx(String bbsIdx) throws SQLException {
		return null;
	}
	
	@Override
	//전체 리스트를 구한다.
	public Map getBbsmap() {
		return null;
	}
}
