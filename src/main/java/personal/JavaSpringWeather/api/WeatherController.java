package personal.JavaSpringWeather.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import personal.JavaSpringWeather.model.ColorsEntity;
import personal.JavaSpringWeather.model.FormInput;
import personal.JavaSpringWeather.model.GeoCode;
import personal.JavaSpringWeather.model.Results;
import personal.JavaSpringWeather.model.SeveralDayForcast;
import personal.JavaSpringWeather.model.Weather;
import personal.JavaSpringWeather.svc.WeatherSvc;

@RestController
public class WeatherController {

	@Autowired
	WeatherSvc svc;
	
	@GetMapping("/")
	public ModelAndView index(Model model, FormInput form, Weather weather) {
		model.addAttribute("form", form);
		model.addAttribute("weather", "");
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("index");
	    return modelAndView;
	}
	@GetMapping("/Colors")
	public ModelAndView colors(Model model){
		
		List<ColorsEntity> colors = svc.colors();
		model.addAttribute("allColors", colors);
		System.out.println(colors.get(0).getColors());
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("colors");
	    return modelAndView;
	}
	
	@PostMapping("/weatherAPI")
	public ModelAndView weatherHotSpot(Model model, FormInput form) {
		GeoCode geoCode = svc.locationToGeo(form);
		Results[] results = geoCode.getResults();
			model.addAttribute("url",
					"https://openweathermap.org/weathermap?basemap=map&cities=false&layer=temperature&lat=" + results[0].getBounds().getNortheast().getLat() 
							+ "&lon=" + results[0].getBounds().getNortheast().getLng() + "&zoom=10");
			List<SeveralDayForcast> weather = svc.weatherAGoGo(form);
//		if (weather.getCod() == null) {
//			model.addAttribute("form", form);
//			model.addAttribute("weather", "No City Found "+form.getLocation());
//			ModelAndView modelAndView = new ModelAndView();
//			modelAndView.setViewName("index");
//			return modelAndView;
//		}
		model.addAttribute("weather", weather);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("weatherTable");
		return modelAndView;
	}
}
