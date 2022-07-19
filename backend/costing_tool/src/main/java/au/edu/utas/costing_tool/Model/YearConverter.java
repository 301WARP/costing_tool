package au.edu.utas.costing_tool.Model;

//import java.sql.Date;
import java.time.Year;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply=true)
//public class YearConverter implements AttributeConverter<Year, Date>
public class YearConverter implements AttributeConverter<Year, Integer>
{
    @Override
    public Integer convertToDatabaseColumn(Year entityYear)
    {
        return entityYear.getValue();
    }

    @Override
    public Year convertToEntityAttribute(Integer databaseYear)
    {
        System.out.println("********************************************************************************"
                            + databaseYear);
        return Year.of(databaseYear);
    }

    /*
    public Date convertToDatabaseColumn(Year entityYear)
    {
        return (entityYear == null)
            ? null
            : Date.valueOf(entityYear.format(DateTimeFormatter.ISO_LOCAL_DATE));

    }

    public Year convertToEntityAttribute(Date databaseYear)
    {
        System.out.println("********************************************************************************"
                            + databaseYear.toString());
        return (databaseYear == null)
            ? null
            : Year.parse(   databaseYear.toString(),
                            DateTimeFormatter.ISO_LOCAL_DATE);
    }
    */
}