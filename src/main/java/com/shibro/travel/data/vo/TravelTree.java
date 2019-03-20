package com.shibro.travel.data.vo;

import lombok.Data;

import java.util.List;

@Data
public class TravelTree {
    private String type;
    private List<String> name;
}
