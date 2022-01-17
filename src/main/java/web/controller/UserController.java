package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model) {//получим всех людей из дао и сервиса и передадим на отображение
        model.addAttribute("users", userService.getAllUsers());
        return "/index";
    }

    @GetMapping("/{id}") //сюда можем поместить любое число и оно вставится в аргументы этого метода
    public String show(@PathVariable("id") int id, Model model) {  // с помощью этой аннотации мы сможем вытащить id
        model.addAttribute("user", userService.getUserById(id));

        //получим однго человека по id из дао и сервиса и передадим на отображение
        return "/show";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users/";
    }

    @GetMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));

        return "/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.update(id, user);

        return "redirect:/users/";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.removeUser(id);

        return "redirect:/users/";
    }
}
