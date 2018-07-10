package codesquad.web;

import codesquad.domain.*;
import codesquad.service.IQuestionService;
import codesquad.service.IUserService;
import codesquad.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static codesquad.web.UserController.SESSION_KEY;


@Controller
@RequestMapping("/questions")
public class QnaController {

    @Autowired
    IUserService userService;

    @Autowired
    IQuestionService questionService;

    @GetMapping("/form")
    public String registForm(HttpSession session){
        Object sessionedUserObject = session.getAttribute(SESSION_KEY);
        if(sessionedUserObject == null){
            return "redirect:/users/login/form";
        }
        return "/qna/form";
    }
    @PostMapping
    public String create(String title, String contents, HttpSession session) {

        User writeUser = (User)session.getAttribute(SESSION_KEY);
        questionService.addQuestion(title, contents, writeUser);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable long id, Model model) {
        model.addAttribute("question", questionService.getQuestionById(id));
        return "/qna/show";
    }

    @GetMapping("/{id}/form")
    public String viewForUpdate(@PathVariable long id, Model model, HttpSession session) {
        Object sessionedUserObject = session.getAttribute(SESSION_KEY);
        if(sessionedUserObject == null){
            return "redirect:/users/login/form";
        }
        User sessionedUser = userService.convertObjectToUser(sessionedUserObject);
        model.addAttribute("question", questionService.getModifiableQuestion(sessionedUser.getId(), id));
        return "/qna/updateForm";
    }

    @PutMapping("/{id}")
    public String updateQuestion(@PathVariable long id, Question question, HttpSession session) {
        User sessionedUser = userService.convertObjectToUser(session.getAttribute(SESSION_KEY));
        questionService.updateQuestion(sessionedUser.getId(), question);
        return "redirect:/questions/{id}";
    }

    @DeleteMapping("/{id}")
    public String deleteQuestion(@PathVariable long id,HttpSession session) {
        User sessionedUser = userService.convertObjectToUser(session.getAttribute(SESSION_KEY));
        questionService.deleteQuestionById(sessionedUser.getId(), id);
        return "redirect:/";
    }


}
