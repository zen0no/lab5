package process;

import process.dataClasses.HumanBeing;
import process.repositories.HumanBeingRepository;
import process.specifications.HumanBeingSpecifications;

import java.util.LinkedHashMap;

public class DataManager extends LinkedHashMap<String, HumanBeing> implements HumanBeingRepository {
    public void insertHumanBeing(HumanBeing human){
        this.put(Integer.toString(human.getId()), human);
    }

    public void removeHumanBeing(HumanBeing human){
        this.remove(Integer.toString(human.getId()));
    }

    public void updateHumanBeing(HumanBeing human){
        return;
    }

    public HumanBeing specifyHumanBeing(HumanBeingSpecifications specification){
      return new HumanBeing();
    }

    public void sort(HumanBeingSpecifications specification){
        return;
    }
}
