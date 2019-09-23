package ru.senla.daoImpl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.senla.dao.AbstractDao;

import java.io.Serializable;
import java.util.List;

public class AbstractDaoImpl<T, PK extends Serializable>
        implements AbstractDao<T, PK> {
    private Class<T> type;
    @Autowired
    private SessionFactory factory;

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    public AbstractDaoImpl(Class<T> type) {
        this.type = type;
    }

    public PK create(T o) {
        return (PK) factory.getCurrentSession().save(o);
    }

    public T read(PK id) {
        return factory.getCurrentSession().get(type, id);
    }

    public void update(T o) {
        factory.getCurrentSession().update(o);
    }

    public void delete(T o) {
        factory.getCurrentSession().delete(o);
    }

    public List<T> findAll(Class clazz) {
        Criteria cr = factory.getCurrentSession().createCriteria(clazz);
        return (List<T>) cr.list();
    }

    public void saveOrUpdate(T o) {
        factory.getCurrentSession().saveOrUpdate(o);
    }
}
