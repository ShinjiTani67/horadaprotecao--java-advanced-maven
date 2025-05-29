package br.com.fiap.horadaprotecao.repository;


import br.com.fiap.horadaprotecao.entity.FloodZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FloodZoneRepository extends JpaRepository<UUID, FloodZone> {
}
