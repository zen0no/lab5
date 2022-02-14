package process.specifications;

import process.dataClasses.*;
import process.specifications.base.CompositeSpecification;
import process.specifications.base.Specification;

import java.util.Date;

public class HumanBeingSpecifications {

    public static Specification<HumanBeingModel> Id(int id){
        return new CompositeSpecification<>() {
            @Override
            public boolean isSatisfiedBy(HumanBeingModel candidate) {
                return candidate.getId() == id;
            }
        };
    }

    public static Specification<HumanBeingModel> Name(String name){
        return new CompositeSpecification<>() {
            @Override
            public boolean isSatisfiedBy(HumanBeingModel candidate) {
                return candidate.getName().equals(name);
            }
        };
    }

    public static Specification<HumanBeingModel> Coordinates(Coordinates cords){
        return new CompositeSpecification<>() {
            @Override
            public boolean isSatisfiedBy(HumanBeingModel candidate) {
                return candidate.getCoordinates().equals(cords);
            }
        };
    }

    public static Specification<HumanBeingModel> CreationDate(Date date){
        return new CompositeSpecification<HumanBeingModel>() {
            @Override
            public boolean isSatisfiedBy(HumanBeingModel candidate) {
                return candidate.getCreationDate().equals(date);
            }
        };
    }

    public static Specification<HumanBeingModel> CreatedInPeriod(Date start, Date end){
        return new CompositeSpecification<>() {
            @Override
            public boolean isSatisfiedBy(HumanBeingModel candidate) {
                return candidate.getCreationDate().before(end) && candidate.getCreationDate().after(start);
            }
        };
    }

    public static Specification<HumanBeingModel> HasToothpick(boolean has){
        return new CompositeSpecification<>() {
            @Override
            public boolean isSatisfiedBy(HumanBeingModel candidate) {
                return candidate.isHasToothpick() == has;
            }
        };
    }

    public static Specification<HumanBeingModel> ImpactSpeedUnder(int speed){
        return new CompositeSpecification<>() {
            @Override
            public boolean isSatisfiedBy(HumanBeingModel candidate) {
                return (candidate.getImpactSpeed() < speed);
            }
        };
    }

    public static Specification<HumanBeingModel> ImpactSpeed(int speed){
        return new CompositeSpecification<>() {
            @Override
            public boolean isSatisfiedBy(HumanBeingModel candidate) {
                return (candidate.getImpactSpeed() == speed);
            }
        };
    }

    public static Specification<HumanBeingModel> WeaponType(WeaponType weaponType){
        return new CompositeSpecification<>() {
            @Override
            public boolean isSatisfiedBy(HumanBeingModel candidate) {
                return candidate.getWeaponType().equals(weaponType);
            }
        };
    }

    public static Specification<HumanBeingModel> Mood(Mood mood){
        return new CompositeSpecification<>() {
            @Override
            public boolean isSatisfiedBy(HumanBeingModel candidate) {
                return candidate.getMood().equals(mood);
            }
        };
    }

    public static Specification<HumanBeingModel> Car(Car car){
        return new CompositeSpecification<HumanBeingModel>() {
            @Override
            public boolean isSatisfiedBy(HumanBeingModel candidate) {
                return candidate.getCar().equals(car);
            }
        };
    }
}