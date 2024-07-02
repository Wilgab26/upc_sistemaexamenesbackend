package com.sistema.examenes.servicios.impl;

import com.sistema.examenes.modelo.Categoria;
import com.sistema.examenes.repositorios.CategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CategoriaServiceImplTest {
    @MockBean
    private CategoriaRepository categoriaRepository;
    @Autowired
    private CategoriaServiceImpl categoriaService;

    private Categoria categoria;

    @BeforeEach
    void setUp() {
        categoria = new Categoria();
        categoria.setCategoriaId(1L);
        categoria.setTitulo("Test Categoria");
    }

    @Test
    void agregarCategoria() {
        when(categoriaRepository.save(categoria)).thenReturn(categoria);

        Categoria resultado = categoriaService.agregarCategoria(categoria);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getCategoriaId());
        assertEquals(categoria.getCategoriaId(), resultado.getCategoriaId());
        verify(categoriaRepository, times(1)).save(categoria);
    }

    @Test
    void actualizarCategoria() {
        when(categoriaRepository.save(categoria)).thenReturn(categoria);

        categoriaService.agregarCategoria(categoria);

        categoria.setTitulo("Categoria actualizada");

        Categoria resultado = categoriaService.actualizarCategoria(categoria);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getCategoriaId());
        assertEquals("Categoria actualizada", resultado.getTitulo());
        verify(categoriaRepository, times(2)).save(categoria);
    }

    @Test
    void obtenerCategorias() {
        List<Categoria> categorias = Arrays.asList(categoria);
        when(categoriaRepository.findAll()).thenReturn(categorias);

        Set<Categoria> resultado = categoriaService.obtenerCategorias();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(categoriaRepository, times(1)).findAll();
    }

    @Test
    void obtenerCategoria() {
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));

        Categoria resultado = categoriaService.obtenerCategoria(1L);

        assertNotNull(resultado);
        assertEquals(categoria.getCategoriaId(), resultado.getCategoriaId());
        verify(categoriaRepository, times(1)).findById(1L);
    }

    @Test
    void eliminarCategoria() {
        doNothing().when(categoriaRepository).delete(any(Categoria.class));

        categoriaService.eliminarCategoria(1L);

        verify(categoriaRepository, times(1)).delete(any(Categoria.class));
    }
}