package pt.uminho.di.aa.parkfinder.api.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProgramadorDTO {
    String nome;
    String email;
    Integer nr_telemovel;
    String password;
}
