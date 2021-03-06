package net.slipp.support;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public class AbstractDaoSupport extends JdbcDaoSupport {

	@Autowired
	private DataSource dataSource;
	
	@PostConstruct
	public void initialize() {
		//
		// 중요!!
		//데이타 소스를 설정한
		setDataSource(dataSource);
		
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("user.sql"));
		DatabasePopulatorUtils.execute(populator, dataSource);		
	}
	
}
