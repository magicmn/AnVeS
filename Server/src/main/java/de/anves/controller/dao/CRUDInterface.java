package de.anves.controller.dao;
import de.anves.DBManaged;

public interface CRUDInterface<T extends DBManaged>{

    T create(T value);

    T read(long id);

    T update(T value);

    boolean delete(T value);
}
