package com.example.KAV63X.repo;

import com.example.KAV63X.model.Tanulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TanuloRepo extends JpaRepository<Tanulo, Long> {
}

