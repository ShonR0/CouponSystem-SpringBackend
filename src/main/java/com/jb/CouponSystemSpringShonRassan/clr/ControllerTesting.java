package com.jb.CouponSystemSpringShonRassan.clr;

import com.jb.CouponSystemSpringShonRassan.beans.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
@Order(2)
public class ControllerTesting implements CommandLineRunner {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${url}")
    private String url;

    @Override
    public void run(String... args) throws Exception {
//        System.out.println("ADMIN:");
//        System.out.println("Add Company");
//        Company company = Company.builder()
//                .name("company1")
//                .email("company@gmail.com")
//                .password("1234")
//                .build();
//        ResponseEntity<String> res = restTemplate.postForEntity(url, company, String.class);
//        System.out.println((res.getStatusCodeValue() == 201) ? "OK" : "NOT OK");

//        System.out.println("Get Single Company");
//        Company fromDb = restTemplate.getForObject(url + "admin/companies/1", Company.class);
//        System.out.println(fromDb);
//        System.out.println((res.getStatusCodeValue() == 201) ? "OK" : "NOT OK");
//
//        System.out.println("Get All Companies");
//        List<Company> companies = Collections.singletonList(restTemplate.getForObject(url + "admin/companies", Company.class));
//        System.out.println((res.getStatusCodeValue() == 201) ? "OK" : "NOT OK");
    }
}
