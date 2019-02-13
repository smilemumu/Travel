package com.shibro.travel.data.enums;

import com.shibro.travel.data.dto.TravelTypeItem;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum TravelTypeEnum {
    TYPE1("1","游乐场"),
    TYPE2("2","公园"),
    TYPE3("3","温泉"),
    TYPE4("4","森林"),
    TYPE5("5","亭台楼阁"),
    TYPE6("6","农家乐"),
    TYPE7("7","故居"),
    TYPE8("8","采摘"),
    TYPE9("9","山脉"),
    TYPE10("10","碑塔"),
    TYPE11("11","红色旅游"),
    TYPE12("12","寺庙"),
    OTHER("13","其他"),
    ;

    private String code;
    private String desc;

    public static List<TravelTypeItem> getTravelType(){
        return Arrays.stream(TravelTypeEnum.values()).map(i->{
           TravelTypeItem item = new TravelTypeItem();
           item.setType(i.getCode());
           item.setName(i.getDesc());
           return item;
        }).collect(Collectors.toList());
    }

    TravelTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getByType(String type) {
        return Arrays.stream(TravelTypeEnum.values()).filter(i->i.getCode().equals(Integer.parseInt(type)))
                .map(i->i.getDesc()).findFirst().orElse(null);
    }

    public static String getByName(String type) {
        return Arrays.stream(TravelTypeEnum.values()).filter(i->i.getDesc().equals(type))
                .map(i->i.getDesc()).findFirst().orElse(null);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
