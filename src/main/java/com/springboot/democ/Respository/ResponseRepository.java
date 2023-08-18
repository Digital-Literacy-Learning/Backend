package com.springboot.democ.Respository;

import com.springboot.democ.Entity.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {


    List<Response> findByuserId(String userId);

    List<Response> findByvideokey(String videokey);

    Response findByUserIdAndVideokey(String userId ,String videokey);


    Page<Response> findAllByUserId(String userId, Pageable pageable);


}
