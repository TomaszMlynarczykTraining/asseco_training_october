package com.example.workflow.mvc.external_task;
//{"table":"A","currency":"frank szwajcarski","code":"CHF","rates":[{"no":"213/A/NBP/2022","effectiveDate":"2022-11-03","mid":4.7697}]}

import java.util.List;

public class Response {
    private String table;
    private String currency;
    private String code;
    private List rates;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List getRates() {
        return rates;
    }

    public void setRates(List rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "Response{" +
                "table='" + table + '\'' +
                ", currency='" + currency + '\'' +
                ", code='" + code + '\'' +
                ", rates=" + rates +
                '}';
    }
}
