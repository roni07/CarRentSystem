package backend.bd.carrental.rim.carrentalbackend.configuration;

import backend.bd.carrental.rim.carrentalbackend.repository.CustomerRepository;
import backend.bd.carrental.rim.carrentalbackend.service.CustomerRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableJpaRepositories(basePackageClasses = CustomerRepository.class)
public class CustomerRegistrationConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomerRegistrationService customerRegistrationService;

    public CustomerRegistrationConfiguration(CustomerRegistrationService customerRegistrationService) {
            this.customerRegistrationService = customerRegistrationService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerRegistrationService)
                .passwordEncoder(getPasswordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception{
                http.csrf().disable();
                http.authorizeRequests()
                        .antMatchers("**/userName/**").permitAll()
                        .and()
                        .httpBasic();
    }

    private PasswordEncoder getPasswordEncoder(){
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return true;
            }
        };
    }
}
