package api.dto.astrologer_settings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AstrologerTimeSettingsDTO {
    EachDayDTO eachDay;
    int gmt;
    List<DayDTO> days;
    int maxSessions;
    int interval;
}
