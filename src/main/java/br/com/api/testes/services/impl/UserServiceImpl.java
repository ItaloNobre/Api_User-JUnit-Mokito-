package br.com.api.testes.services.impl;

import br.com.api.testes.domain.User;
import br.com.api.testes.repositories.UserRepository;
import br.com.api.testes.services.UserService;
import br.com.api.testes.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Override
    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
       return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
    }
}
