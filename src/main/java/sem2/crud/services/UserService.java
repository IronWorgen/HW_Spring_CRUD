package sem2.crud.services;

import lombok.RequiredArgsConstructor;
import sem2.crud.model.User;
import org.springframework.stereotype.Service;
import sem2.crud.repositories.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;


    public List<User> findAll() {
        return repository.findAll();
    }


    public User findUserById(int id) {
        return repository.findUserById(id);
    }


    public User saveUser(User user) {
        return repository.save(user);
    }


    public void update(User user) {
        repository.update(user);
    }


    public void delete(int id) {
        repository.delete(id);
    }
}
