package process.repositories;

import process.dataClasses.HumanBeing;
import process.specifications.HumanBeingSpecifications;

public interface HumanBeingRepository {
    void insertHumanBeing(HumanBeing human);
    void removeHumanBeing(HumanBeing human);
    void updateHumanBeing(HumanBeing human);
    HumanBeing specifyHumanBeing(HumanBeingSpecifications specification);
}
