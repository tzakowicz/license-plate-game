package net.gilmor.plate.util;

import org.apache.johnzon.mapper.Mapper;
import org.apache.johnzon.mapper.MapperBuilder;

public class JsonUtil {

    public static String buildJson(Object obj) {
        try (Mapper map = new MapperBuilder().build()) {
            return map.writeObjectAsString(obj);
        }
    }

}
