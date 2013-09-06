package net.slipp.domain.qna;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import net.slipp.repository.qna.QuestionRepository;

import org.springframework.stereotype.Component;

@Component
public class QnaPopulator {
	@Resource (name = "questionRepository")
	private QuestionRepository questionRepository;
	
	@PostConstruct
	public void populate() {
		
		// String writerId, String writerName, String title, String contents, String plainTags
		questionRepository.save(new Question("jwkim", "김진원" , "글제목." , "글 내용. ", "java"));
		questionRepository.save(new Question("jwkim", "김진원" , "글제목1" , "글 내용1", "java"));
		questionRepository.save(new Question("jwkim", "김진원" , "글제목2" , "글 내용2", "java"));
		questionRepository.save(new Question("jwkim", "김진원" , "글제목3" , "글 내용3", "java"));
		questionRepository.save(new Question("jwkim", "김진원" , "글제목4" , "글 내용4", "java"));
		questionRepository.save(new Question("jwkim", "김진원" , "글제목5" , "글 내용5", "java"));

		questionRepository.save(new Question("jwkim", "김진원" , "글제목6" , "글 내용6", "java"));
		questionRepository.save(new Question("jwkim", "김진원" , "글제목7" , "글 내용7", "java"));
		questionRepository.save(new Question("jwkim", "김진원" , "글제목8" , "글 내용8", "java"));
		questionRepository.save(new Question("jwkim", "김진원" , "글제목9" , "글 내용9", "java"));
		questionRepository.save(new Question("jwkim", "김진원" , "글제목10" , "글 내용10", "java"));

		questionRepository.save(new Question("jwkim", "김진원" , "글제목11" , "글 내용11", "java"));
		questionRepository.save(new Question("jwkim", "김진원" , "글제목12" , "글 내용12", "java"));
		questionRepository.save(new Question("jwkim", "김진원" , "글제목13" , "글 내용13", "java"));
		questionRepository.save(new Question("jwkim", "김진원" , "글제목14" , "글 내용14", "java"));
		questionRepository.save(new Question("jwkim", "김진원" , "글제목15" , "글 내용15", "java"));		
		
	}
}
