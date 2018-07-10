package codesquad.web;

import codesquad.repository.QuestionRepository;
import codesquad.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    IQuestionService questionService;

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("questions", questionService.getQuestionList());
        return "/qna/list";
    }
}
