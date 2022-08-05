package au.edu.utas.costing_tool.Mapper;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;

public abstract class EnumConverter
{
    public static final Converter<Enum<?>, String> string
        = new AbstractConverter<Enum<?>, String>()
    {
        protected String convert(Enum<?> e)
        {
            if (e == null) return null;

            return e.name();
        }
    };

    public static final Converter<Enum<?>, Integer> ordinal
        = new AbstractConverter<Enum<?>, Integer>()
    {
        protected Integer convert(Enum<?> e)
        {
            if (e == null) return null;

            return e.ordinal();
        }
    };
}
