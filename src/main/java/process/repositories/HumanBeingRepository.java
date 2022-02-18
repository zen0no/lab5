package process.repositories;

import process.dataClasses.HumanBeing;
import process.specifications.base.Specification;

import java.util.*;

public class HumanBeingRepository extends LinkedHashMap<Integer, HumanBeing> implements Repository<HumanBeing> {

    @Override
    public void insertEntity(HumanBeing entity) {
        this.put(entity.getPrimaryKey(), entity);
    }

    @Override
    public void removeEntity(HumanBeing entity) {
        this.remove(entity.getPrimaryKey());
    }

    @Override
    public void updateEntity(HumanBeing entity){
        this.replace(entity.getPrimaryKey(), entity);
    }

    @Override
    public List<HumanBeing> query(Specification<HumanBeing> specification) {
        List<HumanBeing> result = new ArrayList<>();
        for(Map.Entry<Integer, HumanBeing> entry: this.entrySet()){
            if (specification.isSatisfiedBy(entry.getValue())){
                result.add(entry.getValue());
            }
        }
        Collections.sort(result);
        return result;
    }
}
