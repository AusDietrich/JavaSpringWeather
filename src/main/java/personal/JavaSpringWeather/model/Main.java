package personal.JavaSpringWeather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Main {
	Double temp;
	Double temp_min;
	Double temp_max;
}
