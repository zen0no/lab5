package process.dataClasses;

import java.util.EnumSet;

public enum WeaponType {
    AXE("axe"),
    PISTOL("pistol"),
    KNIFE("knife"),
    BAT("bat"),
    ;

    private final String description;

    WeaponType(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static WeaponType parseWeaponType(String s) throws IllegalArgumentException{
        for(WeaponType w: EnumSet.allOf(WeaponType.class)){
            if (w.getDescription().equals(s)) return w;
        }

        throw new IllegalArgumentException("incorrect format of string");
    }

    @Override
    public String toString() {
        return "WeaponType{" +
                "description='" + description + '\'' +
                '}';
    }
}
