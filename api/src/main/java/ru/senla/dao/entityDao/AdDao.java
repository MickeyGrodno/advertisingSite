package ru.senla.dao.entityDao;

import ru.senla.dao.AbstractDao;
import ru.senla.entity.Ad;
import ru.senla.entity.AdType;
import java.util.List;

public interface AdDao extends AbstractDao {
    List<Ad> searchByText(String text);
    List<Ad> getByUserId(Long id);
    List<Ad> searchByUserLogin(String login);
    List<Ad> searchByAdType(AdType adType);
}
