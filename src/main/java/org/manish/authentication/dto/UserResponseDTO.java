package org.manish.authentication.dto;

public class UserResponseDTO {
    private Boolean isEnabled;
    private Long id;
    private String username;
    private String role ;

    public UserResponseDTO(Long id, String username, String role, Boolean isEnabled) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.isEnabled = isEnabled;
    }
    public UserResponseDTO(){

    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public Boolean getIsEnabled() {
        return isEnabled;
    }
    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
}
