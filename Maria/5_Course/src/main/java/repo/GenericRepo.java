package repo;

import java.util.List;

public interface GenericRepo<T, ID> {
    T create (T t);
    T update (T t);
    boolean delete (ID id);
    T findById (ID id);
    List<T> loadAll ();
}
