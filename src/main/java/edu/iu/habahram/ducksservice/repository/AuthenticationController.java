package edu.iu.habahram.ducksservice.repository;

import edu.iu.habahram.ducksservice.model.Customer;
import edu.iu.habahram.ducksservice.security.TokenService;
import org.apache.catalina.User;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AuthenticationController {
    private AuthenticationManager authenticationManager;
    private TokenService tokenService;
    CustomerRepository customerRepository;

    public AuthenticationController(AuthenticationManager authenticationManager,
                                    TokenService tokenService,
                                    CustomerRepository customerRepository) {
        this.authenticationManager = authenticationManager;
        this.customerRepository = customerRepository;
        this.tokenService = tokenService;
    }

    @PostMapping("/signin")
        public String login(@RequestBody Customer customer) {
            Authentication authentication = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    customer.username(),
                                    customer.password()));
            return tokenService.generateToken(authentication);
        }
}
