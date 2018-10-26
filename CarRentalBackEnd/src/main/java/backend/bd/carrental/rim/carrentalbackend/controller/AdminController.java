package backend.bd.carrental.rim.carrentalbackend.controller;

import backend.bd.carrental.rim.carrentalbackend.model.Admin;
import backend.bd.carrental.rim.carrentalbackend.service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping(value = "admin")
@RestController
public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping(value = "save")
    public Admin saveAdmin(@RequestBody Admin admin){
        return adminService.saveAdmin(admin);
    }

    @GetMapping(value = "findAdmin{userName}")
    public Optional<Admin> getAdmin(@PathVariable String userName){
        return adminService.getAdmin(userName);
    }
}
