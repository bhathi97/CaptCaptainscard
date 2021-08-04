package com.example.captainscard;

public class Model2 {
    String name;
    Integer value;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Model2() {
    }

    @Override
    public String toString() {
        return "Model2{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
