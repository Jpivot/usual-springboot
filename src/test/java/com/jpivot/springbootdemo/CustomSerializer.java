package com.jpivot.springbootdemo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CustomSerializer extends StdSerializer<Man> {
    protected CustomSerializer(Class<Man> t) {
        super(t);
    }

    public CustomSerializer() {
        this(null);
    }

    @Override
    public void serialize(Man value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("name", value.getName());
        gen.writeEndObject();
    }
}

class Man{
    private int age;
    private String name;

    public Man(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
