package personal.JavaSpringWeather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ColorsEntity {

	public int id;
	public String colors;
	public double price;
	public String description;
}
