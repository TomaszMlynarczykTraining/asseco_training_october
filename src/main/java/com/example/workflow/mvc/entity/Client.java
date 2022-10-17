package com.example.workflow.mvc.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String lastName;
    private String currency;
    private String street;
    private String phoneNumber;
    private String declaredIncome;


    @OneToOne
    private Debt debt;


}
