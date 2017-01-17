package dao.data_source;

import dao.AutoDao;
import models.Auto;
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

public class AutoDaoDataSourceImpl implements AutoDao {
    private static final String SQL_SELECT_ALL_AUTOS = "SELECT * FROM AutoDao;";
    private static final String SQL_SELECT_AUTO_BY_ID = "SELECT * FROM AutoDao WHERE id=?;";
    private static final String SQL_INSERT_AUTO = "INSERT INTO AutoDao(model, color, userId) VALUES(?,?,?);";
    private static final String SQL_DELETE_AUTO = "DELETE FROM AutoDao WHERE id=?;";
    private static final String SQL_UPDATE_AUTO = "UPDATE AutoDao SET model=?, color=?, userId=? WHERE id=?;";
    private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM UserDao WHERE id=?;";
    
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private Map<Integer, Auto> autoMap;
    private JdbcTemplate template;
    
    public AutoDaoDataSourceImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
        autoMap = new HashMap<Integer, Auto>();
    }
    
    private RowMapper<Auto> autoRowMapper = new RowMapper<Auto>() {
        public Auto mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = template.query(SQL_SELECT_USER_BY_ID, new Object[], );
            
            Auto auto = new Auto(resultSet.getString("model"), resultSet.getString("color"),
                    resultSet.getInt("userId"));
            auto.setId(resultSet.getInt("id"));
            autoMap.put(auto.getId(), auto);
            return auto;
        }
    };
    
    @Override
    public List<Auto> findAll() {
        return template.query(SQL_SELECT_ALL_AUTOS, new Object[]{}, autoRowMapper);
    }
    
    public Auto find(int id) {
        template.queryForObject(SQL_SELECT_AUTO_BY_ID, new Object[]{id}, autoRowMapper);
        
        return autoMap.get(id);
    }
    
    public int save(Auto auto) {
        return template.update(SQL_INSERT_AUTO, new Object[]{
                auto.getModel(),
                auto.getColor(),
                auto.getUser()
        },
        new int[]{
                Types.VARCHAR,
                Types.VARCHAR,
                Types.INTEGER
        }) > 0;
    }
    
    @Override
    public int update(int autoId, Auto newAuto) {
        return template.update(SQL_UPDATE_AUTO, new Object[]{
                newAuto.getModel(),
                newAuto.getColor(),
                newAuto.getUser(),
                autoId
        }, new int[]{
                Types.VARCHAR,
                Types.VARCHAR,
                Types.INTEGER,
                Types.INTEGER
        });
    }
    
    @Override
    public boolean delete(int id) {
        return template.update(SQL_DELETE_AUTO, new Object[]{id}, new int[]{Types.INTEGER}) > 0;
    }
}