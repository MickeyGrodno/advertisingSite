package ru.senla.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.senla.entity.Ad;
import ru.senla.entity.AdType;
import ru.senla.entity.Chat;
import ru.senla.entity.Comment;
import ru.senla.entity.Credential;
import ru.senla.entity.Message;
import ru.senla.entity.User;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;
    private static Session session;

    private HibernateSessionFactoryUtil() {
    }

    public static Session getSession() {
        if (session == null) {
            session = getSessionFactory().openSession();
        } else {
            session = getSessionFactory().getCurrentSession();
        }
        return session;
    }

    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(Ad.class);
            configuration.addAnnotatedClass(AdType.class);
            configuration.addAnnotatedClass(Chat.class);
            configuration.addAnnotatedClass(Comment.class);
            configuration.addAnnotatedClass(Credential.class);
            configuration.addAnnotatedClass(Message.class);
            configuration.addAnnotatedClass(User.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        }
        return sessionFactory;
    }
}
