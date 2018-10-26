package frontend.bd.carrental.rim.carrentalfrontend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import frontend.bd.carrental.rim.carrentalfrontend.model.Branch;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@Service
public class BranchService {

    private ObjectMapper objectMapper = new ObjectMapper();

    private RestTemplate restTemplate = new RestTemplate();

    public List<Branch> getBranch() {
        String message = "";
        Branch[] branches = null;
        try {
            URL url = new URL("http://localhost:8083/branch/all");
            branches = objectMapper.readValue(url, Branch[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Branch> branchList = Arrays.asList(branches);
        return branchList;
    }

    public List<Branch> getAllBranch(){
        ResponseEntity<List<Branch>> branchResponseEntity = restTemplate.exchange(
                "http://localhost:8083/branch/all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Branch>>() {}
        );

        return branchResponseEntity.getBody();
    }
}
