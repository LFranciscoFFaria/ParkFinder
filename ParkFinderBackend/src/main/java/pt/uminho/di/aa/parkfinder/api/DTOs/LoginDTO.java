package pt.uminho.di.aa.parkfinder.api.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoginDTO {
    @JsonProperty(required = true)
    public String email;
    @JsonProperty(required = true)
    public String password;
}
