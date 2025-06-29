    package com.billing.controller;

    import com.billing.dto.LoginRequest;
    import com.billing.dto.SignupRequest;
    import com.billing.model.User;
    import com.billing.service.UserService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.Optional;

    @RestController
    @RequestMapping("/api/auth")
    public class AuthController {

        @Autowired
        private UserService userService;
        @PostMapping("/login")
        public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
            Optional<User> userOpt = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
            if (userOpt.isPresent()) {
                return ResponseEntity.ok(userOpt.get());
            } else {
                return ResponseEntity.status(401).body("Invalid username or password");
            }
        }
        @PostMapping("/signup")
        public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
            if (userService.existsByUsername(signupRequest.getUsername())) {
                return ResponseEntity.badRequest().body("Username already exists");
            }

            User user = new User();
            user.setUsername(signupRequest.getUsername());
            user.setPassword(signupRequest.getPassword());
            user.setName(signupRequest.getName());

            return ResponseEntity.ok(userService.register(user));
        }
    }
