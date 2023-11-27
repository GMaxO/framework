package api.dto.http_transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class HttpTransactionDTO {
    public String method;
    public String url;

    @Override
    public String toString() {
        return method + " " + url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HttpTransactionDTO that = (HttpTransactionDTO) o;
        return Objects.equals(method, that.method) && Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, url);
    }
}
