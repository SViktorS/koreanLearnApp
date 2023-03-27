package com.myapp.koreanLearnWebApp.repository;

import com.myapp.koreanLearnWebApp.model.VocBook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VocBookRepository extends CrudRepository<VocBook, Integer> {

    public Boolean existsByName(String name);
}
