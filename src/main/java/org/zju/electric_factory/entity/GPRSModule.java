package org.zju.electric_factory.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: vincent
 * Date: 6/10/13
 * Time: 2:31 PM

 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "GPRS_MODULE")
public class GPRSModule {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "IDENTIFIER")
    private String identifier;

    @Column(name = "NAME")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
