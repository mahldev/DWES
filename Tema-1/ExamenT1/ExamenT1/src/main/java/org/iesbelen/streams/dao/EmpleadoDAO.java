package org.iesbelen.streams.dao;

import org.iesbelen.streams.entity.Empleado;

import java.util.List;

public interface EmpleadoDAO {
    
        List<Empleado> findAll();

}
