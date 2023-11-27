package api.dto.admin_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
public class AstrologerDTO {
    int userId;
    @JsonProperty("isActive")
    boolean isActive;
    @JsonProperty("hasSchedule")
    boolean hasSchedule;
    String fullName;
    @JsonProperty("formats")
    List<String> formats = new ArrayList<>();
}
