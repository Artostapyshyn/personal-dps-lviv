package com.artostapyshyn.personaldpslviv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InfoPagesController {

	@GetMapping("/info-pages/civil_service")
	public String getCivilServicePage() {
		return "info-pages/civil_service";
	}
	
	@GetMapping("/info-pages/certification_training")
	public String getCertificationPage() {
		return "info-pages/certification_training";
	}
	
	@GetMapping("/info-pages/vacation")
	public String getVacationPage() {
		return "info-pages/vacation";
	}

	@GetMapping("/info-pages/key_indicators_and_evaluation")
	public String getKeyIndicatorssPage() {
		return "/info-pages/key_indicators_and_evaluation";
	}
}
