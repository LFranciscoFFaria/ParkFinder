package pt.uminho.di.aa.parkfinder.api.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GestorDTO {
    String nome;
    String email;
    Integer nr_telemovel;
    String password;
    List<Integer> ids_parques;
    List<Integer> ids_admins;
}
