package process.repositories;

import org.supercsv.io.ICsvMapReader;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;
import process.dataClasses.HumanBeing;
import process.specifications.base.CompositeSpecification;
import process.specifications.base.Specification;

import java.io.*;
import java.time.ZonedDateTime;
import java.util.*;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.CsvMapWriter;
import process.utils.HumanBeingBuilder;

public class HumanBeingRepository extends LinkedHashMap<Integer, HumanBeing> implements Repository<HumanBeing> {

    private ZonedDateTime initDate;
    private int primaryKeyCounter = 0;
    private final File csvFile;
    private ICsvMapWriter writer;


    public HumanBeingRepository(){
        super();
        try {
            String path;
            path = System.getenv("filename");
                if (path == null) path = "C:/Different/data.csv";

            csvFile = new File(path);
            if (!csvFile.createNewFile()) load();
            else {
                initDate = ZonedDateTime.now();
            }
        }
        catch (IOException e){
            throw new RuntimeException(e.getMessage());
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
        for(Map.Entry<Integer, HumanBeing> entry: this.entrySet()){
            if (specification.isSatisfiedBy(entry.getValue())){
                result.add(entry.getValue());
            }
        }
        return result;
    }

    @Override
    public List<HumanBeing> query() {
        return query(new CompositeSpecification<HumanBeing>() {
            @Override
            public boolean isSatisfiedBy(HumanBeing candidate) {
                return true;
            }
        });
    }

    @Override
    public void save(){
        ICsvMapWriter writer;
        try{
            writer = new CsvMapWriter(new PrintWriter(csvFile), CsvPreference.STANDARD_PREFERENCE);
            List<String> columns = Arrays.asList("id",
                    "name",
                    "impactSpeed",
                    "creationDate",
                    "weaponType",
                    "mood",
                    "car",
                    "realHero",
                    "hasToothpick");
            columns.add(initDate.toString());
            String[] header = columns.toArray(new String[columns.size()]);
            writer.writeHeader(header);
            for(HumanBeing h: super.values()){
                Map<String, String> fields = new HashMap<>();
                fields.put("id", String.valueOf(h.getId()));
                fields.put("name", h.getName());
                fields.put("impactSpeed", String.valueOf(h.getImpactSpeed()));
                fields.put("creationDate", h.getCreationDate().toString());
                fields.put("weaponType", h.getWeaponType().toString());
                fields.put("car", h.getCar().toString());
                fields.put("realHero", String.valueOf(h.isRealHero()));
                fields.put("hasToothpick", String.valueOf(h.isHasToothpick()));
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

    private void load(){
        ICsvMapReader reader;
        HumanBeingBuilder builder = new HumanBeingBuilder();
        try {
            reader = new CsvMapReader( new FileReader(csvFile), CsvPreference.STANDARD_PREFERENCE);
            String[] header = reader.getHeader(true);
            this.initDate = ZonedDateTime.parse(header[header.length - 1]);
            List<String> columns = Arrays.asList(header);
            while (true){
                Map<String, String> row = reader.read(header);
                if (row == null) break;
                row.remove(this.initDate.toString());
                int id = Integer.parseInt(row.get("id"));
                row.remove("id");
                builder.create(id);
                builder.build(row);
                this.insertEntity(builder.get());
            }
            reader.close();
        }
        catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
        this.initDate = ZonedDateTime.now();
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
}
