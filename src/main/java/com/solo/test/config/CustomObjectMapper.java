package com.solo.test.config;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomObjectMapper extends ObjectMapper {

    public CustomObjectMapper(){

        getSerializerProvider().setNullValueSerializer(new NullSerializer());

    }

}
