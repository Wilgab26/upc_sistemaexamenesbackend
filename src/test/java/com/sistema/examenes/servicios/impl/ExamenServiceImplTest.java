package com.sistema.examenes.servicios.impl;

import com.sistema.examenes.modelo.Categoria;
import com.sistema.examenes.modelo.Examen;
import com.sistema.examenes.repositorios.ExamenRepository;
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
class ExamenServiceImplTest {
    @MockBean
    private ExamenRepository repository;

    @Autowired
    private ExamenServiceImpl service;

    private Examen examen;
    private Categoria categoria;

    @BeforeEach
    void setUp() {
        categoria = new Categoria();
        categoria.setCategoriaId(1L);
        categoria.setTitulo("Test Categoria");

        examen = new Examen();
        examen.setExamenId(1L);
        examen.setTitulo("Test Examen");
        examen.setCategoria(categoria);
        examen.setActivo(true);
    }

    @Test
    void agregarExamen() {
        when(repository.save(examen)).thenReturn(examen);

        Examen result = service.agregarExamen(examen);

        assertNotNull(result);
        assertEquals(examen.getExamenId(), result.getExamenId());
        verify(repository, times(1)).save(examen);
    }

    @Test
    void actualizarExamen() {
        when(repository.save(examen)).thenReturn(examen);

        Examen result = service.actualizarExamen(examen);

        assertNotNull(result);
        assertEquals(examen.getExamenId(), result.getExamenId());
        verify(repository, times(1)).save(examen);
    }

    @Test
    void obtenerExamenes() {
        List<Examen> examenes = Arrays.asList(examen);
        when(repository.findAll()).thenReturn(examenes);

        Set<Examen> result = service.obtenerExamenes();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void obtenerExamen() {
        when(repository.findById(1L)).thenReturn(Optional.of(examen));

        Examen result = service.obtenerExamen(1L);

        assertNotNull(result);
        assertEquals(examen.getExamenId(), result.getExamenId());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void eliminarExamen() {
        doNothing().when(repository).delete(any(Examen.class));

        service.eliminarExamen(1L);

        verify(repository, times(1)).delete(any(Examen.class));
    }

    @Test
    void listarExamenesDeUnaCategoria() {
        List<Examen> examenes = Arrays.asList(examen);
        when(repository.findByCategoria(categoria)).thenReturn(examenes);

        List<Examen> result = service.listarExamenesDeUnaCategoria(categoria);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository, times(1)).findByCategoria(categoria);
    }

    @Test
    void obtenerExamenesActivos() {
        List<Examen> examenes = Arrays.asList(examen);
        when(repository.findByActivo(true)).thenReturn(examenes);

        List<Examen> result = service.obtenerExamenesActivos();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository, times(1)).findByActivo(true);
    }

    @Test
    void obtenerExamenesActivosDeUnaCategoria() {
        List<Examen> examenes = Arrays.asList(examen);
        when(repository.findByCategoriaAndActivo(categoria, true)).thenReturn(examenes);

        List<Examen> result = service.obtenerExamenesActivosDeUnaCategoria(categoria);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository, times(1)).findByCategoriaAndActivo(categoria, true);
    }
}