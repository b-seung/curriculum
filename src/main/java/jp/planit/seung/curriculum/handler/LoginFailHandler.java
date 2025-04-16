package jp.planit.seung.curriculum.handler;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.planit.seung.curriculum.dto.base.BaseErrorResponse;

@Component
public class LoginFailHandler extends SimpleUrlAuthenticationFailureHandler {
  @Override
  public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
      AuthenticationException e) throws IOException, ServletException {

    ObjectMapper mapper = new ObjectMapper();

    BaseErrorResponse responseDataDTO = new BaseErrorResponse();

    String errorMessage = "";
    if (e instanceof BadCredentialsException || e instanceof InternalAuthenticationServiceException) {
      errorMessage = "IDまたはパスワードが正しくありません。";
    } else if (e instanceof UsernameNotFoundException) {
      errorMessage = "登録されていないIDです。";
    } else {
      errorMessage = "システムエラーが発生しました。\nしばらくたってから再度、お手続きください。";
    }

    errorMessage = URLEncoder.encode(errorMessage, "UTF-8");

    responseDataDTO.setError(HttpStatus.METHOD_NOT_ALLOWED);
    responseDataDTO.setMessage(errorMessage);

    httpServletResponse.setCharacterEncoding("UTF-8");
    httpServletResponse.setStatus(HttpServletResponse.SC_OK);
    httpServletResponse.getWriter().println(mapper.writeValueAsString(responseDataDTO));
    httpServletResponse.getWriter().flush();
  }
}
