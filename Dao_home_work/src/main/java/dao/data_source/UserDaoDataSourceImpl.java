package dao.data_source;

import dao.UserDao;
import models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserDaoDataSourceImpl implements UserDao {
    private static final String SQL_SELECT_ALL_USERS = "SELECT * FROM user_dao;";
    private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM user_dao WHERE id=?;";
    private static final String SQL_INSERT_USER = "INSERT INTO user_dao(name, age) VALUES(?,?);";
    private static final String SQL_DELETE_USER = "DELETE FROM user_dao WHERE id=?;";
    private static final String SQL_UPDATE_USER = "UPDATE user_dao SET name=?, age=? WHERE id=?;";
    
    private JdbcTemplate template;
    private PreparedStatement preparedStatement;
    private Map<Integer, User> userMap;
    
    private RowMapper<User> userRowMapper = new RowMapper<User>() {
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User(resultSet.getString("name"), resultSet.getInt("age"));
            user.setId(resultSet.getInt("id"));
            userMap.put(user.getId(), user);
            return user;
        }
    };

    public UserDaoDataSourceImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
        this.userMap = new HashMap<Integer, User>();
    }
    
    public List<User> findAllUsers() {
        return template.query(SQL_SELECT_ALL_USERS, new Object[]{}, userRowMapper);
        //return users.stream().collect(Collectors.toMap(i -> i.getId(),i -> i));
    }
    
    public User find(int id) {
        return template.queryForObject(SQL_SELECT_USER_BY_ID, new Object[]{id}, userRowMapper);
    }
    
    public boolean save(User user) {
        return template.update(SQL_INSERT_USER, new Object[]{user.getName(), user.getAge()},
                new int[]{Types.VARCHAR, Types.INTEGER}) > 0;
    }
    
    public boolean update(User user, User new_user) {
        return template.update(SQL_UPDATE_USER, new Object[]{new_user.getName(), new_user.getAge(), user.getId()},
                new int[]{Types.VARCHAR, Types.INTEGER, Types.INTEGER}) > 0;
    }
    
    public boolean delete(int id) {
        return template.update(SQL_DELETE_USER, new Object[]{id}, new int[]{Types.INTEGER}) > 0;
    }
}
