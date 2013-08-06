package net.slipp.dao.bbs;

import java.sql.SQLException;
import java.util.Map;

import net.slipp.domain.bbs.Bbs;

public interface BbsDao {
	void insert(Bbs bbs) throws SQLException;
	
	void remove(String bbsIdx) throws SQLException;
	
	Bbs findBybbsIdx(String bbsIdx) throws SQLException; 
	
	Map getBbsmap();
}
