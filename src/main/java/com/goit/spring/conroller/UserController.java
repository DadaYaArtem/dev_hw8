package com.goit.spring.conroller;

import com.goit.spring.model.User;
import com.goit.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired private UserService service;

    @GetMapping("/user")
    public String userMainPage(Model model){
        return "user/user";
    }

    @GetMapping("/users")
    public String showUserList(Model model){
        model.addAttribute("listUsers",service.listAll());
        return "user/getAllUsers";
    }

    @GetMapping("/users/new")
    public String showNewForm(Model model){
        model.addAttribute("user", new User());
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user){
        service.save(user);
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model){
        try {
            User user = service.get(id);
            model.addAttribute("user", user);
            return "user_form";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/users";
        }
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
            service.delete(id);
        return "redirect:/users";
    }

}
