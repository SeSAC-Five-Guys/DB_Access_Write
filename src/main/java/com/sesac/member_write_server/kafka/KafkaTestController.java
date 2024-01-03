/*
package com.sesac.member_write_server.kafka;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/write")
public class KafkaTestController {
	private final KafkaTestProducerServiceImpl producerService;

	@GetMapping(value = "/test/{message}")
	public String sendTest(@Valid  @PathVariable String message){
		producerService.sendTest(message);
		return "Success send Msg: " + message;
	}
}
*/
