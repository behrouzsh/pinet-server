package edu.uc.eh.structures;

import java.util.List;

/**
 * Created by shamsabz on 2/27/18.
 */
public class IntStringDoubleStringString {

    private Integer id;
    private String string;
    private Double aDouble;
    private String description;
    private String descriptionAll;
    //private List<StringDoubleStringList> similar;

    public IntStringDoubleStringString(Integer id,String string, Double aDouble, String description, String descriptionAll) {
        this.id = id;
        this.string = string;
        this.aDouble = aDouble;
        this.description = description;
        this.descriptionAll = descriptionAll;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Double getaDouble() {
        return aDouble;
    }

    public void setaDouble(Double aDouble) {
        this.aDouble = aDouble;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionAll() {
        return descriptionAll;
    }

    public void setDescriptionAll(String descriptionAll) {
        this.descriptionAll = descriptionAll;
    }
}
