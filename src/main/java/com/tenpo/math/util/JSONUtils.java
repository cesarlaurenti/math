package com.tenpo.math.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String asJsonString(Object request)  throws  JsonProcessingException{
        return OBJECT_MAPPER.writeValueAsString(request);
    }
}
