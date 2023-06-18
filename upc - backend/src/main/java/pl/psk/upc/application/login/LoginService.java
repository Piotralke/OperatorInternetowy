package pl.psk.upc.application.login;

import pl.psk.upc.web.login.LoginRequestDto;

public interface LoginService {

    String login(LoginRequestDto loginRequestDto);

}