package jp.planit.seung.curriculum.handler;

import java.io.IOException;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.planit.seung.curriculum.constants.SessionConst;
import jp.planit.seung.curriculum.dto.base.BaseResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  private final HttpSession session;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {

    session.setAttribute(SessionConst.AUTH, authentication);

    ObjectMapper mapper = new ObjectMapper();

    BaseResponse responseDataDTO = new BaseResponse();

    responseDataDTO.setHttpStatus(HttpStatus.OK.value());

    response.setCharacterEncoding("UTF-8");
    response.setStatus(HttpServletResponse.SC_OK);
    response.getWriter().println(mapper.writeValueAsString(responseDataDTO));
    response.getWriter().flush();
  }
}
