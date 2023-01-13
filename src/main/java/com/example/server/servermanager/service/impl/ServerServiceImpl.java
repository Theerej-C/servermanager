package com.example.server.servermanager.service.impl;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.server.servermanager.enumeration.Status;
import com.example.server.servermanager.model.Server;
import com.example.server.servermanager.repository.ServerRepo;
import com.example.server.servermanager.service.ServerService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerService {

    private final ServerRepo serverrepo;

    @Override
    public Server create(Server server) {
        log.info("Saving New Server: {}" + server.getName());
        return serverrepo.save(server);
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching every server");
        return serverrepo.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching server by id : " + id);
        return serverrepo.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Updating Server: " + server.getName());
        return serverrepo.save(server);
    }

    @Override
    public boolean delete(Long id) {
        log.info("deleting Server: " + id);
        serverrepo.deleteById(id);
        return true;
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Piniging server ip+ " + ipAddress);
        Server server = serverrepo.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(1000) ? Status.SERVER_UP : Status.SERVER_DOWN);
        serverrepo.save(server);
        return server;
    }
}
