package personal.JavaSpringWeather.restSvc;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import personal.JavaSpringWeather.model.CurrentWeather;
import personal.JavaSpringWeather.model.FormInput;
import personal.JavaSpringWeather.model.GeoCode;
import personal.JavaSpringWeather.model.Weather;

@Component
public class ApiCaller {

	public Weather callOpenWeather(FormInput form) {
		Weather weather = new Weather();
		GeoCode geoCode = new GeoCode();
		String openWeatherUrl = "http://api.openweathermap.org/data/2.5/forecast?q="+form.getLocation()+",us&appid=5e9d8d52fa5ec969af1540d864960c64&units=imperial";
		ObjectMapper objectMapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		try {
			ResponseEntity<String> response = restTemplate.getForEntity(
					openWeatherUrl, String.class);
			 weather = objectMapper.readValue(response.getBody(), Weather.class);
			System.out.println(geoCode);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return weather;
	}
	
	public CurrentWeather callTodayOpenWeather(FormInput form) {
		CurrentWeather weather = new CurrentWeather();
		GeoCode geoCode = new GeoCode();
		String openWeatherUrl = "http://api.openweathermap.org/data/2.5/weather?q="+form.getLocation()+"&appid=5e9d8d52fa5ec969af1540d864960c64&units=imperial";
		ObjectMapper objectMapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		try {
			ResponseEntity<String> response = restTemplate.getForEntity(
					openWeatherUrl, String.class);
			 weather = objectMapper.readValue(response.getBody(), CurrentWeather.class);
			System.out.println(geoCode);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return weather;
	}
	
	public GeoCode callGeoCode(FormInput form) {
		GeoCode geoCode = new GeoCode();
		String geocodeWeatherUrl ="https://api.opencagedata.com/geocode/v1/json?q="+form.getLocation()+"\\&key=d477a337fe3d4842a42602b0046299b5";
		ObjectMapper objectMapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		try {
			ResponseEntity<String> response = restTemplate.getForEntity(
					geocodeWeatherUrl, String.class);
			geoCode = objectMapper.readValue(response.getBody(), GeoCode.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return geoCode;
	}
}
