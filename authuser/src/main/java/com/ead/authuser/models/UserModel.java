package com.ead.AuthUser.models;

import com.ead.AuthUser.enums.UserStatus;
import com.ead.AuthUser.enums.UserType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "TB_USERS")
public class UserModel implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID userId;
  @Column(nullable = false, unique = true, length = 50)
  private String username;
  @Column(nullable = false, unique = true, length = 50)
  private String email;
  @Column(nullable = false, length = 255)
  @JsonIgnore
  private String password;
  @Column(nullable = false, length = 150)
  private String fullname;
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private UserStatus userStatus;
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private UserType userType;
  @Column(length = 20)
  private String phoneNumber;
  @Column(length = 20)
  private String cpf;
  @Column()
  private String imageUrl;
  @Column(nullable = false)
  @JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
  private LocalDateTime creationDate;
  @Column(nullable = false)
  @JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
  private LocalDateTime lastUpdateDate;


}
