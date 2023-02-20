package br.com.api.testes.controller;

import br.com.api.testes.domain.User;
import br.com.api.testes.domain.dto.UserDTO;
import br.com.api.testes.services.impl.UserServiceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    public static final String ID = "{id}";
    @Autowired
    private ModelMapper mapper;


    @Autowired
    UserServiceImpl service;

    @GetMapping("/" + ID)
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(service.findById(id), UserDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<User> list = service.findAll();
        List<UserDTO> listDTO = list.stream().map(x -> mapper.map(x,UserDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO obj) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(service.create(obj),UserDTO.class));
    }

    @PutMapping("/" + ID)
    public ResponseEntity<UserDTO> update(@PathVariable Integer id, @RequestBody UserDTO obj){
        obj.setId(id);
        return ResponseEntity.ok().body(mapper.map(service.update(obj), UserDTO.class));
    }

    @DeleteMapping("/" + ID)
    public ResponseEntity<UserDTO> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
