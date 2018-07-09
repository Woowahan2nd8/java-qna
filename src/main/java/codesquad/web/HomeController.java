package codesquad.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String list(Model model){
        model.addAttribute("questions", QnaController.questions);
        return "/qna/list";
    }
}
