package com.jb.CouponSystemSpringShonRassan.repos;

import com.jb.CouponSystemSpringShonRassan.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    boolean existsByName(String name);
    boolean existsByEmail(String email);
    boolean existsByPassword(String password);
    boolean existsByEmailAndPassword(String email, String password);
}
