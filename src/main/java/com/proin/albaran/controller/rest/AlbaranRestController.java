//package com.proin.albaran.controller.rest;
//
//import java.util.Date;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.proin.albaran.dto.AlbaranDto;
//import com.proin.albaran.service.AlbaranService;
//
//
///**
// * The Class TasacionesRestController.
// */
//@RestController
//
//public class AlbaranRestController {
//	
//	private final AlbaranService albaranService;
//	
//	public AlbaranRestController (final AlbaranService albaranService) {
//		this.albaranService = albaranService;
//	}
//	
////	@PostMapping(value = "/api/v1/albaranes", produces = MediaType.APPLICATION_JSON_VALUE)
////	@ResponseStatus(HttpStatus.CREATED)
////	public AlbaranDto generarAlbaran(@Validated @RequestBody AlbaranDto t, HttpServletRequest request) {
////		return albaranService.generarAlbaran(new Date());
////	}
////
////	@GetMapping(value = "/api/v1/albaranes/{idAlbaran}", produces = MediaType.APPLICATION_JSON_VALUE)
////	@ResponseStatus(HttpStatus.OK)
////	public AlbaranDto obtenerAlbaran(@PathVariable Integer idAlbaran) {
////		return albaranService.obtenerDatosAlbaran(idAlbaran);
////	}
//	
//	
//
////	@PutMapping(value = "/api/v1/albaranes/{idAlbaran}", produces = MediaType.APPLICATION_JSON_VALUE)
////	@ResponseStatus(HttpStatus.CREATED)
////	public AlbaranDto actualizarAlbaran(@PathVariable Integer idTasacion, @RequestBody Entrada accion) {
////		return albaranService.generarAlbaran(new Date());
////	}
//}