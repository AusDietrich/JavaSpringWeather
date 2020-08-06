package personal.JavaSpringWeather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
	public String cod;
	public String message;
	public List[] list;
}
