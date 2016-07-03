package com.voksel.electric.pc.service;

import org.springframework.stereotype.Service;

@Service
public class TestService {

	public String getName() {

		return "date " + System.nanoTime();
	}
}
