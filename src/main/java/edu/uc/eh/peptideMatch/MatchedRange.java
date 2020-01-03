package edu.uc.eh.peptideMatch;

/**
 * Created by shamsabz on 9/20/17.
 */
public class MatchedRange {
    private int start;
    private int end;
    private int[] replacedPos;

    public MatchedRange(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getStart() {
        return this.start;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getEnd() {
        return this.end;
    }

    public void setReplacedPos(int[] replacedPos) {
        this.replacedPos = replacedPos;
    }

    public int[] getReplacedPos() {
        return this.replacedPos;
    }
}
