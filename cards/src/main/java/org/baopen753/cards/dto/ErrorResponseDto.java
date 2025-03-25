package org.baopen753.cards.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ErrorResponseDto {
    @JsonProperty("api_path")
    private String apiPath;

    @JsonProperty("error_code")
    private HttpStatus errorCode;

    @JsonProperty("error_message")
    private String errorMessage;

    @JsonProperty("time_stamp")
    private LocalDateTime timestamp;
}
