package codesquad.web;

import codesquad.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private List<User> users = new ArrayList<>();

//    @PostMapping("/users")
//    public String create(String userId,
//                         String password,
//                         String name,
//                         String email, Model model){
//        User user = new User(userId, password, name, email);
//        users.add(user);
//        model.addAttribute("users", users);
//        return "/user/list";
//    }

//    //Setter 메소드 만든 후 개선
//    @PostMapping("/users")
//    public String create(User user, Model model){
//        users.add(user);
//        model.addAttribute("users", users);
//        return "/user/list";
//    }

    // 한가지 일만 하도록 메소드 분리
    @PostMapping("/users")
    public String create(User user){
        users.add(user);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String create(Model model){
        model.addAttribute("users", users);
        return "/user/list";
    }

    @GetMapping("/users/{index}")
    public String show(@PathVariable int index, Model model) {
        model.addAttribute("user", users.get(index));
        return "/user/profile";
    }

    // 예전버전에서의 사용법
//    @PostMapping("/users")
//    public ModelAndView create(String userId,
//                               String password,
//                               String name,
//                               String email){
//        User user = new User(userId, password, name, email);
//        users.add(user);
//        ModelAndView mav = new ModelAndView("user/list");
//        mav.addObject("users", users);
//        return mav;
//    }
}
