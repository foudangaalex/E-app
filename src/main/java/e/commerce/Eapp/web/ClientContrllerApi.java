package e.commerce.Eapp.web;


import e.commerce.Eapp.entity.Client;
import e.commerce.Eapp.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ClientContrllerApi {
    private ClientRepository clientRepository;
    @GetMapping("/admin/clients")
    public List<Client> findAll(){
        return clientRepository.findAll();
    }
}
