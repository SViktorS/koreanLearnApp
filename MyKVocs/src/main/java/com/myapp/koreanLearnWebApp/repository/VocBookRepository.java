package com.myapp.koreanLearnWebApp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myapp.koreanLearnWebApp.model.VocBook;

@Repository
public interface VocBookRepository extends CrudRepository<VocBook, Integer>{

}
