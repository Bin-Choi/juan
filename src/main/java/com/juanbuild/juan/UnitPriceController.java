package com.juanbuild.juan;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UnitPriceController {

    @GetMapping("/unit-price1")
    public String unitPrice1(Model model) {
        List<UnitPrice> unitList = new ArrayList<>();

        unitList.add(new UnitPrice(1, "A001", "철근콘크리트", "D10, 1m", "m", 1200, 800, 300, 2300));
        unitList.add(new UnitPrice(2, "A002", "형강", "H-300x300x10x15", "kg", 900, 700, 200, 1800));
        unitList.add(new UnitPrice(3, "A003", "콘크리트타설", "25-210-150", "㎥", 5000, 4000, 500, 9500));
        unitList.add(new UnitPrice(4, "A004", "벽돌쌓기", "190x90x57", "㎡", 1500, 1200, 300, 3000));
        unitList.add(new UnitPrice(5, "A005", "창호설치", "알루미늄, 이중창", "세트", 10000, 7000, 1000, 18000));
        unitList.add(new UnitPrice(6, "A006", "페인트칠", "수성페인트", "㎡", 800, 600, 100, 1500));
        unitList.add(new UnitPrice(7, "A007", "배관설치", "PVC Ø100", "m", 1100, 900, 150, 2150));
        unitList.add(new UnitPrice(8, "A008", "전선포설", "VVF 2.5㎟ x 2C", "m", 300, 200, 50, 550));
        unitList.add(new UnitPrice(9, "A009", "천장마감", "텍스 600x600", "㎡", 2000, 1500, 300, 3800));
        unitList.add(new UnitPrice(10, "A010", "마루깔기", "강화마루", "㎡", 2500, 1800, 400, 4700));

        model.addAttribute("unitList", unitList);
        return "unitPrice1";
    }

    @GetMapping("/unit-price")
    public String unitPrice(Model model) {
        List<UnitPrice> unitList = new ArrayList<>();

        unitList.add(new UnitPrice(1, "A001", "철근콘크리트", "D10, 1m", "m", 1200, 800, 300, 2300));
        unitList.add(new UnitPrice(2, "A002", "형강", "H-300x300x10x15", "kg", 900, 700, 200, 1800));
        unitList.add(new UnitPrice(3, "A003", "콘크리트타설", "25-210-150", "㎥", 5000, 4000, 500, 9500));
        unitList.add(new UnitPrice(4, "A004", "벽돌쌓기", "190x90x57", "㎡", 1500, 1200, 300, 3000));
        unitList.add(new UnitPrice(5, "A005", "창호설치", "알루미늄, 이중창", "세트", 10000, 7000, 1000, 18000));
        unitList.add(new UnitPrice(6, "A006", "페인트칠", "수성페인트", "㎡", 800, 600, 100, 1500));
        unitList.add(new UnitPrice(7, "A007", "배관설치", "PVC Ø100", "m", 1100, 900, 150, 2150));
        unitList.add(new UnitPrice(8, "A008", "전선포설", "VVF 2.5㎟ x 2C", "m", 300, 200, 50, 550));
        unitList.add(new UnitPrice(9, "A009", "천장마감", "텍스 600x600", "㎡", 2000, 1500, 300, 3800));
        unitList.add(new UnitPrice(10, "A010", "마루깔기", "강화마루", "㎡", 2500, 1800, 400, 4700));

        model.addAttribute("unitList", unitList);
        return "unitPrice";
    }
}
