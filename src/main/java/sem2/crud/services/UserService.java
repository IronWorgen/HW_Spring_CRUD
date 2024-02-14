package sem2.crud.services;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import sem2.crud.configs.ApplicationConfig;
import sem2.crud.model.User;
import org.springframework.stereotype.Service;
import sem2.crud.repositories.ArchiveRepository;
import sem2.crud.repositories.UserRepository;
import sem2.crud.repositories.iRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private ApplicationConfig applicationConfig;
    /**
     * репозиторий H2
     */
    private final iRepository userRepository;

    /**
     * репозиторий Mysql
     */
    private final ArchiveRepository archiveRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }


    public User findUserById(int id) {
        return userRepository.findUserById(id);
    }

    /**
     * Сохранить пользователя во временную базу данных H2.
     * для удобства проверки домашнего задания закомментировал строку №44,
     * чтобы не было необходимости запускать контейнер Docker с MySQL.
     *
     * @param user - пользователь для сохранения в БД
     * @return - user
     */
    public User saveUser(User user) {
        if(applicationConfig.isEnableArchiveDB()){
            archiveRepository.saveUser(user);
        }
        return userRepository.save(user);
    }

    /**
     * обновить данные пользователя в БД
     *
     * @param user
     */
    public void update(User user) {
        userRepository.update(user);
    }

    /**
     * удалить пользователя
     *
     * @param id - id пользователя
     */
    public void delete(int id) {
        userRepository.delete(id);
    }
}
