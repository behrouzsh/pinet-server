package edu.uc.eh.structures;

/**
 * Created by shamsabz on 8/28/17.
 */
public class CSV2ColBean {
    private String col1;
    private String col2;


    public CSV2ColBean(String col1, String col2) {
        this.col1 = col1;
        this.col2 = col2;

    }

    public String getcol1() {
        return col1;
    }

    public void setcol1(String col1) {
        this.col1 = col1;
    }

    public String getcol2() {
        return col2;
    }

    public void setcol2(String col2) {
        this.col2 = col2;
    }


    @Override
    public String toString() {
        return "CSV [col1=" + col1 + ", col2=" + col2 + "]";
    }

}

