package frontend.bd.carrental.rim.carrentalfrontend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import frontend.bd.carrental.rim.carrentalfrontend.model.Customer;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@Service
public class CustomerService {
    private ObjectMapper objectMapper;

    public CustomerService() {
        this.objectMapper = objectMapper;
    }

    private HttpHeaders createHeaders(String username, String password){
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
        }};
    }


    public Customer customerRegistration(Customer customer) throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Customer> customerHttpEntity = new HttpEntity<>(customer);
        ResponseEntity<Customer> customerResponseEntity = restTemplate.exchange(
                "http://localhost:8083/customer/registration",
                HttpMethod.POST,
                customerHttpEntity,
                Customer.class);
        if (customerResponseEntity.getStatusCode() == HttpStatus.CREATED){
            return customerResponseEntity.getBody();
        }
        else{
            throw new Exception(customerHttpEntity.toString());
        }
    }

}
