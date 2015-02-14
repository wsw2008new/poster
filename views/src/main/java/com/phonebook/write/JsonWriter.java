package com.phonebook.write;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;

import java.io.IOException;

public class JsonWriter {

    public static void main(String[] args) throws IOException {
//        MessageBodyReader reader = new JacksonJsonProvider();
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.writeValue( , myBean); // where 'dst' can be File, OutputStream or Writer

//        JacksonJsonProvider p = new JacksonJsonProvider();
//        JsonGenerator.
//        MessageBodyWriter writer  = new MessageBodyWriter() {

        String json = "{\"nickName\":null,\"name\":\"kozubal\",\"number\":\"068-017-17-57\"}";
        JsonFactory jsonFactory = new JsonFactory(); // or, for data binding, org.codehaus.jackson.mapper.MappingJsonFactory
        JsonParser jp = jsonFactory.createParser(json.getBytes()); // or URL, Stream, Reader, String, byte[]
    }
}
