package edu.uc.eh.peptideMatch;

/**
 * Created by shamsabz on 9/20/17.
 */
public class Fasta {
    private String id = null;
    private String seq = null;
    public Fasta(String id, String seq) {
        super();
        this.id = id;
        this.seq = seq;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSeq() {
        return seq;
    }
    public void setSeq(String seq) {
        this.seq = seq;
    }
}
