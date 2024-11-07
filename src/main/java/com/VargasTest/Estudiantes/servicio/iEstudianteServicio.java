package com.VargasTest.Estudiantes.servicio;

import com.VargasTest.Estudiantes.modelo.Estudiante;

import java.util.List;

public interface iEstudianteServicio {
    public List<Estudiante> listarEstudiante();

    public Estudiante buscarEstudiantePorId(Integer idEstudiante);

    public void guardarEstudiante(Estudiante estudiante);

    public void eliminarestudiante(Estudiante estudiante);
}
