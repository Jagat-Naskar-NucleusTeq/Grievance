package com.feedback.filter;


import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.feedback.serviceImplementation.AuthenticationServiceImpl;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;


class AuthenticationFilterTest {


    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain chain;

    @Mock
    private AuthenticationServiceImpl authenticationServiceImpl;

    private AuthenticationFilter filter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        filter = new AuthenticationFilter(authenticationServiceImpl);
    }

    @Test
    void testDoFilter_Authorized() throws Exception {
        // Arrange
        when(request.getMethod()).thenReturn("GET");
        when(request.getHeader("Email")).thenReturn("am1lQG51Y2xldXN0ZXFjb20="); // Original string
        when(request.getHeader("Password")).thenReturn("Sm1lQDEyMzQ="); // Original string
        when(authenticationServiceImpl.authenticateAdmin(anyString(), anyString())).thenReturn(true);

        // Act
        filter.doFilter(request, response, chain);

        // Assert
        verify(chain).doFilter(request, response);
    }

    @Test
    void testDoFilter_Unauthorized() throws Exception {
        // Arrange
        when(request.getMethod()).thenReturn("GET");
        when(request.getHeader("Email")).thenReturn("am1lQG51Y2xldXN0ZXFjb20");
        when(request.getHeader("Password")).thenReturn("Sm1lQDEyMzQ=");

        when(authenticationServiceImpl.authenticateAdmin(anyString(), anyString())).thenReturn(false);

        // Act
        filter.doFilter(request, response, chain);

        // Assert
        verify(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid credentials");
    }

//    @Test
//    public void testDoFilterAuthenticated() throws IOException, ServletException {
//        // Mocking dependencies
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        FilterChain chain = mock(FilterChain.class);
//        AuthenticationServiceImpl authService = mock(AuthenticationServiceImpl.class);
//
//        // Setting up headers
//        when(request.getHeader("Email")).thenReturn("base64EncodedEmail");
//        when(request.getHeader("Password")).thenReturn("base64EncodedPassword");
//
//        // Mocking authentication
//        when(authService.authenticateAdmin(anyString(), anyString())).thenReturn(true);
//
//        // Creating the filter
//        AuthenticationFilter filter = new AuthenticationFilter(authService);
//
//        // Calling the doFilter method
//        filter.doFilter(request, response, chain);
//
//        // Verifying that chain.doFilter() is called
//        verify(chain).doFilter(request, response);
//    }


}
