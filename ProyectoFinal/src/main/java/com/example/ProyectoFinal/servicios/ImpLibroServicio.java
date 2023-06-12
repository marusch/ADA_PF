package com.example.ProyectoFinal.servicios;

import com.example.ProyectoFinal.entidades.Libro;
import com.example.ProyectoFinal.repositorios.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpLibroServicio implements LibroServicio{

    @Autowired
    private LibroRepositorio libroRepositorio;

    @Override
    public List<Libro> listarTodosLosLibros(){
        return libroRepositorio.findAll();
    }

    @Override
    public Libro guardarLibro(Libro libro){
        return libroRepositorio.save(libro);
    }

    @Override
    public Libro obtenerLibroPorId(Long id){
        return libroRepositorio.findById(id).get();
    }

    @Override
    public Libro actualizarLibro(Libro libro){
        return libroRepositorio.save(libro);
    }

    @Override
    public void eliminarLibro(Libro libro){
        libroRepositorio.delete(libro);
    }
}
