package com.ead.AuthUser.controllers;

import com.ead.AuthUser.dtos.UserDto;
import com.ead.AuthUser.models.UserModel;
import com.ead.AuthUser.services.impl.UserServiceImpl;
import com.fasterxml.jackson.annotation.JsonView;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {

  private UserServiceImpl userService;

  private UserController(UserServiceImpl userService) {
    this.userService = userService;
  }

  @GetMapping()
  public ResponseEntity<Page<UserModel>> getAllUsers(@PageableDefault (page = 0, size = 10, sort = "userId", direction = Sort.Direction.ASC)
                                                      Pageable pageable) {
    Page<UserModel> userModelPage = userService.findAllUsers(pageable);
    return ResponseEntity.status(HttpStatus.OK).body(userModelPage);
  }

  @GetMapping("/{userId}")
  public ResponseEntity<Object> getOneUser(@PathVariable (value = "userId") UUID userId) {
    Optional<UserModel> userModelOptional = userService.findById(userId);
    if (userModelOptional.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<Object> deleteUser(@PathVariable (value = "userId") UUID userId) {
    Optional<UserModel> userModelOptional = userService.findById(userId);
    if (userModelOptional.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    userService.delete(userModelOptional.get());
    return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado");
  }

  @PutMapping("/{userId}")
  public ResponseEntity<Object> updateUser (@PathVariable (value = "userId") UUID userId,
                                             @RequestBody @JsonView(UserDto.UserView.UserPut.class )
                                             @Validated(UserDto.UserView.UserPut.class) UserDto userDto ) {
    Optional<UserModel> userModelOptional = userService.findById(userId);
    if (userModelOptional.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
    }
    var userModel = userModelOptional.get();
    userModel.setFullname(userDto.getFullname());
    userModel.setPhoneNumber(userDto.getPhoneNumber());
    userModel.setCpf(userDto.getCpf());
    userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
    userService.save(userModel);
    return ResponseEntity.status(HttpStatus.OK).body(userModel);
  }

  @PutMapping("/{userId}/password")
  public ResponseEntity<Object> updateUserPassword(@PathVariable (value = "userId") UUID userId,
                                                    @RequestBody @JsonView(UserDto.UserView.PasswordPut.class)
                                                    @Validated(UserDto.UserView.PasswordPut.class) UserDto userDto) {
    Optional<UserModel> userModelOptional = userService.findById(userId);
    if (userModelOptional.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
    }
    if (!userModelOptional.get().getPassword().equals(userDto.getOldPassword())) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Mismatched old password!");
    }
    var userModel = userModelOptional.get();
    userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
    userModel.setPassword(userDto.getPassword());
    userService.save(userModel);
    return ResponseEntity.status(HttpStatus.OK).body("Password updated successfully.");
  }

  @PutMapping("/{userId}/image")
  public ResponseEntity<Object> updateUserImage(@PathVariable (value = "userId") UUID userId,
                                                @RequestBody @JsonView(UserDto.UserView.ImagePut.class)
                                                @Validated(UserDto.UserView.ImagePut.class) UserDto userDto) {

    Optional<UserModel> userModelOptional = userService.findById(userId);
    if (userModelOptional.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
    }
    var userModel = userModelOptional.get();
    userModel.setImageUrl(userDto.getImageUrl());
    userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
    userService.save(userModel);
    return ResponseEntity.status(HttpStatus.OK).body(userModel);
  }


}
