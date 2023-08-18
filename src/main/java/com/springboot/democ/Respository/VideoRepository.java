package com.springboot.democ.Respository;


import com.springboot.democ.Entity.Videokeydata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Videokeydata, Long> {

    Videokeydata findByvideokey(String videokey);
}
