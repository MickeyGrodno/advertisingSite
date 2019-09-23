package ru.senla.dao.entityDao;

import ru.senla.dao.AbstractDao;
import ru.senla.entity.AdType;

import java.util.List;

public interface AdTypeDao extends AbstractDao {
    AdType getAdTypeByCurrentAdType(AdType adType);

}
