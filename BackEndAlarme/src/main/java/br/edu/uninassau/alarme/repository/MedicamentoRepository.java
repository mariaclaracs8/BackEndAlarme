package br.edu.uninassau.alarme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.uninassau.alarme.entity.Medicamento;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
}