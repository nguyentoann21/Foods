package com.example.foods.models;

public class Users {
    private int userId;
    private String username, fullName, password, confirmPassword, address, email, phone, role;

    public Users(int userId, String username, String fullName, String password, String confirmPassword, String address, String email, String phone, String role) {
        this.userId = userId;
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return  "ID: " + userId + "\n"+
                "Username: " + username + "\n" +
                "Full-name: " + fullName + "\n" +
                "Password: " + password + "\n" +
                "Re-password: " + confirmPassword + "\n" +
                "Address: " + address + "\n" +
                "Email: " + email + "\n"+
                "Phone: " + phone + "\n" +
                "Role: " + role + "\n";
    }
}
