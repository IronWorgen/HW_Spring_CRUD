package sem2.crud.repositories;

import lombok.RequiredArgsConstructor;
import sem2.crud.configs.DbUsersQuery;
import sem2.crud.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository implements iRepository {

    private final JdbcTemplate jdbcTemplate;
    private final DbUsersQuery dbUsersQuery;

    public List<User> findAll() {
        String sql = dbUsersQuery.getFindAll();
        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            return rowObject;
        };
        return jdbcTemplate.query(sql, userRowMapper);
    }

    public User findUserById(int id) {
        String sql = dbUsersQuery.getFindUserById();
        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            return rowObject;
        };
        return jdbcTemplate.queryForObject(sql, userRowMapper, id);
    }


    public User save(User user) {
        String sql = dbUsersQuery.getSave();
        jdbcTemplate.update(sql, user.getFirstName(), user.getLastName());
        return user;
    }


    public void update(User user) {
        String sql = dbUsersQuery.getUpdate();
        jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getId());
    }


    public void delete(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

}
