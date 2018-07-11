package codesquad;

import codesquad.domain.Question;
import codesquad.domain.User;
import codesquad.repository.QuestionRepository;
import codesquad.repository.UserRepository;
import codesquad.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


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

    /*
    equals() 기능 공부 테스트
     */
    @Test
    public void testUserEqual(){
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user", userRepository.findByUserId("aa").get());


        User fromSession = (User) session.getAttribute("user");
        User fromRepo = userRepository.findByUserId("aa").get();
        fromRepo.setId((long)12);

        assertTrue(fromSession.equals(fromRepo));
        assertEquals(fromSession, fromRepo);


        User fromConstructor = new User();
        fromConstructor.setId(fromSession.getId());
        fromConstructor.setEmail(fromSession.getEmail());
        fromConstructor.setName(fromSession.getName());
        fromConstructor.setPassword(fromSession.getPassword());
        fromConstructor.setUserId(fromSession.getUserId());

//        assertTrue(fromSession.equals(fromConstructor));
//        assertEquals(fromSession, fromConstructor);


        fromConstructor.setId((long)12);
        assertTrue(fromSession.equals(fromConstructor));
        assertEquals(fromSession, fromConstructor);
    }
}
