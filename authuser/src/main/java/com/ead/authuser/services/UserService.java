package com.ead.AuthUser.services;

import com.ead.AuthUser.models.UserModel;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
  List<UserModel> findAllUsers();

  Optional<UserModel> findById(UUID userId);

  void delete(UserModel userModel);

  UserModel save(UserModel userModel);

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);

  Page<UserModel> findAllUsers(Pageable pageable);
}
