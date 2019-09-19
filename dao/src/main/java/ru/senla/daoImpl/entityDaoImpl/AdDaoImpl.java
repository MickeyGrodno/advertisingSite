package ru.senla.daoImpl.entityDaoImpl;

import org.springframework.stereotype.Repository;
import ru.senla.dao.entityDao.AdDao;
import ru.senla.daoImpl.AbstractDaoImpl;
import ru.senla.entity.Ad;

@Repository
public class AdDaoImpl extends AbstractDaoImpl implements AdDao {

    public AdDaoImpl() {
        super(Ad.class);
    }

}
