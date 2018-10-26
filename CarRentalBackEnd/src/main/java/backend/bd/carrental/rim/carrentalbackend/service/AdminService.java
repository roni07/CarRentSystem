package backend.bd.carrental.rim.carrentalbackend.service;

import backend.bd.carrental.rim.carrentalbackend.model.Admin;
import backend.bd.carrental.rim.carrentalbackend.model.Role;
import backend.bd.carrental.rim.carrentalbackend.repository.AdminRepository;
import backend.bd.carrental.rim.carrentalbackend.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AdminService {

    private AdminRepository adminRepository;

    private RoleRepository roleRepository;

    public AdminService(AdminRepository adminRepository, RoleRepository roleRepository) {
        this.adminRepository = adminRepository;
        this.roleRepository = roleRepository;
    }

    public Admin saveAdmin(Admin admin){
        Role role =  roleRepository.findByRole("ADMIN");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        admin.setRoles(roles);
       return adminRepository.save(admin);
    }

    public Optional<Admin> getAdmin(String userName){
        return adminRepository.findById(userName);
    }
}
