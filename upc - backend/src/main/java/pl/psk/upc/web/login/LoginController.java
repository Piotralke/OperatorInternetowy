package pl.psk.upc.web.login;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.psk.upc.application.login.LoginService;
import pl.psk.upc.web.UpcRestPaths;

import java.security.Principal;

@RestController
public class LoginController {

    private final LoginService service;

    public LoginController(LoginService service) {
        this.service = service;
    }

    @PostMapping(UpcRestPaths.LOGIN)
    public String login(@RequestBody LoginRequestDto loginRequestDto) {
        return service.login(loginRequestDto);
    }

    @GetMapping(UpcRestPaths.LOGIN_OAUTH)
    public void loginOauth(@AuthenticationPrincipal OAuth2User user) {
//    public void loginOauth(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        SecurityContext context = SecurityContextHolder.getContext();
        System.out.println("loginOauth");
    }

}
