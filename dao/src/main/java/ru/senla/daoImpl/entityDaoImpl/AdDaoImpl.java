package ru.senla.daoImpl.entityDaoImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.senla.dao.entityDao.AdDao;
import ru.senla.daoImpl.AbstractDaoImpl;
import ru.senla.entity.Ad;

import java.util.List;

@Repository
public class AdDaoImpl extends AbstractDaoImpl implements AdDao {

    public AdDaoImpl() {
        super(Ad.class);
    }

    @Autowired
    private SessionFactory factory;

    public List<Ad> searchByText(String text) {
        return factory.getCurrentSession().createQuery("from Ad as a where a.adMessage like :text")
                .setString("text", "%" + text + "%")
                .list();
    }
}
