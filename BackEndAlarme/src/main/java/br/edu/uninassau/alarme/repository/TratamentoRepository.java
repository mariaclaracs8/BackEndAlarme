package br.edu.uninassau.alarme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.uninassau.alarme.entity.Tratamento;

public interface TratamentoRepository extends JpaRepository<Tratamento, Long> {
}