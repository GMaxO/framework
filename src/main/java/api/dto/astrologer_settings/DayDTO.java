package api.dto.astrologer_settings;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DayDTO {
    @JsonProperty("isActive")
    private boolean isActive;
    @JsonProperty("intervals")
    private List<IntervalDTO> intervals;
}
