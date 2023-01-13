package com.example.server.servermanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.server.servermanager.model.Server;

public interface ServerRepo extends JpaRepository<Server, Long> {
    Server findByIpAddress(String ipAddress);

}
