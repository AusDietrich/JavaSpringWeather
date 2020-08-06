package personal.JavaSpringWeather.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import personal.JavaSpringWeather.model.FormInput;
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
	
	@PostMapping("/weatherAPI")
	public ModelAndView weatherHotSpot(Model model, FormInput form) {
		Weather weather = svc.weatherAGoGo(form);
		if (weather.getCod()==null) {
			model.addAttribute("form", form);
			model.addAttribute("weather", "No City Found "+form.getLocation());
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("index");
			return modelAndView;
		}
		model.addAttribute("weather", weather.getList());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("weatherTable");
		return modelAndView;
	}
}
