package net.slipp.domain.user;

import javax.annotation.Resource;

import net.slipp.repository.user.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Resource (name = "userRepository")
	private UserRepository userRepository;
	
	public void create(User user) throws ExistedUserException {
		
		logger.debug("사용자는 : " + user.getUserId());
		
		if (findUser(user.getUserId()) != null) {
			throw new ExistedUserException(user.getUserId() + "는 이미 존재하는 아이디입니다.");
		}

		userRepository.save(user);
	}

	public void update(User updatedUser) {
		User user = userRepository.findOne(updatedUser.getUserId());
		user.update(updatedUser);
		userRepository.save(user);
	}

	public void remove(String userId) {
		userRepository.delete(userId);
	}

	public User findUser(String userId) {
		// 사용자가 존재하는지 체크.
		return userRepository.findOne(userId);
	}

	public Iterable<User> finds() {
		return userRepository.findAll();
	}

	public boolean login(String userId, String password) throws PasswordMismatchException {
		// 로그인 인증시 처리한다.
		
		User user = findUser(userId);
		logger.debug("findUser : {}", user);
		if (!user.isMatchPassword(password)) {
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}
		return true;
	}
}
