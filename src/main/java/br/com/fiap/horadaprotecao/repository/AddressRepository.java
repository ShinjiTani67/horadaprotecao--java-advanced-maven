package br.com.fiap.horadaprotecao.repository;

import br.com.fiap.horadaprotecao.entity.Address;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@Entity
public interface AddressRepository extends JpaRepository<UUID, Address> {
}
