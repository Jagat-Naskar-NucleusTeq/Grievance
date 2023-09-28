package com.feedback.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.feedback.serviceImplementation.AuthenticationServiceImpl;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * This filter handles authentication for requests.
 */
@Component
public class AuthenticationFilter implements Filter {

  /**
   * AuthenticationService object.
   */
  @Autowired
  private AuthenticationServiceImpl authenticationServiceImpl;

  /**
   * Constructor for AuthenticationFilter.
   *
   * @param authenticationServiceImpl The AuthenticationService to be injected.
   *
   */
  @Autowired
  public AuthenticationFilter(
      final AuthenticationServiceImpl authenticationServiceImpl) {
    this.authenticationServiceImpl = authenticationServiceImpl;
  }

  /**
   * Performs authentication and authorization for the request.
   *
   * @param request The servlet request.
   *
   * @param response The servlet response.
   *
   * @param chain The filter chain.
   *
   * @throws IOException if an I/O error occurs.
   *
   * @throws ServletException if a servlet error occurs.
   */
  @Override
  public void doFilter(final ServletRequest request,
          final ServletResponse response,
          final FilterChain chain)
      throws IOException, ServletException {
    System.out.println("Filter start");

    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
    if (httpRequest.getMethod().equals("OPTIONS")) {
      httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
      httpServletResponse.setHeader("Access-Control-Allow-Methods",
          "GET, POST, PUT, DELETE");
      httpServletResponse.setHeader("Access-Control-Allow-Headers",
          "Authorization, Content-Type, Email, Password");
      httpServletResponse.setContentType("application/json");
      httpServletResponse.setStatus(HttpServletResponse.SC_OK);
    } else {
      System.out.println(request);
      System.out.println(httpRequest.getHeader("Email"));
      String username1 = httpRequest.getHeader("Email");
      System.out.println(username1);
      String password2 = httpRequest.getHeader("Password");
      System.out.println(password2);
      String username = new String(Base64.getDecoder().decode(username1));
      if (authenticationServiceImpl.authenticateAdmin(username, password2)) {
        System.out.println("Authorized =>.... "
            + authenticationServiceImpl.authenticateAdmin(username, password2));
        chain.doFilter(request, response);
      } else {
        System.out.println("Else Filter: Unauthorized");
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
            "Invalid credentials");
      }
    }
    System.out.println("End Filter");
  }
}
