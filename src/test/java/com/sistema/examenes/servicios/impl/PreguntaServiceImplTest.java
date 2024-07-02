package com.sistema.examenes.servicios.impl;

import com.sistema.examenes.modelo.Examen;
import com.sistema.examenes.modelo.Pregunta;
import com.sistema.examenes.repositorios.PreguntaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PreguntaServiceImplTest {
    @MockBean
    private PreguntaRepository repository;

    @Autowired
    private PreguntaServiceImpl service;

    private Pregunta pregunta;
    private Examen examen;


    @BeforeEach
    void setUp() {
        examen = new Examen();
        examen.setExamenId(1L);
        examen.setTitulo("Test Examen");

        pregunta = new Pregunta();
        pregunta.setPreguntaId(1L);
        pregunta.setContenido("Test Pregunta");
        pregunta.setExamen(examen);
    }

    @Test
    void agregarPregunta() {
        when(repository.save(pregunta)).thenReturn(pregunta);

        Pregunta result = service.agregarPregunta(pregunta);

        assertNotNull(result);
        assertEquals(pregunta.getPreguntaId(), result.getPreguntaId());
        verify(repository, times(1)).save(pregunta);
    }

    @Test
    void actualizarPregunta() {
        when(repository.save(pregunta)).thenReturn(pregunta);

        Pregunta result = service.actualizarPregunta(pregunta);

        assertNotNull(result);
        assertEquals(pregunta.getPreguntaId(), result.getPreguntaId());
        verify(repository, times(1)).save(pregunta);
    }

    @Test
    void obtenerPreguntas() {
        List<Pregunta> preguntas = Arrays.asList(pregunta);
        when(repository.findAll()).thenReturn(preguntas);

        Set<Pregunta> result = service.obtenerPreguntas();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void obtenerPregunta() {
        when(repository.findById(1L)).thenReturn(Optional.of(pregunta));

        Pregunta result = service.obtenerPregunta(1L);

        assertNotNull(result);
        assertEquals(pregunta.getPreguntaId(), result.getPreguntaId());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void obtenerPreguntasDelExamen() {
        Set<Pregunta> preguntas = new HashSet<>(Arrays.asList(pregunta));
        when(repository.findByExamen(examen)).thenReturn(preguntas);

        Set<Pregunta> result = service.obtenerPreguntasDelExamen(examen);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository, times(1)).findByExamen(examen);
    }

    @Test
    void eliminarPregunta() {
        doNothing().when(repository).delete(any(Pregunta.class));

        service.eliminarPregunta(1L);

        verify(repository, times(1)).delete(any(Pregunta.class));
    }

    @Test
    void listarPregunta() {
        when(repository.getById(1L)).thenReturn(pregunta);

        Pregunta result = service.listarPregunta(1L);

        assertNotNull(result);
        assertEquals(pregunta.getPreguntaId(), result.getPreguntaId());
        verify(repository, times(1)).getById(1L);
    }
}