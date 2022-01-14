package com.kevinnt.kraepelinmobile.models;

import com.huawei.hms.support.account.result.AuthAccount;
import com.huawei.hms.support.account.service.AccountAuthService;
import com.huawei.hms.support.feature.service.AuthService;

public class User {
    private static User instance = null;
    private AuthAccount authAccount = null;
    private AccountAuthService authService = null;

    private User(){

    }

    public static User getInstance(){
        if(instance == null){
            instance = new User();
        }
        return instance;
    }

    public AuthAccount getAuthAccount() {
        return authAccount;
    }

    public void setAuthAccount(AuthAccount authAccount) {
        this.authAccount = authAccount;
    }

    public AccountAuthService getAuthService() {
        return authService;
    }

    public void setAuthService(AccountAuthService authService) {
        this.authService = authService;
    }
}
