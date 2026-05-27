package br.edu.uninassau.alarme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.uninassau.alarme.entity.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}