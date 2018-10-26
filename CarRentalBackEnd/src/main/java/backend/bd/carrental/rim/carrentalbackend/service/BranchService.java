package backend.bd.carrental.rim.carrentalbackend.service;

import backend.bd.carrental.rim.carrentalbackend.exception.ResourceAlreadyExistException;
import backend.bd.carrental.rim.carrentalbackend.exception.ResourceDoesnotExistException;
import backend.bd.carrental.rim.carrentalbackend.model.Branch;
import backend.bd.carrental.rim.carrentalbackend.repository.BranchRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BranchService {
    private BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public Branch createBranch(Branch branch){
        Optional<Branch> optionalBranch = branchRepository.findById(branch.getBranchId());
        if(optionalBranch.isPresent()){
            throw new ResourceAlreadyExistException("branch is exist with id = " +branch.getBranchId());
        }
        else {
            return branchRepository.save(branch);
        }
    }

    public Iterable<Branch> getAllBranch() {
        return branchRepository.findAll();
    }


    public Optional<Branch> getBranchById(int id){
        Optional<Branch> optionalBranch = branchRepository.findById(id);
        if (optionalBranch.isPresent()){
            return branchRepository.findById(id);
        }
        else {
            throw new ResourceDoesnotExistException("branch does not exist");
        }
    }


    //for saving car also using this service
    public Branch getBranch(int id){
        return branchRepository.findById(id).get();
    }

    //for saving car using this service
    public Branch createCarUsingBranch(int id, Branch branch){
        Optional<Branch> optionalBranch = branchRepository.findById(id);
        if(optionalBranch.isPresent()){
            return branchRepository.save(branch);
        }
        else{
            throw new ResourceDoesnotExistException("branch is not exist with id = "
                    +branch.getBranchId());
        }
    }

    public void deleteBranch(int id){
        branchRepository.deleteById(id);
    }
}
