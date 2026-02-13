package com.airtribe.meditrack.entity;

public final class BillSummary {
    
    private final int billId;
    private final double totalAmount;

    public BillSummary(int billId, double totalAmount) {
        this.billId = billId;
        this.totalAmount = totalAmount;
    }   

    public int getBillId() {
        return billId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

}
