package com.deportes.deportes_api.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.deportes.deportes_api.tablas.Reservas;

@Repository
@Transactional
public interface ReservasRepositorio<T> extends CrudRepository<Reservas, Integer>, PagingAndSortingRepository<Reservas, Integer>
, JpaSpecificationExecutor<Reservas>, JpaRepository<Reservas, Integer>{

	List<Reservas> findByNombre(String name);



}
