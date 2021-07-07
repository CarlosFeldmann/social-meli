package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.controllers;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.dtos.UserDTO;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms.UserForm;
import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/users")
public class UsersController {

    private UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO createCustomer(@RequestBody @Valid UserForm form) {
        return usersService.createCustomer(form);
    }
    @PostMapping("/seller")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO createSeller(@RequestBody @Valid UserForm form) {
        return usersService.createSeller(form);
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUserInfo(@PathVariable @Min(0) Integer id) {
        return usersService.getUserInfo(id);
    }

}
