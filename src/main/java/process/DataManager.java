package process;

import process.dataClasses.HumanBeing;
import process.repositories.HumanBeingRepository;
import process.specifications.HumanBeingSpecification;

import java.util.LinkedHashMap;

public class DataManager extends LinkedHashMap<String, HumanBeing> implements HumanBeingRepository {
    public void insertHumanBeing(HumanBeing human){
        return;
    }

    public void removeHumanBeing(HumanBeing human){
        return;
    }

    public void updateHumanBeing(HumanBeing human){
        return;
    }

    public HumanBeing specifyHumanBeing(HumanBeingSpecification specification){
      return new HumanBeing();
    }

    public void sort(HumanBeingSpecification specification){
        return;
    }
}
