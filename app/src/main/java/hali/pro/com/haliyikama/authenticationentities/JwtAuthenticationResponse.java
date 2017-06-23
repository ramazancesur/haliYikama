package hali.pro.com.haliyikama.authenticationentities;

import java.io.Serializable;

/**
 * Created by ramazancesur on 23/06/2017.
 */

public class JwtAuthenticationResponse implements Serializable{

    private String token;
    private String userName;

    public JwtAuthenticationResponse(String token, String userName) {
        this.token = token;
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}