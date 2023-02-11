package br.com.api.testes.services.impl;

import br.com.api.testes.domain.User;
import br.com.api.testes.domain.dto.UserDTO;
import br.com.api.testes.repositories.UserRepository;
import br.com.api.testes.services.exceptions.DataIntegrityViolationException;
import br.com.api.testes.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "italo";
    public static final String EMAIL = "email@gmail";
    public static final String PASSWORD = "123";
    public static final int INDEX = 0;
    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;


    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(optionalUser);
        User response = service.findById(ID);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());

    }

    @Test
    void whenFindByIdThenReturnObjectNotFoundException(){
        Mockito.when(repository.findById(Mockito.anyInt())).
                thenThrow(new ObjectNotFoundException("Objeto não encontrado!"));
        try {
            service.findById(ID);
        }catch (Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Objeto não encontrado!", ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAListOfUsers() {
        Mockito.when(repository.findAll()).thenReturn(List.of(user));

        List<User> response = service.findAll();
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(User.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        Mockito.when(repository.save(Mockito.any())).thenReturn(user);

        User response = service.create(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());

    }

    @Test
    void whenCreateThenReturnADataIntegrityViolationException() {
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(optionalUser);

        try {
            service.create(userDTO);
        } catch (Exception ex){
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals("E-mail já cadastrado no sistema", ex.getMessage());
        }

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}