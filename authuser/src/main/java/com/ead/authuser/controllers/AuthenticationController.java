package com.ead.AuthUser.controllers;

import com.ead.AuthUser.dtos.UserDto;
import com.ead.AuthUser.enums.UserStatus;
import com.ead.AuthUser.enums.UserType;
import com.ead.AuthUser.models.UserModel;
import com.ead.AuthUser.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthenticationController {

  private UserService userService;

  private AuthenticationController(UserService userService){
    this.userService = userService;
  }

  @PostMapping("/signup")
  public ResponseEntity<Object> registerUser(@RequestBody
                                             @JsonView(UserDto.UserView.RegistrationPost.class)
                                             @Validated(UserDto.UserView.RegistrationPost.class) UserDto userDto) {
    if (userService.existsByUsername(userDto.getUsername())) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Username is Already Taken!");
    }
    if (userService.existsByEmail(userDto.getEmail())) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Email is Already Taken!");
    }
    var userModel = new UserModel();
    BeanUtils.copyProperties(userDto, userModel);
    userModel.setUserStatus(UserStatus.ACTIVE);
    userModel.setUserType(UserType.STUDENT);
    userModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
    userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
    userService.save(userModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
  }

}
