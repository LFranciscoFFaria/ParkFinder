package pt.uminho.di.aa.parkfinder.logicaParques.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@AllArgsConstructor
@Getter
@Setter
public class ParqueDTO {
    Optional<Integer> id;
    Optional<String> nome;
    Optional<String> morada;
    Optional<String> descricao;
    Optional<Float> latitude;
    Optional<Float> longitude;
    Optional<Boolean> disponivel;
    Optional<Integer> instantaneos_livres;
    Optional<Integer> instantaneos_total;
    Optional<Integer> total_lugares;
    Optional<String> caminho_foto;
}
