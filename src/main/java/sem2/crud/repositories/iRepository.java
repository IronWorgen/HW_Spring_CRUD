package sem2.crud.repositories;

import sem2.crud.model.User;

import java.util.List;

public interface iRepository {
    /**
     * найти всех пользователей в БД
     * @return список пользователей
     */
    List<User> findAll();

    /**
     * поиск пользователя по id
     * @param id - id пользователя
     * @return User
     */

    User findUserById(int id);

    /**
     * добавить пользователя в БД
     * @param user
     * @return
     */

    User save(User user);

    /**
     * изменить пользователя
     * @param user - пользователь с изменёнными данными и не измененным id.
     */

    void update(User user);

    /**
     * удаление пользователя из БД
     * @param id - id пользователя
     */
    void delete(int id);
}
