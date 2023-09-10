package com.anyangProject.anyangNoticeBoardProject.service;

import com.anyangProject.anyangNoticeBoardProject.DataNotFoundException;
import com.anyangProject.anyangNoticeBoardProject.entitiy.Answer;
import com.anyangProject.anyangNoticeBoardProject.entitiy.Question;
import com.anyangProject.anyangNoticeBoardProject.entitiy.UserEntity;
import com.anyangProject.anyangNoticeBoardProject.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public Answer create(Question question, String content,
                       UserEntity author) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setAuthor(author);
        this.answerRepository.save(answer);
        return answer;
    }

    public Answer getAnswer(Integer id) {
        Optional<Answer> answer = this.answerRepository.findById(id);
        if(answer.isPresent()) {
            return answer.get();
        } else {
            throw new DataNotFoundException("answer not found");
        }
    }

    public void modify(Answer answer, String content) {
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }

    public void delete(Answer answer) {
        this.answerRepository.delete(answer);
    }

    public void vote(Answer answer, UserEntity userEntity) {
        answer.getVoter().add(userEntity);
        this.answerRepository.save(answer);
    }
}
