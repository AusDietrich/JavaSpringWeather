package personal.JavaSpringWeather.svc;

import java.util.List;

import personal.JavaSpringWeather.model.ColorsEntity;
import personal.JavaSpringWeather.model.FormInput;
import personal.JavaSpringWeather.model.GeoCode;
import personal.JavaSpringWeather.model.SeveralDayForcast;
import personal.JavaSpringWeather.model.Weather;

public interface WeatherSvc {

	public List<SeveralDayForcast> weatherAGoGo(FormInput weather);
	
	public GeoCode locationToGeo(FormInput form);
	
	public List<ColorsEntity> colors();
}
