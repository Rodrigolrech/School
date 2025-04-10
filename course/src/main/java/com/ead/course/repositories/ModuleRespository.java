package com.ead.course.repositories;

import com.ead.course.models.ModuleModel;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRespository extends JpaRepository<ModuleModel, UUID> {}
