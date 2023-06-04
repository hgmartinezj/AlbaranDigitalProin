package com.proin.albaran.controller;

public interface BaseController<Entity,Dto> {

	Dto convertToDto(Entity dto);

	Entity convertToEntity(Dto entity);

	abstract void configMappingDto();
}