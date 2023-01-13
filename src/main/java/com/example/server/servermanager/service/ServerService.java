package com.example.server.servermanager.service;

import java.io.IOException;
import java.util.Collection;

import com.example.server.servermanager.model.Server;

public interface ServerService {

    Server create(Server server);

    Server ping(String ipAddress) throws IOException;

    Collection<Server> list(int limit);

    Server get(Long id);

    Server update(Server server);

    boolean delete(Long id);

}
