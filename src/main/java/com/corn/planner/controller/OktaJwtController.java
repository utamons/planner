package com.corn.planner.controller;

import com.corn.planner.dto.OktaRequest;
import com.kkr.okta.jwt.OktaTokenHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@SuppressWarnings("unused")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class OktaJwtController {
	private final Logger log = LoggerFactory.getLogger(OktaJwtController.class);

	private String lastToken;

	private long   lastGet = 0;
	private String lastUser;
	private String lastEnv;

	@PostMapping("/oktajwt")
	public ResponseEntity<String> oktaJwt(@RequestBody OktaRequest oktaRequest) {
		String env  = oktaRequest.getEnv();
		String user = oktaRequest.getUser();
		log.info("Environment - {}", env);
		log.info("User - {}", user);
		String accessToken;
		try {
			long moment = System.currentTimeMillis();
			if (moment - lastGet < 4.5 * 60000 && env.equals(lastEnv) && user.equals(lastUser))
				accessToken = lastToken;
			else {
				OktaTokenHelper oktaTokenHelper = new OktaTokenHelper(env, user);
				accessToken = oktaTokenHelper.getAccessToken();
				lastEnv = env;
				lastUser = user;
				lastToken = accessToken;
				lastGet = System.currentTimeMillis();
			}
			return ResponseEntity.ok().body(accessToken);
		} catch (IOException | InterruptedException e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
}
