package process.repositories;

import process.dataClasses.HumanBeing;
import process.specifications.base.CompositeSpecification;
import process.specifications.base.Specification;

import java.time.ZonedDateTime;
import java.util.*;

public class HumanBeingRepository extends LinkedHashMap<Integer, HumanBeing> implements Repository<HumanBeing> {

    private ZonedDateTime initDate;
    private int primaryKeyCounter = 0;

    @Override
    public void insertEntity(HumanBeing entity) {
        this.put(entity.getPrimaryKey(), entity);
    }

    @Override
    public void removeEntity(HumanBeing entity) {
        this.remove(entity.getPrimaryKey());
    }

    @Override
    public void removeEntity(List<HumanBeing> entityList){
        for (HumanBeing h: entityList){
            this.removeEntity(h);
        }
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

    @Override
    public List<HumanBeing> query() {
        return query(new CompositeSpecification<HumanBeing>() {
            @Override
            public boolean isSatisfiedBy(HumanBeing candidate) {
                return true;
            }
        });
    }

    @Override
    public void save(){
        return;
    }

    @Override
    public void load(){

    }

    @Override
    public ZonedDateTime getInitDate() {
        return this.initDate;
    }

    @Override
    public String getTypeName(){
        return "HumanBeing";
    }

    public int getPrimaryKeyCounter() {
        return ++primaryKeyCounter;
    }
}
