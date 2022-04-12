package br.com.samuel.productapi.interceptor;

import br.com.samuel.productapi.exception.ValidationException;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class FeignClientAuthInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION = "Authorization";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        var currentRequest = getCurrentRequest();
        requestTemplate
                .header(AUTHORIZATION, currentRequest.getHeader(AUTHORIZATION));
    }

    private HttpServletRequest getCurrentRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes())
                    .getRequest();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ValidationException("The current request could not be processed");
        }
    }
}
