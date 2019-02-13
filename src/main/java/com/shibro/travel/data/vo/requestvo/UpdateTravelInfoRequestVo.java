package com.shibro.travel.data.vo.requestvo;

import com.shibro.travel.data.vo.BaseRequestVo;
import lombok.Data;

import java.util.List;

@Data
public class UpdateTravelInfoRequestVo extends BaseRequestVo {
    private Integer id;

    private String name;

    private String description;

    private String price;

    private Integer travelLevel;

    private Integer ranking;

    private String type;

    private String mainPictureUrl;

    private List<String> otherPictureUrls;

    private List<String> busRoutes;

    private Double recommendLevel;

    private String editorComments;
}
