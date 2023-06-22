package pt.uminho.di.aa.parkfinder.api.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GestorDTO {
    Integer id;
    String nome;
    String email;
    Integer nr_telemovel;
    String password;
    List<Integer> ids_parques;
    List<Integer> ids_admins;
}
