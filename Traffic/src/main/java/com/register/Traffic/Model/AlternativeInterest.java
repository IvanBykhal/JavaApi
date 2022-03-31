package com.register.Traffic.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "alternativeinterest")
public class AlternativeInterest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @ManyToOne(targetEntity=Interes .class)
    @JoinColumn(name = "id_model")
    private Interes interes;
    
    @ManyToOne(targetEntity=Traffic.class)
    @JoinColumn(name = "id_traffic")
    private Traffic traffic;
    
     public AlternativeInterest() {}
     
    public AlternativeInterest(Interes interes, Traffic traffic) {
        this.interes = interes;
        this.traffic = traffic;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Interes getModel() {
        return interes;
    }

    public void setModel(Interes interes) {
        this.interes = interes;
    }

    public void setTraffic(Traffic traffic) {
        this.traffic = traffic;
    }
}
