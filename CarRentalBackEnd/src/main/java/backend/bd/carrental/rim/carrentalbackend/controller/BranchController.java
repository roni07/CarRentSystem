package backend.bd.carrental.rim.carrentalbackend.controller;

import backend.bd.carrental.rim.carrentalbackend.model.Branch;
import backend.bd.carrental.rim.carrentalbackend.service.BranchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping(value = "branch")
@RestController
public class BranchController {
    private BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping(value = "save")
    public ResponseEntity<Branch> saveBranch(@RequestBody Branch branch){
        try{
            Branch createdBranch = branchService.createBranch(branch);
            ResponseEntity<Branch> branchResponseEntity = ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(createdBranch);
            return branchResponseEntity;
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping(value = "all")
    public Iterable<Branch> getBranches(){
        return branchService.getAllBranch();
    }

    @DeleteMapping(value = "delete/{branchId}")
    public ResponseEntity deleteBranchById(@PathVariable int branchId){
        Optional<Branch> optionalBranch = branchService.getBranchById(branchId);
        if(optionalBranch.isPresent()){
            branchService.deleteBranch(branchId);
            return ResponseEntity.ok().body("Deleted!");
        }
        else{
            return ResponseEntity.badRequest().body(null);
        }
    }
}
