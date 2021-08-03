package com.example.captainscard;

public class Model3 {
    String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    Integer value;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Model3() {
    }

    @Override
    public String toString() {
        return "Model2{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}





