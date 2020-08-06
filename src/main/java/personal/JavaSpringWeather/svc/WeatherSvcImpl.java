package personal.JavaSpringWeather.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import personal.JavaSpringWeather.model.FormInput;
import personal.JavaSpringWeather.model.List;
import personal.JavaSpringWeather.model.Weather;
import personal.JavaSpringWeather.restSvc.ApiCaller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

@Service
public class WeatherSvcImpl implements WeatherSvc {

	@Autowired
	ApiCaller apiCaller;

	@Override
	public Weather weatherAGoGo(FormInput form) {
		Weather returnedWeather = apiCaller.callOpenWeather(form);
		if(returnedWeather.getCod() == null) {
			return returnedWeather;
		}
		List[] list = returnedWeather.getList();
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		for (List singleList : list) {
			LocalDateTime dateTime = LocalDateTime.parse(singleList.getDt_txt(), formatter);
			singleList.setDate(dateTime.format(dateFormatter));
			singleList.setTime(dateTime.format(timeFormatter));
		}
		return returnedWeather;
	}

}
