package com.kcj.domain;

import javax.persistence.*;

@Entity
@Table(name="poll_option")
public class Option {
    @Id
    @GeneratedValue
    @Column(name="OPTION_ID")
    private Long id;

    @Column(name="OPTION_VALUE")
    private String value;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
