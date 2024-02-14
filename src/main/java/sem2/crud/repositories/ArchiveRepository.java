package sem2.crud.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;
import sem2.crud.configs.DbArchiveQuery;
import sem2.crud.configs.DbUsersQuery;
import sem2.crud.model.User;

import javax.sql.DataSource;
import java.net.ConnectException;
import java.sql.SQLException;

/**
 * репозиторий позволяющий работать с базой данных MYSQL
 */
@Repository
public class ArchiveRepository {

    private final JdbcTemplate jdbcTemplate;

    private  final DbArchiveQuery dbArchiveQuery;

    public ArchiveRepository(@Autowired DbArchiveQuery dbArchiveQuery) {
        DataSource dataSource = DataSourceBuilder.create().username("root")
                .password("password")
                .url("jdbc:mysql://localhost:8086/Users")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.dbArchiveQuery = dbArchiveQuery;
    }

    /**
     * сохранить пользователя в базе данных MySQL
     *
     * @param user - пользователь
     */
    public void saveUser(User user) {
        String sql = dbArchiveQuery.getSaveUser();
        try {
            jdbcTemplate.update(sql, user.getFirstName(), user.getLastName());
        } catch (RuntimeException e) {
            try {
                createTableUsers();
                jdbcTemplate.update(sql, user.getFirstName(), user.getLastName());
            } catch (RuntimeException t) {
                System.out.println("не удается подключиться к базе данных MySql");
            }
        }
    }


    /**
     * если в базе данных нет таблицы `users` создать ее
     */
    private void createTableUsers() {
        String sql = dbArchiveQuery.getCreateTableUsers();
        jdbcTemplate.update(sql);
    }
}
