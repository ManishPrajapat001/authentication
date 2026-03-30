package org.manish.authentication.dto;

public class AuthResponse {
    private String token;
    private UserResponseDTO userResponseDTO;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserResponseDTO getUserResponseDTO() {
        return userResponseDTO;
    }

    public void setUserResponseDTO(UserResponseDTO userResponseDTO) {
        this.userResponseDTO = userResponseDTO;
    }

    public AuthResponse(String token,UserResponseDTO userResponseDTO) {
        this.token = token;
        this.userResponseDTO = userResponseDTO;
    }
}
