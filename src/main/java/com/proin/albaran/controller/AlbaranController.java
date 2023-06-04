package com.proin.albaran.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.proin.albaran.dto.AlbaranDto;
import com.proin.albaran.dto.CamionDto;
import com.proin.albaran.dto.ClienteDto;
import com.proin.albaran.dto.MeteorologiaDto;
import com.proin.albaran.dto.RemolqueDto;
import com.proin.albaran.dto.TransporteDto;
import com.proin.conex.modelos.transporte.TAlbaran;
import com.proin.conex.modelos.transporte.TConsumo;
import com.proin.conex.modelos.transporte.TIncidenciaAlbaran;
import com.proin.conex.modelos.transporte.TIncidenciaAlbaranId;
import com.proin.conex.modelos.transporte.TLineaAlbaran;
import com.proin.conex.modelos.transporte.TMedida;
import com.proin.conex.modelos.transporte.albaranes.TDescripcionHormigon;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AlbaranController implements BaseController<TAlbaran,AlbaranDto> {
	
	// private final AlbaranService albaranService;

	private final ModelMapper modelMapper;
	
	@GetMapping("/")
	public String inicio(Model model) {
		model.addAttribute("albaran", convertToDto(obtenerAlbaran()));
		return "albaran";
	}

	@PostConstruct
	public void init() {
        configMappingDto(); 
    }
	
	private TAlbaran obtenerAlbaran() {
		TAlbaran albaran = new TAlbaran();
		albaran.setAlbaranid(1l);
		albaran.setCantidadARecuperar(new TMedida());
		albaran.setConsumos(obtenerConsumos());
		albaran.setDescripcionHormigon(obtenerDescripcionDeHormigon(albaran));
		albaran.setFechaAlbaran(new Date());
		albaran.setIncidencias(obtenerIncidencias());
		albaran.setLineasAlbaran(obtenerLineasAlbaran(albaran));
		return albaran;
	}

	private List<TLineaAlbaran> obtenerLineasAlbaran(TAlbaran albaran) {
		TLineaAlbaran a = new TLineaAlbaran();
		a.setAlbaran(albaran);
		a.setCantidadRestantePedido(new TMedida());
		a.setLineaalbaranid(1);
		return List.of(a);
	}

	private List<TIncidenciaAlbaran> obtenerIncidencias() {
		TIncidenciaAlbaran a = new TIncidenciaAlbaran();
		a.setCantidadARecuperar(new TMedida());
		TIncidenciaAlbaranId id = new TIncidenciaAlbaranId();
		id.setCentro("centro");
		id.setCodigoExterno("codigoexterno");
		id.setCodigoExternoAlbaran("codigoexternoalbaran");
		id.setCodigoPlanta("codigoplanta");
		id.setSerieAlbaran("seriealbaran");
		return List.of(a);
	}

	private TDescripcionHormigon obtenerDescripcionDeHormigon(TAlbaran albaran) {
		TDescripcionHormigon a = new TDescripcionHormigon();
		a.setAlbaran(albaran);
		return a;
	}

	private List<TConsumo> obtenerConsumos() {
		TConsumo c = new TConsumo();
		c.setId(1);
		return List.of(c);
	}

	@PostMapping("/submit")
	public String submit(Model model) {
		return "index";
	}

	@Override
	public AlbaranDto convertToDto(TAlbaran entity) {
		return modelMapper.map(entity, AlbaranDto.class);
	}

	@Override
	public TAlbaran convertToEntity(AlbaranDto dto) {
		return modelMapper.map(dto, TAlbaran.class);
	}

	@Override
	public void configMappingDto() {

		modelMapper.createTypeMap(TAlbaran.class, ClienteDto.class).addMappings(mapper -> {
			mapper.map(TAlbaran::getCliente, ClienteDto::setNombre);
			mapper.map(TAlbaran::getId, ClienteDto::setId);
			mapper.map(TAlbaran::getCifTransportista, ClienteDto::setCif);
		});

		modelMapper.createTypeMap(TAlbaran.class, CamionDto.class).addMappings(mapper -> {
			mapper.map(TAlbaran::getMatriculaCamion, CamionDto::setMatricula);
		});
		modelMapper.createTypeMap(TAlbaran.class, RemolqueDto.class).addMappings(mapper -> {
			mapper.map(TAlbaran::getMatricularemolque, RemolqueDto::setMatricula);
		});

		modelMapper.createTypeMap(TAlbaran.class, TransporteDto.class).addMappings(mapper -> {
			mapper.map(TAlbaran::getCodigoEmpresa, TransporteDto::setEmpresa);
			mapper.map(TAlbaran::getCifTransportista, TransporteDto::setCif);
			mapper.map(src -> src.getClienteEsCargadorContractual()?" no se que va aquí":" tampoco aquí", TransporteDto::setCargadorContractual);
			mapper.map(src -> modelMapper.map(src, CamionDto.class), TransporteDto::setCamion);
			mapper.map(src -> modelMapper.map(src, RemolqueDto.class), TransporteDto::setRemolque);
		});

		//revisar esto es un listado
		// modelMapper.createTypeMap(TAlbaran.class, MeteorologiaDto.class).addMappings(mapper -> {
		// 	mapper.map(src -> src.getConsumos().stream().filter(c -> c.getHumedad().getValor()).findFirst().get(), MeteorologiaDto::setTemperatura);
		// });


		TypeMap<TAlbaran, AlbaranDto> typeMapAlbaran = modelMapper.createTypeMap(TAlbaran.class, AlbaranDto.class);
		typeMapAlbaran.addMappings(mapper -> {
			mapper.map(TAlbaran::getNumeroalbaran, AlbaranDto::setNumAlbaran);
			mapper.map(TAlbaran::getFechaAlbaran, AlbaranDto::setFechaEntrega);
			// mapper.map(TAlbaran::get, AlbaranDto::setRadial); @Hugo  radial que es
			// mapper.map(TAlbaran::get, AlbaranDto::setM3);
			// mapper.map(TAlbaran::get, AlbaranDto::setProgresoDia);
			mapper.map(TAlbaran::getPlantasChanged, AlbaranDto::setPlanta);
			mapper.map(src ->src.getClienteEsCargadorContractual()?1:0, AlbaranDto::setViaCarga);
			mapper.map(src -> modelMapper.map(src, ClienteDto.class), AlbaranDto::setCliente);
			mapper.map(TAlbaran::getObra, AlbaranDto::setObra);
			// mapper.map(TAlbaran::get, AlbaranDto::setDireccion);
			// mapper.map(TAlbaran::get, AlbaranDto::setCp);
			// mapper.map(TAlbaran::get, AlbaranDto::setMunicipio);
			mapper.map(src -> modelMapper.map(src, TransporteDto.class), AlbaranDto::setTransporte);
			mapper.map(src -> new MeteorologiaDto(), AlbaranDto::setMeteorologia);
		});
	}
}