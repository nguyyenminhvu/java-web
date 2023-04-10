/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.modelFeature;

/**
 *
 * @author ACER
 */
public class AccountGoogleError {

    private String passwordError;
    private String rePasswordError;

    public AccountGoogleError() {
    }

    public AccountGoogleError(String passwordError, String rePasswordError) {
        this.passwordError = passwordError;
        this.rePasswordError = rePasswordError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getRePasswordError() {
        return rePasswordError;
    }

    public void setRePasswordError(String rePasswordError) {
        this.rePasswordError = rePasswordError;
    }

}
