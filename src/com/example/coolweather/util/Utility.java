package com.example.coolweather.util;

import com.example.coolweather.model.City;
import com.example.coolweather.model.CoolWeatherDB;
import com.example.coolweather.model.County;
import com.example.coolweather.model.Province;

import android.text.TextUtils;
import android.util.Log;

public class Utility {
	
	//解析和处理服务器返回的全国各省数据
	public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB, String response) {
		
		if (!TextUtils.isEmpty(response)) {
			String [] allProvinces =response.split(",");
			
			if (allProvinces != null && allProvinces.length > 0) {
				for (String p : allProvinces) {
					String [] array = p.split("\\|");
					Province province = new Province();
					province.setProvinceName(array[1]);
					
					province.setProvinceCode(array[0]);
					coolWeatherDB.saveProvince(province);
				}
				return true;
			}
		}
		return false;
	}
	
	//解析和处理服务器返回的市级数据
	public static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB, String response, int provinceId) {
		
		if (!TextUtils.isEmpty(response)) {
			String [] allCities = response.split(",");
			if (allCities != null && allCities.length > 0) {
				for (String c : allCities) {
					String [] array = c.split("\\|");
					City city = new City();
					city.setCityCode(array[0]);
					city.setCityName(array[1]);
					city.setProvinceId(provinceId);
					coolWeatherDB.saveCity(city);
				}
				return true;
			}
		}
		return false;
		
	}
	
	//解析和处理服务器返回的县级数据
	public static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB, String response, int cityId) {
		
		if (!TextUtils.isEmpty(response)) {
			String [] allCounties = response.split(",");
			Log.d("haha", allCounties[0]);
			Log.d("haha", allCounties[1]);
			if (allCounties != null && allCounties.length > 0) {
				for (String c : allCounties) {
					String [] array = c.split("\\|");
					County county = new County();
					county.setCountyCode(array[0]);
					county.setCountyName(array[1]);
					Log.d("haha", county.getCountyName());
					county.setCityId(cityId);
					coolWeatherDB.saveCounty(county);
				}
				return true;
			}
		}
		return false;
	}
	
}
