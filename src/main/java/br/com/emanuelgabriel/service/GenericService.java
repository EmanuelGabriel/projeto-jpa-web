package br.com.emanuelgabriel.service;

import java.io.Serializable;
import java.util.List;

public interface GenericService<T> extends Serializable {

	T criar(T obj);

	List<T> findAll();

	T findByCodigo(Long codigo);

	void remover(T obj);

}
