package uz.pdp.lesson6_7role_and_permission.secure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.lesson6_7role_and_permission.service.AuthService;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {  //IT COMES HERE BEFORE CONTROLLER

    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    AuthService myAuthService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,

                                    FilterChain filterChain) throws ServletException, IOException {
        //GETTING TOKEN FROM REQUEST
        String token = request.getHeader("Authorization");//put in header in postman with bearer

        //CHECHKING IS TOKEN EXIST AND IS IT STARTS WITH BEARER
        if(token != null && token.startsWith("Bearer")){  //as in postman

            //GET ONLY TOKEN
            token=token.substring(7); //we delete letters which goes to bearer

            //CHECING TOKEN THROW VALIDATION EX: NOT DESTROYED, TIMELIMIT
            boolean validateToken = jwtProvider.validateToken(token);

            if(validateToken){
                //GETTING USERNAME FROM TOKEN
                String userNameFromToken = jwtProvider.getUserNameFromToken(token);

                //GETTING USER-DETAILS FROM USERNAME
                UserDetails userDetails = myAuthService.loadUserByUsername(userNameFromToken);

                //CREATING AUTHENTICATION THROUGH USER-DETAILS
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails,
                                null,
                                userDetails.getAuthorities());
                System.out.println(SecurityContextHolder.getContext().getAuthentication());

                //CHECKING WHO ENTERING TO SYSTEM
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            }

        }

        filterChain.doFilter(request, response);
    }

}
