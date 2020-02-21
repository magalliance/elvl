package com.byvalcev.model;

import javax.persistence.*;

@Table(name="QUOTES")
@Entity
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String isin;

    private Double bid;

    private Double ask;

    private Double elvl;

    public Quote() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsin() {
        return isin;
    }

    public Quote setIsin(String isin) {
        this.isin = isin;
        return this;
    }

    public Double getBid() {
        return bid;
    }

    public Quote setBid(Double bid) {
        this.bid = bid;
        return this;
    }

    public Double getAsk() {
        return ask;
    }

    public Quote setAsk(Double ask) {
        this.ask = ask;
        return this;
    }

    public Double getElvl() {
        return elvl;
    }

    public Quote setElvl(Double elvl) {
        this.elvl = elvl;
        return this;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "id=" + id +
                ", isin='" + isin + '\'' +
                ", bid=" + bid +
                ", ask=" + ask +
                ", elvl=" + elvl +
                '}';
    }
}
