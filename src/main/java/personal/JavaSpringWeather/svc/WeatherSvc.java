package personal.JavaSpringWeather.svc;

import personal.JavaSpringWeather.model.FormInput;
import personal.JavaSpringWeather.model.Weather;

public interface WeatherSvc {

	public Weather weatherAGoGo(FormInput weather);
}
