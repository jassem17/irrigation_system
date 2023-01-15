package com.mycompany.smart_irrigation.security.Oauth2;

import com.mycompany.smart_irrigation.FieldPropertyVisibilityStrategy;
import jakarta.ws.rs.FormParam;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbVisibility;
import javax.validation.constraints.NotBlank;

@JsonbVisibility(FieldPropertyVisibilityStrategy.class)
public class Oauth2Request {
    @JsonbProperty("grand_type")
    @NotBlank
    private String grandType;

    @JsonbProperty("username")
    @NotBlank(groups = {GenerateToken.class})
    private String username;

    @JsonbProperty("password")
    @NotBlank(groups = {GenerateToken.class})
    private String password;

    @JsonbProperty("refresh_token")
    @NotBlank(groups = {RefreshToken.class})
    private String refreshToken;

    public void setGrandType(GrantType grandType) {
        if(grandType != null) {
            this.grandType = grandType.get();
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public GrantType getGrandType() {
        if(grandType != null) {
            return GrantType.parse(grandType);
        }
        return null;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRefreshToken() {
        return refreshToken;
    }


    public @interface  GenerateToken{}

    public @interface  RefreshToken{}
}
