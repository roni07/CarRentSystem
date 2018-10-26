package backend.bd.carrental.rim.carrentalbackend.controller;

import backend.bd.carrental.rim.carrentalbackend.model.Role;
import backend.bd.carrental.rim.carrentalbackend.repository.RoleRepository;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "role")
@RestController
public class RoleController {
    private RoleRepository roleRepository;


    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostMapping(value = "save")
    public Role createRole(@RequestBody Role role){
        return roleRepository.save(role);
    }

    @GetMapping(value = "all")
    public Iterable<Role> getRole(){
        return roleRepository.findAll();
    }
}
