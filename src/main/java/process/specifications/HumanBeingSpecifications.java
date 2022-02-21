package process.specifications;

import process.dataClasses.*;
import process.specifications.base.CompositeSpecification;
import process.specifications.base.Specification;

import java.util.Date;

public class HumanBeingSpecifications {

    public static Specification<HumanBeing> Id(int id){
        return new CompositeSpecification<>() {
            @Override
            public boolean isSatisfiedBy(HumanBeing candidate) {
                return candidate.getId() == id;
            }
        };
    }

    public static Specification<HumanBeing> Name(String name){
        return new CompositeSpecification<>() {
            @Override
            public boolean isSatisfiedBy(HumanBeing candidate) {
                return candidate.getName().equals(name);
            }
        };
    }

    public static Specification<HumanBeing> Coordinates(Coordinates cords){
        return new CompositeSpecification<>() {
            @Override
            public boolean isSatisfiedBy(HumanBeing candidate) {
                return candidate.getCoordinates().equals(cords);
            }
        };
    }

    public static Specification<HumanBeing> CreationDate(Date date){
        return new CompositeSpecification<HumanBeing>() {
            @Override
            public boolean isSatisfiedBy(HumanBeing candidate) {
                return candidate.getCreationDate().equals(date);
            }
        };
    }

    public static Specification<HumanBeing> CreatedInPeriod(Date start, Date end){
        return new CompositeSpecification<>() {
            @Override
            public boolean isSatisfiedBy(HumanBeing candidate) {
                return candidate.getCreationDate().before(end) && candidate.getCreationDate().after(start);
            }
        };
    }

    public static Specification<HumanBeing> HasToothpick(boolean has){
        return new CompositeSpecification<>() {
            @Override
            public boolean isSatisfiedBy(HumanBeing candidate) {
                return candidate.isHasToothpick() == has;
            }
        };
    }

    public static Specification<HumanBeing> RealHero(boolean realHero){
        return new CompositeSpecification<HumanBeing>() {
            @Override
            public boolean isSatisfiedBy(HumanBeing candidate) {
                return candidate.isRealHero();
            }
        };
    }

    public static Specification<HumanBeing> ImpactSpeedUnder(int speed){
        return new CompositeSpecification<>() {
            @Override
            public boolean isSatisfiedBy(HumanBeing candidate) {
                return (candidate.getImpactSpeed() < speed);
            }
        };
    }

    public static Specification<HumanBeing> ImpactSpeed(int speed){
        return new CompositeSpecification<>() {
            @Override
            public boolean isSatisfiedBy(HumanBeing candidate) {
                return (candidate.getImpactSpeed() == speed);
            }
        };
    }

    public static Specification<HumanBeing> WeaponType(WeaponType weaponType){
        return new CompositeSpecification<>() {
            @Override
            public boolean isSatisfiedBy(HumanBeing candidate) {
                return candidate.getWeaponType().equals(weaponType);
            }
        };
    }

    public static Specification<HumanBeing> Mood(Mood mood){
        return new CompositeSpecification<>() {
            @Override
            public boolean isSatisfiedBy(HumanBeing candidate) {
                return candidate.getMood().equals(mood);
            }
        };
    }

    public static Specification<HumanBeing> Car(Car car){
        return new CompositeSpecification<HumanBeing>() {
            @Override
            public boolean isSatisfiedBy(HumanBeing candidate) {
                return candidate.getCar().equals(car);
            }
        };
    }

    public static Specification<HumanBeing> Lower(HumanBeing o){
        return new CompositeSpecification<HumanBeing>() {
            @Override
            public boolean isSatisfiedBy(HumanBeing candidate) {
                return o.compareTo(candidate) > 0;
            }
        };
    }
}