package ru.senla.daoImpl.entityDaoImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.senla.dao.entityDao.AdTypeDao;
import ru.senla.daoImpl.AbstractDaoImpl;
import ru.senla.entity.AdType;

import java.util.List;

@Repository
public class AdTypeDaoImpl extends AbstractDaoImpl implements AdTypeDao {

    public AdTypeDaoImpl() {
        super(AdType.class);
    }

    @Autowired
    private SessionFactory factory;

    public AdType getAdTypeByCurrentAdType(AdType adType) {

        return (AdType) factory.getCurrentSession().createQuery("from AdType as at where (at.category = :category and " +
                "at.classification = :classification and buyOrSale = :buyOrSale)")
                .setString("category", adType.getCategory())
                .setString("classification", adType.getClassification())
                .setBoolean("buyOrSale", adType.getBuyOrSale())
                .uniqueResult();
    }
}
