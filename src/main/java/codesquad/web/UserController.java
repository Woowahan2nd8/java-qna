package codesquad.web;

import codesquad.domain.User;
import codesquad.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private List<User> users = new ArrayList();

    @Autowired
    private UserRepository userRepository;

    @PostMapping("")
    public String create(User user){
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("")
    public String create(Model model){
        model.addAttribute("users", user);
        return "/user/list";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model){
        model.addAttribute("user", users.get(id));
        return "/user/profile";
    }
}
