package dataAccess.repository;

import java.io.Serializable;
import java.util.List;

public interface Repository<T> extends Serializable {
    void add(T obj);

    void remove(T obj);

    List<T> getAll();
}
