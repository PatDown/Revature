package com.pdownton.reimbursementapp.utils;

import java.io.FileReader;
import java.util.Properties;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Pat Down
 */
public class HibernateSessionFactory {
    
    private static SessionFactory sessionFactory;
    
    public static Session getSession(){
        if (sessionFactory == null){
            Properties props = new Properties();
            try {
                FileReader connProps = new FileReader("src/main/resources/connection.properties");
                props.load(connProps);
                sessionFactory = new Configuration().configure()
                   .setProperty("hibernate.connection.url", props.getProperty("url"))
                   .setProperty("hibernate.connection.username", props.getProperty("username"))
                   .setProperty("hibernate.connection.password", props.getProperty("password"))
                   .buildSessionFactory();
            } catch (Exception e){
                e.printStackTrace();
            }//catch (Exception e)
        }//if (sessionFactory == null)
        return sessionFactory.getCurrentSession();
    }//getSession()
}//HibernateSessionFactory
