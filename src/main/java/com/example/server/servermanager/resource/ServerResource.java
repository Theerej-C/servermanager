package com.example.server.servermanager.resource;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.server.servermanager.enumeration.Status;
import com.example.server.servermanager.model.Response;
import com.example.server.servermanager.model.Server;
import com.example.server.servermanager.service.impl.ServerServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {

        private final ServerServiceImpl serviceImpl;

        @GetMapping("/list")
        private ResponseEntity<Response> getServers() {

                return ResponseEntity.ok(
                                Response.builder()
                                                .date(LocalDateTime.now())
                                                .data(Map.of("servers", serviceImpl.list(30)))
                                                .status("OK")
                                                .statusCode(HttpStatus.OK.value())
                                                .message("ALL Server Recieved")
                                                .build());
        }

        @GetMapping("/ping/{ipAddress}")
        private ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAdd) throws IOException {

                Server server = serviceImpl.ping(ipAdd);
                return ResponseEntity.ok(
                                Response.builder()
                                                .date(LocalDateTime.now())
                                                .data(Map.of("server", server))
                                                .status("OK")
                                                .statusCode(HttpStatus.OK.value())
                                                .message(server.getStatus() == Status.SERVER_UP ? "Ping success"
                                                                : "ping Fail")
                                                .build());
        }

        @PostMapping("/save")
        private ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) {
                return ResponseEntity.ok(
                                Response.builder()
                                                .date(LocalDateTime.now())
                                                .data(Map.of("server", serviceImpl.create(server)))
                                                .status("CREATED")
                                                .statusCode(HttpStatus.CREATED.value())
                                                .message("Server Created Successfully")
                                                .build());
        }

        @GetMapping("/get/{id}")
        private ResponseEntity<Response> getServer(@PathVariable("id") Long id) throws IOException {

                return ResponseEntity.ok(
                                Response.builder()
                                                .date(LocalDateTime.now())
                                                .data(Map.of("server", serviceImpl.get(id)))
                                                .status("OK")
                                                .statusCode(HttpStatus.OK.value())
                                                .message("Server Retrieved")
                                                .build());
        }

        @DeleteMapping("/delete/{id}")
        private ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) {

                return ResponseEntity.ok(
                                Response.builder()
                                                .date(LocalDateTime.now())
                                                .data(Map.of("server", serviceImpl.delete(id)))
                                                .status("OK")
                                                .statusCode(HttpStatus.OK.value())
                                                .message("Server Deleted")
                                                .build());
        }
}
