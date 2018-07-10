package codesquad.service;

import codesquad.domain.Question;
import codesquad.domain.User;

public interface IQuestionService {
    void addQuestion(String title, String contents, User writer);
    Question getQuestionById(long id);
    Question getModifiableQuestion(long userId, long questionId);
    void deleteQuestionById(long userId,long questionId);
    void updateQuestion(long id, Question question);
    Iterable<Question> getQuestionList();

}
