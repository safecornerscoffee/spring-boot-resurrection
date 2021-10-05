package com.safecornerscoffee.resurrection.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Authority {
    @Id
    @Column(name = "authority_name")
    private String name;

    protected Authority() {}

    public Authority(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

