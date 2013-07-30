package net.slipp.dao.bbs;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import net.slipp.domain.bbs.Bbs;

public class BbsDao {
	private static Map<String, Bbs> bbsmap = new HashMap<String, Bbs>();
	
	public void insert(Bbs bbs) throws SQLException {
		bbsmap.put(bbs.getBbsIdx(), bbs);
	}

	public Bbs findBybbsIdx(String bbsIdx) throws SQLException {
		return bbsmap.get(bbsIdx);
	}
}
