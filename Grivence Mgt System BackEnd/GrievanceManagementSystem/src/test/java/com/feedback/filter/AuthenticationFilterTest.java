package com.feedback.filter;


import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.feedback.serviceImplementation.AuthenticationService;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class AuthenticationFilterTest {


    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain chain;

    @Mock
    private AuthenticationService authenticationService;

    private AuthenticationFilter filter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        filter = new AuthenticationFilter(authenticationService);
    }

    @Test
    void testDoFilter_Authorized() throws Exception {
        // Arrange
        when(request.getMethod()).thenReturn("GET");
        when(request.getHeader("Email")).thenReturn("am1lQG51Y2xldXN0ZXFjb20="); // Original string
        when(request.getHeader("Password")).thenReturn("Sm1lQDEyMzQ="); // Original string
        when(authenticationService.authenticateAdmin(anyString(), anyString())).thenReturn(true);

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

        when(authenticationService.authenticateAdmin(anyString(), anyString())).thenReturn(false);

        // Act
        filter.doFilter(request, response, chain);

        // Assert
        verify(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid credentials");
    }


}
