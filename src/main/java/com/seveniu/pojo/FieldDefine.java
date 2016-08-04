package com.seveniu.pojo;

/**
 * Created by seveniu on 5/16/16.
 * Book
 */
public class FieldDefine extends Pojo {

    @Unique
    @InsertAndUpdate
    private String name;

    @InsertAndUpdate
    private int type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Field{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }


}
