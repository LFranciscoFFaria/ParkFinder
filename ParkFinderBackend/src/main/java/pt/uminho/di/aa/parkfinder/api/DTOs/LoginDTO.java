package pt.uminho.di.aa.parkfinder.api.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoginDTO {
    @JsonProperty(required = true)
    @Schema(implementation = String.class, example = "alex@gmail.com")
    public String email;
    @JsonProperty(required = true)
    @Schema(implementation = String.class, example = "mypass1234")
    public String password;
}
