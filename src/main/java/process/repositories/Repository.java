package process.repositories;
import process.dataClasses.HumanBeing;
import process.specifications.base.Specification;

import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.List;

public interface Repository<T> {
    /**
     * @param entity object to insert into collection
     */
    void insertEntity(T entity);

    /**
     * @param entity object to remove
     */
    void removeEntity(T entity);

    /**
     * @param entity list of objects to remove
     */
    void removeEntity(List<T> entity);

    /**
     * @param entity object ot update
     */
    void updateEntity(T entity);

    /**
     * @param specification condition
     * @return list of entities
     */
    List<HumanBeing> query(Specification<T> specification);

    /**
     * @return complete collection
     */
    List<HumanBeing> query();

    /**
     * method to save collection to file
     */
    void save();

    /**
     * @return collections initialize date
     */
    ZonedDateTime getInitDate();

    /**
     * @return entities type name
     */
    String getTypeName();

    /**
     * @return key counter
     */
    int getPrimaryKeyCounter();

    /**
     * @param key primary key
     * @return true if contains, false otherwise
     */
    boolean containsPrimaryKey(String key);

}
