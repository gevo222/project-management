package com.gevo.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gevo.pma.dao.IUserAccountRepository;
import com.gevo.pma.entities.UserAccount;

@Controller
@RequestMapping("/register")
public class SecurityController {

	@Autowired
	IUserAccountRepository userAccountRepo;

	@Autowired
	BCryptPasswordEncoder bCrytpoEncoder;

	@GetMapping
	public String createUser(Model model) {
		UserAccount userAccount = new UserAccount();
		model.addAttribute("userAccount", userAccount);
		return "security/register";
	}

	@PostMapping("/save")
	public String saveUser(Model model, UserAccount userAccount) {
		userAccount.setPassword(bCrytpoEncoder.encode(userAccount.getPassword()));
		userAccountRepo.save(userAccount);
		return "main/home.html";
	}
}
