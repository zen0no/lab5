package process.dataClasses;

import java.util.EnumSet;

public enum Mood implements Comparable<Mood>{
    SADNESS("sadness"),
    SORROW("sorrow"),
    GLOOM("gloom"),
    APATHY("apathy"),
    FRENZY("frenzy"),
    ;

    private final String description;

    Mood(String s){
        this.description = s;
    }

    public String getDescription() {
        return description;
    }

    public static Mood parseMood(String s) throws IllegalArgumentException{
        if (s == null){
            return null;
        }
        for (Mood m:
                EnumSet.allOf(Mood.class)) {
            if (m.getDescription().equals(s)) return m;
        }
        throw new IllegalArgumentException("can't parse mood from \"" + s + "\"");
    }


    @Override
    public String toString() {
        return getDescription();
    }
}
