package uz.pdp.lesson6_7role_and_permission.secure;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import uz.pdp.lesson6_7role_and_permission.entity.Lavozim;

import java.util.Date;
import java.util.Set;

@Component
public class JwtProvider { //TOKEN GENERATION

  private static final long expireTime = 1_000_000000;  //in millisec == 10000sec
  private static final String keyWord = "ThisIsTokensSecretWord12345";  //if somebody knows this word he can hack our program

    public /* static */ String generateToken(String username, Lavozim lavozims){

        Date expireDate = new Date(System.currentTimeMillis()+expireTime);
        String token = Jwts
                .builder()  //start building token
                .setSubject(username)
                .setIssuedAt(new Date()) //it started now
                .setExpiration(expireDate)  //time limit of how much this token should exist
                .claim("roles", lavozims)
                .signWith(SignatureAlgorithm.HS512, keyWord)  //HS512 ENCODING METHOD, KeyWord is secret word
                .compact(); //it returns this token with string
        return token;

    }

    public String getUserNameFromToken(String token){
try {
    String email = Jwts
            .parser()
            .setSigningKey(keyWord)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    return email;
}catch (Exception e){
    return null;
}
    }

    public boolean validateToken(String token){
        try {
            Jwts
                    .parser()
                    .setSigningKey(keyWord)
                    .parseClaimsJws(token);
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

}
