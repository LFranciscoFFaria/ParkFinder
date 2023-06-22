package pt.uminho.di.aa.parkfinder.api.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AdminDTO {
    Integer id;
    String nome;
    String email;
    Integer nr_telemovel;
    String password;
    Integer id_gestor;
    List<Integer> ids_parques;
}
