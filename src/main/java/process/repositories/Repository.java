package process.repositories;

import process.model.Model;
import process.specifications.base.Specification;

import java.util.LinkedHashMap;

public interface Repository<T extends Model> {
    void insertEntity(T entity);
    void removeEntity(T entity);
    void updateEntity(T entity, T newEntity);
    LinkedHashMap<Integer, T> query(Specification<T> specification);

}
