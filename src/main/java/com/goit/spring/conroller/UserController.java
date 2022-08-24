package com.goit.spring.conroller;

import com.goit.spring.model.User;
import com.goit.spring.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/user")
    public String userMainPage(Model model) {
        return "user/user_main";
    }

    @GetMapping("/user/list")
    public String showUserList(Principal principal, Model model) throws Exception {
        System.out.println(principal.getName());
        model.addAttribute("listUsers", service.listAll());
        model.addAttribute("listSize", service.listAll().size());
        if (service.getRoleByEmail(principal.getName()) != null) {
            model.addAttribute("activeUser", service.getByEmail(principal.getName()));
        } else {
            model.addAttribute("activeUser", new User());
        }
        return "user/getAllUsers";
    }

    @RequestMapping("/user/user_info")
    public String showUserById(Principal principal, Model model, HttpServletRequest req) {
        try {
            model.addAttribute("user", service.get(Long.valueOf(req.getParameter("userId"))));
            model.addAttribute("activeUser", service.getByEmail(principal.getName()));
            return "user/getUserById";
        } catch (Exception e) {
            return "redirect:/user/list";
        }
    }

    @PostMapping(value = "/user/new")
    public String showNewForm(Model model, Principal principal) {
        if (service.getRoleByEmail(principal.getName()) != null) {
            if (service.getRoleByEmail(principal.getName()).equals("Admin")) {
                model.addAttribute("user", new User());
                return "user/user_form";
            } else {
                return "forbidden";
            }
        }
        return "redirect:/user/list";
    }

    @PostMapping("/user/save")
    public String saveUser(User user) {
        service.save(user);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/user/edit", method = {RequestMethod.GET, RequestMethod.POST})
    public String showEditForm(Principal principal, ModelMap model, HttpServletRequest req) {
        if (service.getRoleByEmail(principal.getName()) != null) {
            if (service.getRoleByEmail(principal.getName()).equals("Admin")) {
                try {
                    Long id = Long.valueOf(req.getParameter("userId"));
                    User user = service.get(id);
                    model.addAttribute("user", user);
                    return "user/user_form";
                } catch (Exception e) {
                    e.printStackTrace();
                    return "redirect:/user/list";
                }
            } else {
                return "forbidden";
            }
        }
        return "redirect:/user/list";
    }

    @PostMapping("/user/delete")
    public String deleteUser(Principal principal, HttpServletRequest req) {
        if (service.getRoleByEmail(principal.getName()) != null) {
            if (service.getRoleByEmail(principal.getName()).equals("Admin")) {
                Long id = Long.valueOf(req.getParameter("userId"));
                service.delete(id);
                return "redirect:/user/list";
            } else {
                return "forbidden";
            }
        }
        return "redirect:/user/list";
    }

}
