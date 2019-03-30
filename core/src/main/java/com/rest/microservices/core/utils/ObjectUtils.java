package com.rest.microservices.core.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.File;

public class ObjectUtils {


    @SneakyThrows
    public static String objectToJson(Object object) {
        ObjectMapper mapper = getMapper();
//        mapper.writeValueAsString(object);
        return mapper.writeValueAsString(object);
    }

    @SneakyThrows
    public static Object jsonToObject(String json, Class clazz) {
        ObjectMapper mapper = getMapper();
        return mapper.readValue(json, clazz);
    }

    private static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }
}
