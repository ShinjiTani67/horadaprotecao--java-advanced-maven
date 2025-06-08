package br.com.fiap.horadaprotecao.repository;

import br.com.fiap.horadaprotecao.entity.Address;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address,UUID> {

    Optional<Address> findByUuid(UUID uuid);
    Optional<Address> findByUser_Email(String email);

}
