package codesquad.service;

import codesquad.domain.Question;
import codesquad.repository.QuestionRepository;
import codesquad.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class QuestionService implements IQuestionService{
    @Autowired
    QuestionRepository questionRepository;
    public void checkFields(String title, String contents){
        if(contents == null || contents == null || contents.equals("") || title.equals("")) {
            throw new CustomException(CustomErrorMessage.BLANK);
        }
    }
    public void addQuestion(String title, String contents, User writer) {
        checkFields(title, contents); // *
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
        questionRepository.save(targetQuestion);
    }
    public Question getQuestionById(long id){
        Optional<Question> maybeQuestion = questionRepository.findById(id);
        if (!maybeQuestion.isPresent() ) {
            throw new CustomException(CustomErrorMessage.NOT_VALID_PATH);
        }
        return maybeQuestion.get();
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
