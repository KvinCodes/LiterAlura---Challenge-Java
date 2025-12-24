package com.alura.literalura.repository;

import com.alura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    List<Autor> findByNacimientoLessThanEqualAndFallecimientoGreaterThan(
            Integer anio,
            Integer anio2
    );

    // Para autores que NO tienen año de fallecimiento
    List<Autor> findByNacimientoLessThanEqualAndFallecimientoIsNull(Integer anio);
}
