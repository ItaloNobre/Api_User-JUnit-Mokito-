package br.com.api.testes.services.impl;

import br.com.api.testes.domain.User;
import br.com.api.testes.domain.dto.UserDTO;
import br.com.api.testes.repositories.UserRepository;
import br.com.api.testes.services.UserService;
import br.com.api.testes.services.exceptions.DataIntegrityViolationException;
import br.com.api.testes.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
       return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User create(UserDTO obj) {
        findByEmail(obj);
        return repository.save(modelMapper.map(obj, User.class));
    }

    @Override
    public User update(UserDTO obj) {
        findByEmail(obj);
        return repository.save(modelMapper.map(obj,User.class));

    }

    @Override
    public void delete(Integer id) {
        repository.delete(findById(id));
    }

    private void findByEmail(UserDTO obj){
        Optional<User> user = repository.findByEmail(obj.getEmail());
        if(user.isPresent() && !user.get().getId().equals(obj.getId())){
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema");
        }
    }
}
