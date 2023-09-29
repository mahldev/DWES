package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.example.annotations.aDirectivo;
import org.example.annotations.aEmpleado;
import org.example.annotations.aOficial;
import org.example.annotations.aTecnico;

@aDirectivo(value = @aEmpleado(dni = "1", nombre = "1", apellidos = "1"), codigoDespacho = 1)
@aTecnico(value = @aEmpleado(dni = "2", nombre = "2", apellidos = "2"), codigoTaller = 2, perfil = "2")
@aOficial(value = @aEmpleado(dni = "3", nombre = "3", apellidos = "3"), codigoTaller = 3, categoria = "3")
public class Empresa {

        private String nombre;
        private List<org.example.Empleado> empleados;

        public Empresa(String nombre) {
                this.empleados = new ArrayList<>();
                this.nombre = nombre;
        }

        public String getNombre() {
                return nombre;
        }

        public void setNombre(String nombre) {
                this.nombre = nombre;
        }

        public <T extends Empleado> boolean add(T e) {
                return empleados.add(e);
        }

        public static Empresa cargadorDeContexto(String nombre) {
                Empresa empresa = new Empresa(nombre);

                cargarDirectivos(empresa);
                cargarTecnicos(empresa);
                cargarOficiales(empresa);

                return empresa;
        }

        private static void cargarOficiales(Empresa empresa) {
                List<aOficial> oficiales = Utils.recuperaAnotaciones(aOficial.class);
                oficiales.stream()
                                .map(Utils::crearOficial)
                                .forEach(empresa::add);
        }

        private static void cargarTecnicos(Empresa empresa) {
                List<aTecnico> tecnicos = Utils.recuperaAnotaciones(aTecnico.class);
                tecnicos.stream()
                                .map(Utils::crearTecnico)
                                .forEach(empresa::add);
        }

        private static void cargarDirectivos(Empresa empresa) {
                List<aDirectivo> directivos = Utils.recuperaAnotaciones(aDirectivo.class);
                directivos.stream()
                                .map(Utils::crearDirectivo)
                                .forEach(empresa::add);
        }

        @Override
        public String toString() {
                return empleados.stream()
                                .map(Empleado::toString)
                                .collect(Collectors.joining("\n", "",
                                                empleados.isEmpty() ? "Vacio" : ""));
        }
}
