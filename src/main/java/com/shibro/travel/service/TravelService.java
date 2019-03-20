package com.shibro.travel.service;

import com.shibro.travel.data.dto.HomePageInfo;
import com.shibro.travel.data.dto.HomePageInfoItem;
import com.shibro.travel.data.entity.TravelInfo;
import com.shibro.travel.data.enums.ErrorCodeEnum;
import com.shibro.travel.data.enums.TravelTypeEnum;
import com.shibro.travel.data.vo.BaseRequestVo;
import com.shibro.travel.data.vo.BaseResponseVo;
import com.shibro.travel.data.vo.TravelTree;
import com.shibro.travel.data.vo.requestvo.*;
import com.shibro.travel.persistence.TravelInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TravelService {

    private static final Logger LOG = LoggerFactory.getLogger(TravelService.class);

    @Resource
    private TravelInfoMapper travelInfoMapper;

    public BaseResponseVo getHomePageInfo(HomePageInfoRequestVo param) {
        //设置分页
        if(Objects.nonNull(param.getCount())&&Objects.nonNull(param.getPage())){
            Integer start = (param.getPage()-1)*param.getCount();
            Integer end = param.getPage()*param.getCount();
            param.setPage(start);
            param.setCount(end);
        }
        convertParam(param);
        LOG.info("getHomePageInfo 入参:");
        HomePageInfo homePageInfo = new HomePageInfo();
        List<TravelInfo> travelInfos = travelInfoMapper.selectByParam(param);
        Integer count = travelInfoMapper.selectCount(param);
        homePageInfo.setHomePageInfos(convertHomePageInfo(travelInfos));
        homePageInfo.setCount(count);
        BaseResponseVo responseVo = BaseResponseVo.successResponseVo(homePageInfo);
        return responseVo;
    }

    private void convertParam(HomePageInfoRequestVo param) {
        if(Objects.nonNull(param.getSort())){
            if(param.getSort().equals("recommendLevel")){
                param.setSort("recommend_level");
            }
            if(param.getSort().equals("travelLevel")){
                param.setSort("travel_level");
            }
        }
    }

    private List<HomePageInfoItem> convertHomePageInfo(List<TravelInfo> travelInfos) {
        List<HomePageInfoItem> homePageInfoItems = new ArrayList<>();
        HomePageInfoItem homePageInfoItem;
        for(TravelInfo item: travelInfos){
            homePageInfoItem = new HomePageInfoItem();
            homePageInfoItem.setId(item.getId());
            homePageInfoItem.setName(item.getName());
            homePageInfoItem.setDescription(item.getDescription());
            homePageInfoItem.setPrice(item.getPrice().toString());
            homePageInfoItem.setTravelLevel(item.getTravelLevel());
            homePageInfoItem.setRanking(item.getRanking());
            homePageInfoItem.setType(item.getType());
            homePageInfoItem.setMainPictureUrl(item.getMainPictureUrl());
            homePageInfoItem.setBusRoutes(convertBusRoutes(item.getBusRoutes()));
            homePageInfoItem.setRecommendLevel(item.getRecommendLevel());
            homePageInfoItem.setEditorComments(item.getEditorComments());
            homePageInfoItem.setOtherPictureUrls(convertOtherPictureUrls(item.getOtherPictureUrl()));
            homePageInfoItems.add(homePageInfoItem);
        }
        return homePageInfoItems;
    }

    private List<String> convertBusRoutes(String busRoutes) {
        List<String> busRoutesList  = Arrays.asList(busRoutes.split("##"));
        return busRoutesList;
    }

    /**
     * 库中存的  是字符串，返回给前端以List数组形式返回
     * @param otherPictureUrl
     * @return
     */
    private List<String> convertOtherPictureUrls(String otherPictureUrl) {
       List<String> otherPictureUrls  = Arrays.asList(otherPictureUrl.split(","));
       return otherPictureUrls;
    }

    public BaseResponseVo insertTravelInfo(InsertTravelInfoRequestVo requestVo) {
        Boolean flag =true;
        try{
            TravelInfo travelInfo = new TravelInfo();
            travelInfo.setName(requestVo.getName());
            travelInfo.setDescription(requestVo.getDescription());
            travelInfo.setPrice(new BigDecimal(requestVo.getPrice()).setScale(2,BigDecimal.ROUND_HALF_UP));
            travelInfo.setTravelLevel(requestVo.getTravelLevel());
            travelInfo.setRanking(requestVo.getRanking());
            travelInfo.setType(requestVo.getType());
            travelInfo.setMainPictureUrl(requestVo.getMainPictureUrl());
            travelInfo.setBusRoutes(list2StringBusRoutes(requestVo.getBusRoutes()));
            travelInfo.setRecommendLevel(requestVo.getRecommendLevel());
            travelInfo.setEditorComments(requestVo.getEditorComments());
            travelInfo.setOtherPictureUrl(list2String(requestVo.getOtherPictureUrls()));
            travelInfoMapper.insertSelective(travelInfo);
        }catch (Exception e){
            LOG.error("插入旅游景点信息失败,message:{}",e.getMessage());
            flag = false;
        }
        if(flag){
            return new BaseResponseVo(ErrorCodeEnum.SUCCESS);
        }else{
            return BaseResponseVo.failResponseVo();
        }
    }

    private String list2StringBusRoutes(List<String> busRoutes) {
        return busRoutes.stream().collect(Collectors.joining("##"));
    }

    private String list2String(List<String> otherPictureUrls) {
        return otherPictureUrls.stream().collect(Collectors.joining(","));
    }

    public BaseResponseVo deleteTravelInfo(DeleteTravelInfoRequestVo requestVo) {
        Boolean flag =true;
        try{
            travelInfoMapper.deleteByPrimaryKey(requestVo.getId());
        }catch (Exception e){
            LOG.error("删除旅游景点信息失败,message:{}",e.getMessage());
            flag = false;
        }
        if(flag){
            return new BaseResponseVo(ErrorCodeEnum.SUCCESS);
        }else{
            return BaseResponseVo.failResponseVo();
        }
    }

    public BaseResponseVo updateTravelInfo(UpdateTravelInfoRequestVo requestVo) {
        Boolean flag =true;
        try{
            TravelInfo travelInfo = new TravelInfo();
            travelInfo.setId(requestVo.getId());
            travelInfo.setName(requestVo.getName());
            travelInfo.setDescription(requestVo.getDescription());
            travelInfo.setPrice(new BigDecimal(requestVo.getPrice()).setScale(2,BigDecimal.ROUND_HALF_UP));
            travelInfo.setTravelLevel(requestVo.getTravelLevel());
            travelInfo.setRanking(requestVo.getRanking());
            travelInfo.setType(requestVo.getType());
            travelInfo.setMainPictureUrl(requestVo.getMainPictureUrl());
            travelInfo.setBusRoutes(list2StringBusRoutes(requestVo.getBusRoutes()));
            travelInfo.setRecommendLevel(requestVo.getRecommendLevel());
            travelInfo.setEditorComments(requestVo.getEditorComments());
            travelInfo.setOtherPictureUrl(list2String(requestVo.getOtherPictureUrls()));
            travelInfoMapper.updateByPrimaryKeySelective(travelInfo);
        }catch (Exception e){
            LOG.error("更新旅游景点信息失败,message:{}",e.getMessage());
            flag = false;
        }
        if(flag){
            return new BaseResponseVo(ErrorCodeEnum.SUCCESS);
        }else{
            return BaseResponseVo.failResponseVo();
        }
    }


    public BaseResponseVo queryTravelType(BaseRequestVo requestVo) {
        return BaseResponseVo.successResponseVo(TravelTypeEnum.getTravelType());
    }

    public BaseResponseVo getTravelDetailByName(TravelDetailQueryRequestVo requestVo) {
        if(StringUtils.isEmpty(requestVo.getName())){
            return BaseResponseVo.successResponseVo();
        }
        TravelInfo travelInfo = travelInfoMapper.selectByName(requestVo.getName());
        return BaseResponseVo.successResponseVo(travelInfo);
    }

    public BaseResponseVo getTravelTree(BaseRequestVo requestVo) {
        List<TravelTree> result  = new ArrayList<>();
        HomePageInfoRequestVo param = new HomePageInfoRequestVo();
        List<TravelInfo> travelInfos = travelInfoMapper.selectByParam(param);
        if(Objects.nonNull(travelInfos)){
            Map<String,List<TravelInfo>> mapR = travelInfos.stream().collect(Collectors.groupingBy(item->item.getType()));
            mapR.forEach((k,v)->{
                List<String> list = v.stream().map(item->item.getName()).collect(Collectors.toList());
                String type = k;
                TravelTree travelTree = new TravelTree();
                travelTree.setName(list);
                travelTree.setType(type);
                result.add(travelTree);
            });
        }
        return BaseResponseVo.successResponseVo(result);
    }
}
