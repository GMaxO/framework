package api.dto.admin_dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class PriceDTO {
    private String currency;
    private int value;
    private int discount;
}
