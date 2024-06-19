package com.example.Foodle.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.Foodle.entity.customOAuth2User;
import com.example.Foodle.provider.JwtProvider;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

    private final JwtProvider jwtProvider;
    
    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request, 
        HttpServletResponse response,
		Authentication authentication
        ) throws IOException, ServletException {
		    customOAuth2User oauth2User = (customOAuth2User) authentication.getPrincipal();

            String userId = oauth2User.getName();
            String token = jwtProvider.create(userId);

            response.sendRedirect("http://localhost:3000/auth/oauth-response/" + token + "/3600");
            

	}
}
