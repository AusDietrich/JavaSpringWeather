package personal.JavaSpringWeather.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import personal.JavaSpringWeather.model.CurrentWeather;
import personal.JavaSpringWeather.model.FormInput;
import personal.JavaSpringWeather.model.GeoCode;
import personal.JavaSpringWeather.model.Lists;
import personal.JavaSpringWeather.model.SeveralDayForcast;
import personal.JavaSpringWeather.model.Weather;
import personal.JavaSpringWeather.restSvc.ApiCaller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherSvcImpl implements WeatherSvc {

	@Autowired
	ApiCaller apiCaller;

	@Override
	public List<SeveralDayForcast> weatherAGoGo(FormInput form) {
		Weather returnedWeather = apiCaller.callOpenWeather(form);
		List<SeveralDayForcast> forcast = new ArrayList<>();
		if(returnedWeather.getCod() == null) {
			return forcast;
		}
		CurrentWeather returnWeather = apiCaller.callTodayOpenWeather(form);
		Lists[] list = returnedWeather.getList();
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDate today = LocalDate.now();
		String now = today.format(dateFormatter);
		SeveralDayForcast e = new SeveralDayForcast();
		e.setDate(now);
		e.setTempMax(returnWeather.getMain().getTemp_max());
		e.setTempMin(returnWeather.getMain().getTemp_min());
		forcast.add(e);
		int x = 0;
		for (Lists singleList : list) {
			LocalDateTime dateTime = LocalDateTime.parse(singleList.getDt_txt(), formatter);
			singleList.setDate(dateTime.format(dateFormatter));
			singleList.setTime(dateTime.format(timeFormatter));
			for (SeveralDayForcast day : forcast) {
				x = 0;
				if(!(day.getDate().equals(singleList.getDate()))) {
					x++;
				}
			}
			if(x==1) {
				e = new SeveralDayForcast();
				e.setDate(singleList.getDate());
				e.setTempMax(singleList.getMain().getTemp_max());
				e.setTempMin(singleList.getMain().getTemp_min());
				forcast.add(e);
			}
		}
		for (Lists singleList : list) {
			for (SeveralDayForcast day : forcast) {
				if (day.getDate().equals(singleList.getDate())) {
					if (day.getTempMax() < singleList.getMain().getTemp_max()) {
						day.setTempMax(singleList.getMain().getTemp_max());
					}
					if (day.getTempMin() > singleList.getMain().getTemp_min()) {
						day.setTempMin(singleList.getMain().getTemp_min());
					}
				}
			}
		}
		System.out.println(forcast);
		return forcast;
	}
	
	@Override
	public GeoCode locationToGeo(FormInput form) {
		GeoCode returnedGeo = apiCaller.callGeoCode(form);
		return returnedGeo;
	}

}
