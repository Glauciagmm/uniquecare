package com.uniquecare.payload.request;


import com.uniquecare.models.Contract;


public class ContractRequest {
    private Long id;
    private String start;
    private String finish;
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

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
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
