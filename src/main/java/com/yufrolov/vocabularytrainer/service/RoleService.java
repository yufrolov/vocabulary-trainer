package com.yufrolov.vocabularytrainer.service;

import com.yufrolov.vocabularytrainer.entity.Role;
import com.yufrolov.vocabularytrainer.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }
}
