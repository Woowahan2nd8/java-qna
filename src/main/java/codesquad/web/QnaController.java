package codesquad.web;

import codesquad.domain.Question;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class QnaController {
    public static List<Question> questions = new ArrayList<>();

    @PostMapping("/questions")
    public String create(Question question) {
//
        question.setWriteTime(currentTime());

        if(addQuestion(question))
            return "redirect:/";

        return "redirect:/error"+"/{}";

    }

    @GetMapping("/questions/form")
    public String form(){
        return "qna/form";
    }

    @GetMapping("/questions/{index}")
    public String view(@PathVariable int index, Model model) {
        model.addAttribute("question",questions.get(index));
        return "/qna/show";
    }

    private boolean addQuestion(Question question){
        questions.add(question);
        return true;
    }

    private String currentTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String time =  dtf.format(LocalDateTime.now());
        System.out.println("make time : " + time);
        return time;
    }

}
