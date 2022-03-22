package process.repositories;

import org.supercsv.io.ICsvMapReader;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;
import process.dataClasses.HumanBeing;
import process.specifications.base.CompositeSpecification;
import process.specifications.base.Specification;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.*;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.CsvMapWriter;
import process.utils.HumanBeingBuilder;

/**
 * Data storage class. Implements repository and extends linkedhashmap
 * Contains instances of HumanBeing
 */
public class HumanBeingRepository extends LinkedHashMap<String, HumanBeing> implements Repository<HumanBeing> {

    private ZonedDateTime initDate;
    private int primaryKeyCounter = 0;
    private final File csvFile;
    private ICsvMapWriter writer;


    /**
     * Constructor. Loads data from file, if it exists
     */
    public HumanBeingRepository(){
        super();
        String path;
        path = System.getProperty("filename");

        if (path == null) path ="data.csv";
        Path filePath = Paths.get(System.getProperty("user.dir"), path);
        csvFile = filePath.toFile();
        boolean exists = csvFile.exists();
        if (!exists) {
            initDate = ZonedDateTime.now();

        }
        else{
            load();
        }

    }

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
        for(Map.Entry<String, HumanBeing> entry: this.entrySet()){
            if (specification.isSatisfiedBy(entry.getValue())){
                result.add(entry.getValue());
            }
        }
        return result;
    }

    @Override
    public List<HumanBeing> query() {
        return query(new CompositeSpecification<>() {
            @Override
            public boolean isSatisfiedBy(HumanBeing candidate) {
                return true;
            }
        });
    }

    @Override
    public void save(){
        try{
            writer = new CsvMapWriter(new PrintWriter(csvFile), CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);
            List<String> columns = new ArrayList<>(List.of("primaryKey"));
            columns.addAll(HumanBeing.getAutoGeneratedFields());
            columns.addAll(HumanBeing.getFields());
            columns.add(initDate.toString());
            String[] header = columns.toArray(new String[0]);
            writer.writeHeader(header);
            for(HumanBeing h: super.values()){
                Map<String, String> fields = new HashMap<>();
                fields.put("primaryKey", h.getPrimaryKey());
                fields.put("id", String.valueOf(h.getId()));
                fields.put("name", h.getName());
                fields.put("impactSpeed", String.valueOf(h.getImpactSpeed()));
                SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy, hh:mm a", Locale.ENGLISH);
                fields.put("creationDate", formatter.format(h.getCreationDate()));
                fields.put("coordinates", h.getCoordinates().toString());
                fields.put("weaponType", h.getWeaponType().toString());
                fields.put("car", h.getCar().toString());
                fields.put("realHero", String.valueOf(h.isRealHero()));
                if (h.isHasToothpick() == null) fields.put("hasToothpick", "null");
                else fields.put("hasToothpick", String.valueOf(h.isHasToothpick()));
                fields.put("mood", h.getMood().toString());
                fields.put(initDate.toString(), "-");
                writer.write(fields, header);
            }
            writer.flush();
            writer.close();
        }
        catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Method to load collection from file
     */
    private void load(){
        ICsvMapReader reader;
        HumanBeingBuilder builder = new HumanBeingBuilder();
        try {
            reader = new CsvMapReader( new FileReader(csvFile), CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);
            String[] header = reader.getHeader(true);
            this.initDate = ZonedDateTime.parse(header[header.length - 1]);
            List<String> columns = Arrays.asList(header);
            while (true){
                Map<String, String> row = reader.read(header);
                if (row == null) break;
                row.remove(this.initDate.toString());
                String primaryKey = row.get("primaryKey");
                row.remove("primaryKey");
                Integer id = Integer.parseInt(row.get("id"));
                row.remove("id");
                SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy, hh:mm a", Locale.ENGLISH);
                Date creationDate = formatter.parse(row.get("creationDate"));
                row.remove("creationDate");
                builder.create(primaryKey, id, creationDate);
                for (Map.Entry<String, String> f: row.entrySet()){
                    builder.build(f.getKey(), f.getValue());
                }
                this.insertEntity(builder.get());
            }
            reader.close();
        }
        catch (ParseException e){
            return;
        }
        catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
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

    @Override
    public boolean containsPrimaryKey(String key){
        return this.containsKey(key);
    }
}
