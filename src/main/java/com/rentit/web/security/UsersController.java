package com.rentit.web.security;
import com.rentit.security.Users;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/security/users/")
@Controller
@RooWebScaffold(path = "security/users/", formBackingObject = Users.class)
public class UsersController {
}
