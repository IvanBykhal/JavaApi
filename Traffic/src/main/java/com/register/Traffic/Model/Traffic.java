package com.register.Traffic.Model;

import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "traffic")
public class Traffic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "id_customer")
    private Customer customer;
    
    @ManyToOne(targetEntity = Manager.class)
    @JoinColumn(name = "id_manager")
    private Manager manager;
    
    private String typeLied;
    private LocalDateTime registrationDate;
    private String kindTraffic;
    private String department;
    private Boolean callBack;
    private Boolean testDrive;
    
    @ManyToOne(targetEntity = DataSource.class)
    @JoinColumn(name = "id_data_source")
    private DataSource datasource;
    
    @ManyToOne(targetEntity = Interes.class)
    @JoinColumn(name = "id_primary_interest")
    private Interes interes;
    
    @OneToMany(mappedBy="traffic")
    private Set<AlternativeInterest> alternativeInterest;
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public String getTypeLied() {
        return typeLied;
    }

    public void setTypeLead(String typeLied) {
        this.typeLied = typeLied;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getKindTraffic() {
        return kindTraffic;
    }

    public void setKindTraffic(String kindTraffic) {
        this.kindTraffic = kindTraffic;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Boolean getCallBack() {
        return callBack;
    }

    public void setCallBack(Boolean callBack) {
        this.callBack = callBack;
    }

    public Boolean getTestDrive() {
        return testDrive;
    }

    public void setTestDrive(Boolean testDrive) {
        this.testDrive = testDrive;
    }

    public DataSource getDataSource() {
        return datasource;
    }

    public void setDataSource(DataSource datasource) {
        this.datasource = datasource;
    }

    public Interes getModel() {
        return interes;
    }

    public void setModel(Interes interes) {
        this.interes = interes;
    }
    
    public Set<AlternativeInterest> getAlternativeInterest() {
        return alternativeInterest;
    }

    public void setAlternativeInterest(Set<AlternativeInterest> alternativeInterest) {
        this.alternativeInterest = alternativeInterest;
    }
}
