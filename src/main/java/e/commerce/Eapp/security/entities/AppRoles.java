package e.commerce.Eapp.security.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class AppRoles {
    @Id
    private String role;
}
