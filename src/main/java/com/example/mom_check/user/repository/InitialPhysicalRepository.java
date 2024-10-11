package com.example.mom_check.user.repository;

import com.example.mom_check.user.domain.InitialPhysical;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InitialPhysicalRepository extends JpaRepository<InitialPhysical, UUID> {
}
