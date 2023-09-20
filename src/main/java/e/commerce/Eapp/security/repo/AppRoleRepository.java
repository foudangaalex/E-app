package e.commerce.Eapp.security.repo;

import e.commerce.Eapp.security.entities.AppRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRoles,String> {
}
