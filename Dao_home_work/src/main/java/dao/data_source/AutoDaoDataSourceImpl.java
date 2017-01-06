package dao.data_source;

import dao.AutoDao;
import models.Auto;
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
    private static final String SQL_SELECT_ALL_AUTOS = "SELECT * FROM auto_dao;";
    private static final String SQL_SELECT_AUTO_BY_ID = "SELECT * FROM auto_dao WHERE id=?;";
    private static final String SQL_INSERT_AUTO= "INSERT INTO auto_dao(model, color, user_id) VALUES(?,?,?);";
    private static final String SQL_DELETE_AUTO = "DELETE FROM auto_dao WHERE id=?;";
    private static final String SQL_UPDATE_AUTO = "UPDATE auto_dao SET model=?, color=?, user_id=? WHERE id=?;";
    
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
            Auto auto = new Auto(resultSet.getString("model"), resultSet.getString("color"), resultSet.getInt("user_id"));
            auto.setId(resultSet.getInt("id"));
            autoMap.put(auto.getId(), auto);
            return auto;
        }
    };
    
    public List<Auto> findAllAutos() {
        return template.query(SQL_SELECT_ALL_AUTOS, new Object[]{}, autoRowMapper);
    }
    
    public Auto find(int id) {
        template.queryForObject(SQL_SELECT_AUTO_BY_ID, new Object[]{id}, autoRowMapper);
        
        return autoMap.get(id);
    }
    
    public boolean save(Auto auto) {
        return template.update(SQL_INSERT_AUTO, new Object[]{auto.getModel(), auto.getColor(), auto.getUserId()},
                new int[]{Types.VARCHAR, Types.VARCHAR, Types.INTEGER}) > 0;
    }
    
    public boolean update(Auto auto, Auto new_auto) {
        return template.update(SQL_UPDATE_AUTO, new Object[]{new_auto.getModel(), new_auto.getColor(),
                new_auto.getUserId(), auto.getId()}, new int[]{Types.VARCHAR, Types.VARCHAR, Types.INTEGER,
                Types.INTEGER}) > 0;
    }
    
    public boolean delete(int id) {
        return template.update(SQL_DELETE_AUTO, new Object[]{id}, new int[]{Types.INTEGER}) > 0;
    }
}