// START SNIPPET  AuthFilter
package packt.java11.mybusiness.productinformation.auth;

//SNIPPET SKIP TILL "//IMPORT"

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//IMPORT

@Component
public class AuthFilter implements Filter {
    public static final int NOT_AUTHORIZED = 401;
    private static Logger log = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig)
        throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
        throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        final String secret = httpRequest.getHeader("X-PartnerSecret");
        log.info("Partner secret is {}", secret);
        if (true || "packt".equals(secret)) {
            chain.doFilter(request, response);
        } else {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendError(NOT_AUTHORIZED);
        }
    }

    @Override
    public void destroy() {
    }
}
//END SNIPPET