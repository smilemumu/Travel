package com.shibro.travel.data.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class HomePageInfoItem {

    private Integer id;

    private String name;

    private String description;

    private String price;

    private Integer travelLevel;

    private Integer ranking;

    private String type;

    private List<String> busRoutes;

    private Double recommendLevel;

    private String editorComments;

    private String mainPictureUrl;

    private List<String> otherPictureUrls;
}
