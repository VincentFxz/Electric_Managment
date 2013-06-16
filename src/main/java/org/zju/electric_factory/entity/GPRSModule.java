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
}
