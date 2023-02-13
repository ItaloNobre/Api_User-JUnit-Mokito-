package br.com.api.testes.services;

import br.com.api.testes.domain.User;
import br.com.api.testes.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    User findById(Integer id);
    List<User> findAll ();
    User create (UserDTO obj);
    User update(UserDTO obj);
    void delete(Integer id);
}
