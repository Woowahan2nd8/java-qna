package codesquad.service;

import codesquad.domain.Question;
import codesquad.repository.QuestionRepository;
import codesquad.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class QuestionService{
    @Autowired
    QuestionRepository questionRepository;

    public void addQuestion(String title, String contents, User writer) {
        Question newQuestion = new Question(writer, title, contents);
        questionRepository.save(newQuestion);
    }
    /*
        Question -> 바꾸기 위한 데이터 포함
        id         -> 업데이트 시킬 대상 식별자
     */
    public void updateQuestion(long id, Question question){
        Question targetQuestion = getQuestionById(id);
        targetQuestion.updateData(question);
        System.out.println(targetQuestion);
        questionRepository.save(targetQuestion);
    }
    /*
    User user = userRepository.findByUserId(userId)
    			.filter(u -> u.matchPassword(password))
   				.orElseThrow(IllegalArgumentException::new);
     */
    public Question getQuestionById(long id){
        return questionRepository.findById(id)
                .orElseThrow( () -> new CustomException(CustomErrorMessage.NOT_VALID_PATH));
    }

    public Question getModifiableQuestion(long userId, long questionId){ //1. Question /
        Question targetQuestion = getQuestionById(questionId);
        if(targetQuestion.unEqualWriter(userId)){
            throw new CustomException(CustomErrorMessage.NOT_AUTHORIZED);
        }
        return targetQuestion;
    }

    public void deleteQuestionById(long userId,long questionId){
        questionRepository.delete(getModifiableQuestion(userId, questionId));
    }

    public Iterable<Question> getQuestionList(){
        return questionRepository.findAll();
    }
}
