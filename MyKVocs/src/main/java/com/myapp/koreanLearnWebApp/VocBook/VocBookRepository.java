package com.myapp.koreanLearnWebApp.VocBook;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VocBookRepository extends CrudRepository<VocBook, Integer>{

}
