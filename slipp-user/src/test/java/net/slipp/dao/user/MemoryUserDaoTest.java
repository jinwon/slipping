package net.slipp.dao.user;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import net.slipp.domain.user.User;

import org.junit.Test;

public class MemoryUserDaoTest {

	@Test
	public void curd() throws Exception {
		User expected = new User("userId", "password", "name", "javajigi@email.com");
		MemoryUserDao userDao = new MemoryUserDao();
		userDao.insert(expected);

		User actual = userDao.findByUserId(expected.getUserId());
		assertThat(actual, is(expected));
	}
	
}