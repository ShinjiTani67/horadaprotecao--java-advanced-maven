package br.com.fiap.horadaprotecao.repository;

import br.com.fiap.horadaprotecao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository <User, UUID> {
    Optional<User> findByUuid(UUID uuid);
}
