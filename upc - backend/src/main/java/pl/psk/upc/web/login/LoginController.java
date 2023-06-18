package pl.psk.upc.web.login;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.psk.upc.application.login.LoginService;
import pl.psk.upc.web.UpcRestPaths;

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

}
