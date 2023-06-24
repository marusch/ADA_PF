package com.example.ProyectoFinal.controladores;

import com.example.ProyectoFinal.entidades.Autor;
import com.example.ProyectoFinal.entidades.Libro;
import com.example.ProyectoFinal.servicios.AutorServicio;
import com.example.ProyectoFinal.servicios.LibroServicio;
import com.example.ProyectoFinal.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class LibroControlador {

    @Autowired
    private LibroServicio libroServicio;

    @Autowired
    private AutorServicio autorServicio;


    @Autowired
    private UsuarioServicio Uservicio;


    @GetMapping("/login")
    public String iniciarSesion(){
        return "login";
    }

    @GetMapping("/")
    public String verPaginaDeInicio(){
        return "index";
    }

    @GetMapping("/home")
    public String paginaPrincipal(){
        return "home";
    }

    @GetMapping("/libros")
    public String listarLibros(Model modelo) {
        modelo.addAttribute("libros", libroServicio.listarTodosLosLibros());
        return "libros";
    }

    @GetMapping("/libros/nuevo")
    public String crearLibroFormulario(Model modelo) {
        Libro libro = new Libro();
        List<Autor> listaAutores = autorServicio.listarTodosLosAutores();

        modelo.addAttribute("libro", libro);
        modelo.addAttribute("autores", listaAutores);

        return "crear_libro";
    }

    @PostMapping("/libros")
    public String guardarLibro(@ModelAttribute("libro") Libro libro, RedirectAttributes redirect) {
        libroServicio.guardarLibro(libro);

        redirect.addFlashAttribute("msgExito", "El libro ha" +
                " sido agregado con exito");

        return "redirect:/libros";
    }

    @GetMapping("/libros/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable Long id, Model modelo) {
        modelo.addAttribute("libro", libroServicio.obtenerLibroPorId(id));
        return "editar_libro";
    }

    @PostMapping("/libros/{id}")
    public String actualizarLibro(@PathVariable Long id, @ModelAttribute("libro") Libro libro, Model modelo) {
        Libro libroExistente = libroServicio.obtenerLibroPorId(id);

        libroExistente.setIsbn(libro.getIsbn());
        libroExistente.setTitulo(libro.getTitulo());
        libroExistente.setAnio(libro.getAnio());
        libroExistente.setEjemplares(libro.getEjemplares());
        libroExistente.setEjemplaresPrestados(libro.getEjemplaresPrestados());

        libroServicio.actualizarLibro(libroExistente);
        return "redirect:/libros";
    }


    @PostMapping("/eliminar/libro/{id}")
    public String eliminarLibro(@PathVariable Long id, RedirectAttributes redirect) {
        Libro libro = libroServicio.obtenerLibroPorId(id);

        libroServicio.eliminarLibro(libro);

        redirect.addFlashAttribute("msgExito", "El libro ha " +
                "sido eliminado correctamente");

        return "redirect:/libros";

    }



}
