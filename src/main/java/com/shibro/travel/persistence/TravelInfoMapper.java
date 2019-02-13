package com.shibro.travel.persistence;


import com.shibro.travel.data.entity.TravelInfo;
import com.shibro.travel.data.vo.requestvo.HomePageInfoRequestVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TravelInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(TravelInfo record);

    TravelInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TravelInfo record);

    List<TravelInfo> selectByParam(HomePageInfoRequestVo param);

    Integer selectCount(HomePageInfoRequestVo param);
}