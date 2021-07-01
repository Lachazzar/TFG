package es.udc.tfgproject.backend.rest.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.udc.tfgproject.backend.model.entities.UserBD;
import es.udc.tfgproject.backend.model.services.ListService;
import es.udc.tfgproject.backend.model.services.SecService;
import es.udc.tfgproject.backend.rest.dtos.UserDto;

@Transactional
@ControllerAdvice
@RequestMapping("/administracion/usuarios")
public class userController {

    @Autowired
    private ListService listService;

    @Autowired
    private SecService secService;

    @GetMapping("")
    public String users(Model model) {

	boolean security = secService.checkSecurity("ROLE_ADMIN");

	if (!security) {
	    return "forbidden";
	}

	ArrayList<UserDto> usersList = listService.listAllUsersDto();

	model.addAttribute("users", usersList);

	return "admin/users";
    }

    @GetMapping("/eliminar/{userName}")
    public String deleteUser(@PathVariable("userName") String userName, Model model) {

	boolean security = secService.checkSecurity("ROLE_ADMIN");

	if (!security) {
	    return "forbidden";
	}

	ArrayList<UserDto> usersList = listService.listAllUsersDto();

	listService.deleteUserByUserName(usersList, userName);

	usersList.clear();

	usersList = listService.listAllUsersDto();

	model.addAttribute("users", usersList);

	return "admin/users";
    }

    @GetMapping("/editar/{userName}")
    public String editUser(@PathVariable("userName") String userName, Model model) {

	boolean security = secService.checkSecurity("ROLE_ADMIN");

	if (!security) {
	    return "forbidden";
	}

	UserDto user = listService.getUserDtoByUserName(userName);

	if (user.getUserName() == null || user.getUserName() == "") {
	    model.addAttribute("title", "Nuevo Usuario");
	    model.addAttribute("err", "No se ha encontrado el usuario, si guarda se insertará una nuevo");
	    model.addAttribute("user", user);
	    return "admin/newUser";
	} else {
	    model.addAttribute("title", "Detalle Usuario");
	    model.addAttribute("user", user);
	    model.addAttribute("oldUserName", user.getUserName());
	}

	List<UserBD.RoleType> roles = Arrays.asList(UserBD.RoleType.values());

	model.addAttribute("roles", roles);

	return "admin/userDetails";
    }

    @GetMapping("/nuevo")
    public String newUser(Model model) {

	boolean security = secService.checkSecurity("ROLE_ADMIN");

	if (!security) {
	    return "forbidden";
	}

	model.addAttribute("title", "Nuevo Usuario");

	UserDto user = new UserDto();
	model.addAttribute("user", user);

	List<UserBD.RoleType> roles = Arrays.asList(UserBD.RoleType.values());

	model.addAttribute("roles", roles);

	return "admin/newUser";
    }

    @PostMapping("/guardar")
    public String saveUser(@ModelAttribute("user") UserDto userDto,
	    @RequestParam(value = "oldUserName", required = false) String oldUserName, Model model) {

	boolean security = secService.checkSecurity("ROLE_ADMIN");

	if (!security) {
	    return "forbidden";
	}

	Boolean hasError = listService.checkAndSaveUser(oldUserName, userDto.getUserName(), userDto.getPassword(),
		userDto.getEmail(), userDto.getRole());

	if (hasError) {
	    model.addAttribute("err", "Ya existe un usuario con ese nombre");

	    List<UserBD.RoleType> roles = Arrays.asList(UserBD.RoleType.values());
	    model.addAttribute("roles", roles);

	    if (oldUserName == "") {
		model.addAttribute("title", "Nuevo Usuario");
		return "admin/newUser";
	    } else {
		model.addAttribute("title", "Detalle Usuario");
		model.addAttribute("user", userDto);
		model.addAttribute("oldUserName", oldUserName);
		return "admin/userDetails";
	    }
	}

	ArrayList<UserDto> usersList = listService.listAllUsersDto();

	model.addAttribute("users", usersList);

	return "admin/users";
    }

}