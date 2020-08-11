package br.com.prove.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.prove.api.dto.MacroprocessDTO;
import br.com.prove.api.model.Macroprocesso;
import br.com.prove.api.request.MacroprocessRequest;

@Component
public class MacroprocessMapper {

	@Autowired
	private ModelMapper modelMapper;

	public MacroprocessDTO modelToDto(Macroprocesso macroprocesso) {
		return modelMapper.map(macroprocesso, MacroprocessDTO.class);
	}

	public Macroprocesso dtoRequestToModel(MacroprocessRequest request) {
		return modelMapper.map(request, Macroprocesso.class);
	}
}
