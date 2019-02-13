package com.shibro.travel.controller;

import com.shibro.travel.data.vo.BaseRequestVo;
import com.shibro.travel.data.vo.BaseResponseVo;
import com.shibro.travel.data.vo.requestvo.DeleteTravelInfoRequestVo;
import com.shibro.travel.data.vo.requestvo.HomePageInfoRequestVo;
import com.shibro.travel.data.vo.requestvo.InsertTravelInfoRequestVo;
import com.shibro.travel.data.vo.requestvo.UpdateTravelInfoRequestVo;
import com.shibro.travel.service.TravelService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TravelController {

//    http://down-www.newasp.net/pcdown/soft/soft1/intellij.idea.2018.ult.rar
//    idea下载地址

    @Resource
    private TravelService travelService;

    /**
     * 首页信息
     * @param requestVo
     * @return
     */
    @RequestMapping(value = "/travel/getHomePageInfo", method = RequestMethod.POST)
    public BaseResponseVo getHomePageInfo(@RequestBody HomePageInfoRequestVo requestVo){
        return travelService.getHomePageInfo(requestVo);
    }


    /**
     * 新增旅游景点信息
     * @param requestVo
     * @return
     */
    @RequestMapping(value = "/travel/insertTravelInfo", method = RequestMethod.POST)
    public BaseResponseVo insertTravelInfo(@RequestBody InsertTravelInfoRequestVo requestVo){
        return travelService.insertTravelInfo(requestVo);
    }

    /**
     * 删除旅游景点信息
     * @param requestVo
     * @return
     */
    @RequestMapping(value = "/travel/deleteTravelInfo", method = RequestMethod.POST)
    public BaseResponseVo deleteTravelInfo(@RequestBody DeleteTravelInfoRequestVo requestVo){
        return travelService.deleteTravelInfo(requestVo);
    }


    /**
     * 更新旅游景点信息
     * @param requestVo
     * @return
     */
    @RequestMapping(value = "/travel/updateTravelInfo", method = RequestMethod.POST)
    public BaseResponseVo updateTravelInfo(@RequestBody UpdateTravelInfoRequestVo requestVo){
        return travelService.updateTravelInfo(requestVo);
    }

    /**
     * 旅游景点类别信息
     * @param requestVo
     * @return
     */
    @RequestMapping(value = "/travel/queryTravelType", method = RequestMethod.POST)
    public BaseResponseVo queryTravelType(@RequestBody BaseRequestVo requestVo){
        return travelService.queryTravelType(requestVo);
    }
}
