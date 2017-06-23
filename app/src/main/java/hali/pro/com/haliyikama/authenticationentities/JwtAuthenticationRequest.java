package hali.pro.com.haliyikama.authenticationentities;

import java.io.Serializable;

/**
 * Created by ramazancesur on 23/06/2017.
 */

public class JwtAuthenticationRequest implements Serializable {
    private String username;
    private String password;

    public JwtAuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public JwtAuthenticationRequest() {
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}