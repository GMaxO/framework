package api.dto.admin_dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsultationDTO {
    int id;
    int status;
    LocalDateTime start;
}
