package process.repositories;
import process.dataClasses.HumanBeing;
import process.specifications.base.Specification;

import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.List;

public interface Repository<T> {
    void insertEntity(T entity);
    void removeEntity(T entity);
    void removeEntity(List<T> entity);
    void updateEntity(T entity);
    List<HumanBeing> query(Specification<T> specification);
    List<HumanBeing> query();
    void save();
    void load();
    ZonedDateTime getInitDate();
    String getTypeName();
    int getPrimaryKeyCounter();

}
