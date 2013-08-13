package net.slipp.dao.bbs;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import net.slipp.domain.bbs.Bbs;
import net.slipp.support.AbstractDaoSupport;

@Repository("JdbcBbsDao")
public class JdbcBbsDao extends AbstractDaoSupport implements BbsDao{

	@Override
	public void insert(Bbs bbs) throws SQLException {
		
		String sql = "INSERT INTO BBS(bbsIdx, userId, bbsPassword, subject, content, writeDate) values(?,?,?,?,?,?) ";
			
		getJdbcTemplate().update(sql, new Object[] {bbs.getBbsIdx(), 
				bbs.getUserId(), bbs.getBbsPassword(),bbs.getSubject(), 
				bbs.getContent(), bbs.getWriteDate()});
	}
	
	@Override
	public void update(String bbsIdx, Bbs bbs) throws SQLException{
		String sql = "UPDATE BBS set userId = ?, subject = ?, content = ? where bbsIdx = ? ";
		
		getJdbcTemplate().update(sql, new Object[] {
				bbs.getUserId(), bbs.getSubject(), 
				bbs.getContent(), bbs.getBbsIdx() });
	}

	@Override
	public void remove(String bbsIdx) throws SQLException {
		String sql = "DELETE FROM BBS WHERE bbsIdx = ? ";
		getJdbcTemplate().update(sql, bbsIdx);
	}
	
	@Override
	public Bbs findBybbsIdx(String bbsIdx) throws SQLException {
		String sql = "SELECT * from BBS WHERE bbsIdx = ?";

		RowMapper<Bbs> rowMapper = new RowMapper<Bbs> () {
			@Override
			public Bbs mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Bbs(
						rs.getString("bbsIdx"), 
						rs.getString("userId"), 
						rs.getString("bbsPassword"),
						rs.getString("subject"),
						rs.getString("content"),
						rs.getString("writeDate"));
			}
		};
		
		return DataAccessUtils.uniqueResult(getJdbcTemplate().query(sql, rowMapper, bbsIdx));
	}
	
	@Override
	//전체 리스트를 구한다.
	public Map getBbsmap() {
		
		Map<String, Bbs> bbsmap = new HashMap<String, Bbs>();		
		
		String sql = "SELECT * from BBS";
		
		List<Map<String,Object>> rows = getJdbcTemplate().queryForList(sql);
		
		for(Map<String,Object> row : rows) {
			Bbs r_bbs = new Bbs();
			
			r_bbs.setBbsIdx((String) row.get("bbsIdx"));
			r_bbs.setUserId((String) row.get("userId"));
			r_bbs.setBbsPassword((String) row.get("bbsPassword"));
			r_bbs.setSubject((String)row.get("subject"));
			r_bbs.setContent((String)row.get("content"));
			r_bbs.setWriteDate((String)row.get("writeDate"));
			
			
			bbsmap.put(r_bbs.getBbsIdx(), r_bbs);
		}

		return bbsmap;
	}
}
