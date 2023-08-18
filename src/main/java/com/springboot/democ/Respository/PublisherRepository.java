package com.springboot.democ.Respository;


import com.springboot.democ.Entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    // 이름으로 언론사 조회 메서드 선언

    Publisher findByName(String PublisherName);
}