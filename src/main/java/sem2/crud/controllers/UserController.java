package sem2.crud.controllers;

import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.PathVariable;
import sem2.crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import sem2.crud.services.UserService;

import java.util.List;

@Controller
@Log
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * полуить список всех пользователей в БД
     *
     * @param model модель для шаблонизатора
     * @return - страница со списком пользователей
     */
    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        System.out.println();
        model.addAttribute("users", users);

        log.info("выведен список пользователей");
        return "user-list";
    }

    /**
     * получить форму создания нового пользователя
     *
     * @param user
     * @return страница создания пользователя
     */
    @GetMapping("/user-create")
    public String createUserForm(User user) {
        return "user-create";
    }


    /**
     * создать нового пользователя в бд
     *
     * @param user
     * @return - перенаправляет на страницу со списком пользователей
     */
    @PostMapping("/user-create")
    public String createUser(User user) {
        userService.saveUser(user);
        log.info("добавлен пользователь:"+user);
        return "redirect:/users";
    }

    /**
     * получить форму для обновления данных пользователя
     *
     * @param id    - id пользователя
     * @param model модель для шаблонизатора
     * @return
     */
    @GetMapping("/user-update/{id}")
    public String updateGet(@PathVariable("id") int id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute(user);
        return "user-update";
    }


    /**
     * обновить данные пользователя в БД
     *
     * @param user - пользователь
     * @return - перенаправляет на страницу со списком пользователей
     */
    @PostMapping("/user-update")
    public String updateUser(User user) {
        userService.update(user);
        log.info("данные пользователя "+user+ " изменены");
        return "redirect:/users";
    }


    /**
     * Удалить пользователя из БД
     *
     * @param id -id пользователя
     * @return - перенаправляет на страницу со списком пользователей
     */
    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        log.info("пользователь id="+id+ " удален");
        return "redirect:/users";
    }
}
