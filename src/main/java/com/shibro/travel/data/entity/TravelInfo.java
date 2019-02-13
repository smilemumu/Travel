package com.shibro.travel.data.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class TravelInfo {
    private Integer id;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer travelLevel;

    private Integer ranking;

    private String type;

    private String mainPictureUrl;

    private String otherPictureUrl;

    private String busRoutes;

    private Double recommendLevel;

    private String editorComments;

    private Date updateTime;
}