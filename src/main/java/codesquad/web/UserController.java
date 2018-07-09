package codesquad.web;

import codesquad.domain.User;
import codesquad.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    private Map<String, User> users = new HashMap<String, User>();
    
    @Autowired
    private UserRepository userRepository;

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
        users.put(user.getUserId(), user);
        //userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String create(Model model){
        model.addAttribute("users", users.values());
        return "/user/list";
    }

    @GetMapping("/users/{userId}")
    public String show(@PathVariable String userId, Model model) {
        model.addAttribute("user", users.get(userId));
        return "/user/profile";
    }
    @GetMapping("/users/{userId}/form")
    public String update(@PathVariable String userId, Model model) {
        model.addAttribute("user", users.get(userId));
        return "/user/updateForm";
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
