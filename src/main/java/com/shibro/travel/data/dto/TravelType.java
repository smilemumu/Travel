package com.shibro.travel.data.dto;

import lombok.Data;

import java.util.List;

@Data
public class TravelType {
    private List<TravelTypeItem> types;
}
