package br.com.api.testes.services;

import br.com.api.testes.domain.User;

public interface UserService {

    User findById(Integer id);
}
