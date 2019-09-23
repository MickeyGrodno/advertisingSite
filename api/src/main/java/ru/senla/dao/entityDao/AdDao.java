package ru.senla.dao.entityDao;

import ru.senla.dao.AbstractDao;
import ru.senla.entity.Ad;

import java.util.List;

public interface AdDao extends AbstractDao {
    List<Ad> searchByText(String text);
}
