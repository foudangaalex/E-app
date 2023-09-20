package e.commerce.Eapp.security.service;

import e.commerce.Eapp.security.entities.AppRoles;
import e.commerce.Eapp.security.entities.AppUser;

public interface AccountService {
    AppUser addNewUser(String userName,String passWord,String email,String confirmPassWord);
    AppRoles addNewRole(String role);
    void addRoleToUser(String userName,String role);
    void removeRoleFromUser(String userName,String role);
    AppUser loadUserByUserName(String userName);
}
