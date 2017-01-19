package genome.fabrics;

import genome.models.Auto;
import genome.models.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateFabric {
    private static HibernateFabric instance;
    private Configuration configuration;
    private SessionFactory sessionFactory;
    
    private HibernateFabric() {
        configuration = new Configuration();
        
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/user_auto_dao");
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.username", "den");
        configuration.setProperty("hibernate.connection.password", "123321");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Auto.class);
        
        sessionFactory = configuration.buildSessionFactory();
        System.out.println("done");
    }
    
    static {
        instance = new HibernateFabric();
    }
    
    public static HibernateFabric getInstance() {
        return instance;
    }
    
    public Session getSession() throws HibernateException {
        Session session = sessionFactory.openSession();
        if (!session.isConnected()) {
            this.reconnect();
        }
        return session;
    }
    
    private void reconnect() throws HibernateException {
        this.sessionFactory = configuration.buildSessionFactory();
    }
}
