package api.dto.admin_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServicesDTO {
    private int id;
    private int category;
    private int format;
    private int duration;
    private String visible;
    private double reward;
    private double linkReward;
    private PriceDTO price;
}
