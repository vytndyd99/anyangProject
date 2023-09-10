package com.anyangProject.anyangNoticeBoardProject;

import com.anyangProject.anyangNoticeBoardProject.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AnyangNoticeBoardProjectApplicationTests {

	@Autowired
	private QuestionService questionService;
	@Test
	void contextLoads() {
		for(int i = 0 ; i < 20 ; i++) {
			String subject = String.format("테스트 데이터입니다: [%03d]", i);
			String content = "내용 무";
			this.questionService.create(subject, content, null);
		}
	}

}
