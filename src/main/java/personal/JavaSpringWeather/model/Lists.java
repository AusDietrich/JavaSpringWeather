package personal.JavaSpringWeather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Lists {
	public Main main;
	public String dt_txt;
	public String date;
	public String time;
}
