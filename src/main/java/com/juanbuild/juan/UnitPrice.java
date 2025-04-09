package com.juanbuild.juan;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnitPrice {
    private int id;
    private String code;
    private String name;
    private String spec;
    private String unit;
    private int materialCost;
    private int laborCost;
    private int expense;
    private int totalCost;

    public UnitPrice() {}

    // 전체 필드를 받는 생성자
    public UnitPrice(int id, String code, String name, String spec, String unit,
                int materialCost, int laborCost, int expense, int totalCost) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.spec = spec;
        this.unit = unit;
        this.materialCost = materialCost;
        this.laborCost = laborCost;
        this.expense = expense;
        this.totalCost = totalCost;
    }
}
