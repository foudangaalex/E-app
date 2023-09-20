package e.commerce.Eapp.security.service;

import e.commerce.Eapp.security.repo.AppRoleRepository;
import e.commerce.Eapp.security.entities.AppRoles;
import e.commerce.Eapp.security.entities.AppUser;
import e.commerce.Eapp.security.repo.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private AppUserRepository appUserRepository;
    private PasswordEncoder passwordEncoder;
    private AppRoleRepository appRoleRepository;
    @Override
    public AppUser addNewUser(String userName, String passWord, String email, String confirmPassWord) {
       AppUser user= appUserRepository.findByUsername(userName);
       if(user!=null) throw new RuntimeException("this user already exist");
       if(!passWord.equals(confirmPassWord)) throw new RuntimeException("password not match");
       AppUser appUser=new AppUser();
       appUser.setUserId(UUID.randomUUID().toString());
       appUser.setUsername(userName);
       appUser.setEmail(email);
       appUser.setPassword(passwordEncoder.encode(passWord));
        return appUserRepository.save(appUser);
    }

    @Override
    public AppRoles addNewRole(String role) {
       AppRoles appRoles=appRoleRepository.findById(role).orElse(null);
       if(appRoles!=null) throw  new RuntimeException("this role already exist");
       AppRoles appRoles1=new AppRoles();
       appRoles1.setRole(role);
        return appRoleRepository.save(appRoles1);
    }

    @Override
    public void addRoleToUser(String userName, String role) {
        AppUser user= appUserRepository.findByUsername(userName);
        AppRoles appRoles=appRoleRepository.findById(role).get();
        user.getRoles().add(appRoles);

    }

    @Override
    public void removeRoleFromUser(String userName, String role) {
        AppUser user= appUserRepository.findByUsername(userName);
        AppRoles appRoles=appRoleRepository.findById(role).get();
        user.getRoles().remove(appRoles);
    }

    @Override
    public AppUser loadUserByUserName(String userName) {
        return appUserRepository.findByUsername(userName);
    }
}
