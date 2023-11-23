package com.booster.CineMagic.Repository;

import com.booster.CineMagic.Entity.Projection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProjectionRepository extends JpaRepository<Projection, Integer> {
}
