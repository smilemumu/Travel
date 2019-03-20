package com.shibro.travel.data.vo.requestvo;

import com.shibro.travel.data.vo.BaseRequestVo;
import lombok.Data;

@Data
public class TravelDetailQueryRequestVo extends BaseRequestVo {
    private String name;
}
