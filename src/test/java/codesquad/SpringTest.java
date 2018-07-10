package codesquad;

import codesquad.domain.Question;
import codesquad.repository.QuestionRepository;
import codesquad.repository.UserRepository;
import codesquad.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;


//@RunWith(QnaApplication)
//@DataJpaTest
@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DataJpaTest
public class SpringTest {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    public void testTime(){
       System.out.println(userRepository.toString());
       Question question = new Question(userRepository.findByUserId("aa").get(), "A", "B");
       assertThat(question).isNotNull();
       questionRepository.save(question);
       assertThat(questionRepository.findById(new Long(1))).isNotNull();
    }
    @Test
    public void userServiceTest(){
        UserService service = new UserService(userRepository);

        assertThat(service.getUserByUserId("aa")).isNotNull();
    }
}
