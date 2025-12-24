package com.alura.literalura.service;

import com.alura.literalura.model.Autor;
import com.alura.literalura.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<Autor> autoresVivosEn(int anio) {
        List<Autor> vivos = new ArrayList<>();

        vivos.addAll(autorRepository.findByNacimientoLessThanEqualAndFallecimientoGreaterThan(anio, anio));

        vivos.addAll(autorRepository.findByNacimientoLessThanEqualAndFallecimientoIsNull(anio));

        return vivos;
    }

    public List<Autor> listarAutores() {
        return autorRepository.findAll();
    }

    public Autor guardarAutor(Autor autor) {
        return autorRepository.save(autor);
    }
}

