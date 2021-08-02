package com.example.captainscard;

public class Model {

    String name;
    String age;
    String position;
    String height;
    String weight;
    String note;

    String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Model() {
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getPosition() {
        return position;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String getNote() {
        return note;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Model{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", position='" + position + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", note='" + note + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
