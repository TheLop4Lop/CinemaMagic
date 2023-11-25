package com.booster.CineMagic.Controller;

import com.booster.CineMagic.Service.JwtUtilService;
import com.booster.CineMagic.Util.AuthenticationRequest;
import com.booster.CineMagic.Util.TokenInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication/v0")
public class Authentication {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    private JwtUtilService jwtUtilService;

    @PostMapping("/authenticate")
    public ResponseEntity<TokenInfo> authenticate(@RequestBody AuthenticationRequest authenticationReq) {
        // logger.info("Autenticando al usuario {}", authenticationReq.getUsuario());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationReq.getUser(),
                        authenticationReq.getKey())); //autentica

        final UserDetails userDetails = userDetailsService.loadUserByUsername(
                authenticationReq.getUser()); // crea userdatails con las credenciales

        final String jwt = jwtUtilService.generateToken(userDetails); // se crean el token

        TokenInfo tokenInfo = new TokenInfo(jwt);

        return ResponseEntity.ok(tokenInfo);
    }
}
