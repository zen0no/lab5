package other;

import com.google.gson.internal.LinkedTreeMap;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class InfoBlock implements Serializable {
    public final ZonedDateTime dateTime;
    public final int size;
    public final String typeName;

    public InfoBlock(ZonedDateTime dateTime, int size, String typeName) {
        this.dateTime = dateTime;
        this.size = size;
        this.typeName = typeName;

    }

    @Override
    public String toString() {
        return "InfoBlock{" +
                "dateTime=" + dateTime +
                ", size=" + size +
                ", typeName=" + typeName +
                '}';
    }

}
