package travel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import travel.entity.User;
import travel.model.request.UserRequest;
import travel.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController extends BaseController {


    private UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<Void> insert(@Valid @RequestBody UserRequest userRequest) {
        userService.insert(userRequest);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserByUserName(@RequestParam String userName) {
//        return new ResponseEntity<>(userService.findUserByUserName(userName), HttpStatus.OK);
        return ResponseEntity.ok(userService.findUserByUserName(userName));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.findAll());
    }
}
