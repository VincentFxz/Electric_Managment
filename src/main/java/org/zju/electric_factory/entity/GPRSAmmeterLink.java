package org.zju.electric_factory.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: vincent
 * Date: 6/10/13
 * Time: 2:35 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "GPRS_AMMETER_LINK")
public class GPRSAmmeterLink {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "GPRS_ID")
    private Long gprsId;

    @Column(name = "AMMETER_ID")
    private Long ammeterId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGprsId() {
        return gprsId;
    }

    public void setGprsId(Long gprsId) {
        this.gprsId = gprsId;
    }

    public Long getAmmeterId() {
        return ammeterId;
    }

    public void setAmmeterId(Long ammeterId) {
        this.ammeterId = ammeterId;
    }

}
