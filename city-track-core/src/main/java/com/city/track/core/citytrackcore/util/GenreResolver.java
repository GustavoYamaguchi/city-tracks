package com.city.track.core.citytrackcore.util;

import com.city.track.core.citytrackcore.bean.WeatherBean;

public class GenreResolver {
    public static String getGenre(WeatherBean weatherBean) {
        Double temperature = weatherBean.getTemperature();
        if (temperature > 30) {
            return "Party";
        } else if (temperature >= 15 && temperature <= 30) {
            return "Pop";
        } else if (temperature >= 10 && temperature < 15) {
            return "Rock";
        } else {
            return "Classical";
        }
    }
}
