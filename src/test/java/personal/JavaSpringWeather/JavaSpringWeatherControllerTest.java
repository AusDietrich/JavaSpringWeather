package personal.JavaSpringWeather;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import personal.JavaSpringWeather.api.WeatherController;

@RunWith(MockitoJUnitRunner.class)
public class JavaSpringWeatherControllerTest {

	@Mock
	ModelAndView modelAndView;
	@InjectMocks
	WeatherController classUnderTest;
	
	@Test
	public void index() {
//		classUnderTest.index();
	}
}
