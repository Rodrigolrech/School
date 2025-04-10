package com.ead.AuthUser.repositories;

import com.ead.AuthUser.models.UserModel;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);

  Page<UserModel> findAll(Specification<com.ead.AuthUser.models.UserModel> spec, Pageable pageable);
}
