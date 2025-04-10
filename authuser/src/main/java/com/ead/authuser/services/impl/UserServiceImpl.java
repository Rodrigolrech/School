package com.ead.AuthUser.services.impl;

import com.ead.AuthUser.models.UserModel;
import com.ead.AuthUser.repositories.UserRepository;
import com.ead.AuthUser.services.UserService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;

  private UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public List<UserModel> findAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public Optional<UserModel> findById(UUID userId) {
    return userRepository.findById(userId);
  }

  @Override
  public void delete(UserModel userModel) {
    userRepository.delete(userModel);
  }

  @Override
  public UserModel save(UserModel userModel) {
    return userRepository.save(userModel);
  }

  @Override
  public boolean existsByUsername(String username) {
    return userRepository.existsByUsername(username);
  }

  @Override
  public boolean existsByEmail(String email) {
    return userRepository.existsByEmail(email);
  }

  @Override
  public Page<UserModel> findAllUsers(Pageable pageable) {
    return userRepository.findAll(pageable);
  }

}
