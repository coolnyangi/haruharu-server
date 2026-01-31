package com.haruharu.haruharu.routine.repository;

import com.haruharu.haruharu.routine.entity.Routine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutineRepository extends JpaRepository<Routine, Long> {
}
