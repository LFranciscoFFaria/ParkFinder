package pt.uminho.di.aa.parkfinder.api.auxiliar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoginRequest {
    public String email;
    public String password;
}
