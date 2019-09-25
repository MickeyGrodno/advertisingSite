package ru.senla.daoImpl.entityDaoImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.senla.dao.entityDao.AdDao;
import ru.senla.daoImpl.AbstractDaoImpl;
import ru.senla.entity.Ad;
import ru.senla.entity.AdType;

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

    public List<Ad> getByUserId(Long id) {
        return factory.getCurrentSession().createQuery("from Ad as a where a.user = (from User as u where u.id = :id)")
                .setLong("id", id)
                .list();
    }

    public List<Ad> searchByUserLogin(String login) {
        return factory.getCurrentSession().createQuery("from Ad as a where a.user = " +
                "(select c.user from Credential as c where c.login = :login)")
                .setString("login", login)
                .list();
    }

    public List<Ad> searchByAdType(AdType adType) {
        return factory.getCurrentSession().createQuery("from Ad as a where a.adType = (from AdType as at where " +
                "at.category = :category and at.classification = :classification and at.buyOrSale = :buyOrSale)")
                .setString("category", adType.getCategory())
                .setString("classification", adType.getClassification())
                .setBoolean("buyOrSale", adType.getBuyOrSale())
                .list();
    }
}
