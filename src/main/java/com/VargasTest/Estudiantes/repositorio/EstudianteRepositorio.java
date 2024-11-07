package com.VargasTest.Estudiantes.repositorio;

import com.VargasTest.Estudiantes.modelo.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EstudianteRepositorio extends JpaRepository<Estudiante, Integer> {
}
