package br.com.samuel.productapi.jwt.interceptor;

import br.com.samuel.productapi.jwt.service.JwtServiceInterface;
import feign.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {

    private static  final String AUTHORIZATION = "Authorization";

    @Autowired
    private JwtServiceInterface jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(isOptions(request)){
            return true;
        }
        var authorization = request.getHeader(AUTHORIZATION);
        jwtService.validateAuthorization(authorization);
        return true;
    }

    private boolean isOptions(HttpServletRequest request){
        return Request.HttpMethod.OPTIONS.name().equals(request.getMethod());

    }
}
