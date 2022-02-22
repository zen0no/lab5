package process.dataClasses;

import java.util.EnumSet;

public enum WeaponType implements Comparable<WeaponType>{
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
        if (s == null) return null;
        for(WeaponType w: EnumSet.allOf(WeaponType.class)){
            if (w.getDescription().equals(s)) return w;
        }

        throw new IllegalArgumentException("can't parse weaponType from \"" + s + "\"");
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
