package org.zju.electric_factory.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.zju.electric_factory.entity.converter.impl.JsonDateSerializer;

@Entity
@Table(name = "AMMETER_RECORD")
public class AmmeterRecord {

    @Id
    @GeneratedValue
    private int id;

    /**
     * 电表id
     */
    @Column(name = "AMMETER_ID")
    private int ammeterId;

    /**
     * 记录的时间
     */
    @Column(name = "RECORD_TIME")
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date recordDate;

    /**
     * 电表数据
     */
    @Column(name = "AMMETER_VALUE")
    private float ammeterValue;

    @Column(name="AMMETER_NAME")
    private String ammeterName;
    
    public String getAmmeterName() {
        return ammeterName;
    }

    public void setAmmeterName(String ammeterName) {
        this.ammeterName = ammeterName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmmeterId() {
        return ammeterId;
    }

    public void setAmmeterId(int ammeterId) {
        this.ammeterId = ammeterId;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public float getAmmeterValue() {
        return ammeterValue;
    }

    public void setAmmeterValue(float ammeterValue) {
        this.ammeterValue = ammeterValue;
    }

}
