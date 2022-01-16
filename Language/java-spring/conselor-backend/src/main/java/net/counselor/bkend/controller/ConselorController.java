package net.counselor.bkend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.counselor.bkend.repo.CounselorRepository;
import net.counselor.bkend.model.Counselor;

@RestController
@RequestMapping("/api/v1/")
public class ConselorController {

	@Autowired
	private CounselorRepository counselorRepo;
	
	@GetMapping("/counselors")
	public List<Counselor> getAllCounselors() {
		return counselorRepo.findAll();
	}
}