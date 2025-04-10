package com.ead.course.services.impl;

import com.ead.course.repositories.ModuleRespository;
import com.ead.course.services.ModuleService;
import org.springframework.stereotype.Service;

@Service
public class ModuleServiceImpl implements ModuleService {

  private final ModuleRespository moduleRespository;

  private ModuleServiceImpl(ModuleRespository moduleRespository) {
    this.moduleRespository = moduleRespository;
  }
}
