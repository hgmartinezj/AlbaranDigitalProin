//package com.proin.albaran.service.impl;
//
//import java.util.List;
//
//import org.springframework.stereotype.Service;
//
//import com.proin.albaran.entity.transporte.TAlbaran;
//import com.proin.albaran.repository.AlbaranRepository;
//import com.proin.albaran.service.AlbaranService;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Service
//@Slf4j
//public class AlbaranServiceImpl implements AlbaranService{
//	
//	private final AlbaranRepository albaranRepository;
//	
//	public AlbaranServiceImpl(final AlbaranRepository albaranRepository) {
//		this.albaranRepository = albaranRepository;
//	}
//	
//	public List<TAlbaran> findAll() {
//		return albaranRepository.findAll();
//	}
//
//	@Override
//	public TAlbaran save(TAlbaran albaran) {
//		return albaranRepository.save(albaran);
//	}
//	
//}