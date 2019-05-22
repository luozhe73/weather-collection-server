package com.cloud.weather.job;

import com.cloud.weather.service.WeatherDataCollectionService;
import com.cloud.weather.vo.City;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataSyncJob extends QuartzJobBean {

    private final static Logger logger = LoggerFactory.getLogger(WeatherDataSyncJob.class);

    @Autowired
    private WeatherDataCollectionService weatherDataCollectionService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        logger.info("weather data sync job start");


        int i = 0;
        List<City> cityList = null;

        try {
            //TODO改为城市数据微服务来获取城市数据
            cityList = new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (City city : cityList) {
            String cityId = city.getCityId();
            logger.info("总共城市数:" + cityList.size() + "  正在获取第" + ++i + "个城市:" + city.getCityName() + cityId);
            weatherDataCollectionService.syncDataByCityId(cityId);
        }

        logger.info("weather data sync job end");
    }
}
