package com.ead.authuser.specifications;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import com.ead.AuthUser.models.UserModel;

public class SpecificationTemplate {

  @And({
      @Spec(path = "userType", spec = Equal.class),
      @Spec(path = "email", spec = Like.class),
      @Spec(path = "userStatus", spec = Equal.class)
  })
  public interface UserSpec extends Specification<UserModel> {

  }
}
