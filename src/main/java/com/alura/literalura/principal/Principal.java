package com.alura.literalura.principal;

import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Libro;
import com.alura.literalura.service.AutorService;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;
import com.alura.literalura.service.LibroService;
import com.alura.literalura.dto.*;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Principal {

    private static final String URL_BASE = "https://gutendex.com/books/?search=";

    private final Scanner scanner = new Scanner(System.in);
    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private final ConvierteDatos conversor = new ConvierteDatos();

    private final LibroService libroService;
    private final AutorService autorService;

    public Principal(LibroService libroService, AutorService autorService) {
        this.libroService = libroService;
        this.autorService = autorService;
    }

    public void mostrarMenu() {
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("""
                    ===== LITERALURA =====
                    1 - Buscar libro por título
                    2 - Listar todos los libros
                    3 - Cantidad de libros por idioma
                    4 - Listar autores
                    5 - Listar autores vivos en un año
                    0 - Salir
                    =====================
                    """);

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> buscarLibro();
                case 2 -> libroService.listarLibros();
                case 3 -> listarCantidadLibrosPorIdioma();
                case 4 -> autorService.listarAutores().forEach(System.out::println);
                case 5 -> listarAutoresVivos();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida");
            }
        }
    }

    private void buscarLibro() {
        System.out.print("Ingrese el título del libro: ");
        String titulo = scanner.nextLine();

        String json = consumoAPI.obtenerDatos(URL_BASE + titulo.replace(" ", "%20"));
        DatosRespuesta respuesta = conversor.obtenerDatos(json, DatosRespuesta.class);

        if (respuesta.results().isEmpty()) {
            System.out.println("Libro no encontrado.");
            return;
        }

        DatosLibro datosLibro = respuesta.results().get(0);
        DatosAutor datosAutor = datosLibro.authors().get(0);

        Autor autor = new Autor(datosAutor.name(), datosAutor.nacimiento(), datosAutor.fallecimiento());

        Libro libro = new Libro(datosLibro.title(), datosLibro.languages().get(0), datosLibro.numeroDescargas().doubleValue(), autor);

        autorService.guardarAutor(autor);
        libroService.guardarLibro(libro);

        System.out.println(libro);
    }

    private void listarCantidadLibrosPorIdioma() {
        System.out.println("===== LIBROS POR IDIOMA =====");
        System.out.println("Español: " + libroService.contarPorIdioma("es"));
        System.out.println("Inglés : " + libroService.contarPorIdioma("en"));
    }

    private void listarAutoresVivos() {
        System.out.print("Ingrese el año: ");
        int anio = scanner.nextInt();
        scanner.nextLine();

        if (anio < 0) {
            System.out.println("Año inválido");
            return;
        }

        var autores = autorService.autoresVivosEn(anio);

        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores vivos en ese año.");
        } else {
            autores.forEach(System.out::println);
        }
    }
}
