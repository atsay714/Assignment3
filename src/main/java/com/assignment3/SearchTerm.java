package com.assignment3;

import java.util.Date;

public class SearchTerm {

    private String term;
    private Integer frequency;
    private Date lastSearchedTime;
    
    public SearchTerm(String term, Integer frequency, Date date) {
        this.term = term;
        this.frequency = frequency;
        this.lastSearchedTime = date;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Date getLastSearchedTime() {
        return lastSearchedTime;
    }

    public void setLastSearchedTime(Date lastSearchedTime) {
        this.lastSearchedTime = lastSearchedTime;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

}
