package api.dto.sms_dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SmsDTO {
    private long id;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime received;
    private String phone;
    private String message;
    @JsonProperty("to_phone")
    private String toPhone;
    private String sent;
}
