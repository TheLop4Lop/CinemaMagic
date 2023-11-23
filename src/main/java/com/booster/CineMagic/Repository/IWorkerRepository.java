package com.booster.CineMagic.Repository;

import com.booster.CineMagic.Entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWorkerRepository extends JpaRepository<Worker, Integer> {
}
