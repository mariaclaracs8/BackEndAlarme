package br.edu.uninassau.alarme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.uninassau.alarme.entity.Historico;

public interface HistoricoRepository extends JpaRepository<Historico, Long> {
}