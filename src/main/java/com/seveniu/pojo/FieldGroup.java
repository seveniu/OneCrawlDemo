package com.seveniu.pojo;

/**
 * Created by seveniu on 5/16/16.
 * Book
 */
public class FieldGroup extends Pojo {

    @Unique
    @InsertAndUpdate
    private String name;
    @InsertAndUpdate
    private String fields;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }
}
