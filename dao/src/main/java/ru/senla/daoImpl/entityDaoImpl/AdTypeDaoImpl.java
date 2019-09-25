package ru.senla.daoImpl.entityDaoImpl;

import org.springframework.stereotype.Repository;
import ru.senla.dao.entityDao.AdTypeDao;
import ru.senla.daoImpl.AbstractDaoImpl;
import ru.senla.entity.AdType;

@Repository
public class AdTypeDaoImpl extends AbstractDaoImpl implements AdTypeDao {

    public AdTypeDaoImpl() {
        super(AdType.class);
    }

}
