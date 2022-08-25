package com.uniquecare.payload.request;


import com.uniquecare.models.Contract;

import java.util.Date;

public class ContractRequest {
    private Long id;
    private Date start;
    private Date finish;
    private double totalPrice;
    private Long facility_id;
    private Long client_id;
    private Contract.State state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getFacility_id() {
        return facility_id;
    }

    public void setFacility_id(Long facility_id) {
        this.facility_id = facility_id;
    }

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public Contract.State getState() {
        return state;
    }

    public void setState(Contract.State state) {
        this.state = state;
    }

    public Contract.State getState(Contract.State open) {
        return open;
    }


}
