package process.repositories;
import process.dataClasses.HumanBeing;
import process.specifications.base.Specification;

import java.util.List;

public interface Repository<T> {
    void insertEntity(T entity);
    void removeEntity(T entity);
    void updateEntity(T entity);
    List<HumanBeing> query(Specification<T> specification);

}
