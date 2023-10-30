package org.iesbelen.streams.tests;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.iesbelen.streams.dao.DepartamentoDAOImpl;
import org.iesbelen.streams.dao.EmpleadoDAOImpl;
import org.iesbelen.streams.entity.Departamento;
import org.iesbelen.streams.entity.Empleado;
import org.junit.jupiter.api.Test;

class EmpleadosStreamTests {

    @Test
    void testSkeletonDepartamento() {

        DepartamentoDAOImpl depHome = new DepartamentoDAOImpl();

        try {
            depHome.beginTransaction();

            List<Departamento> listDep = depHome.findAll();


            //TODO STREAMS

        } catch (RuntimeException e) {
            depHome.rollbackTransaction();
            throw e; // or display error message
        }
    }


    @Test
    void testSkeletonEmpleado() {

        EmpleadoDAOImpl empHome = new EmpleadoDAOImpl();
        try {
            empHome.beginTransaction();

            List<Empleado> listProd = empHome.findAll();

            //TODO STREAMS

        } catch (RuntimeException e) {
            empHome.rollbackTransaction();
            throw e; // or display error message
        }
    }

    @Test
    void testAllDepartamento() {

        DepartamentoDAOImpl depHome = new DepartamentoDAOImpl();

        try {
            depHome.beginTransaction();

            List<Departamento> listDep = depHome.findAll();
            listDep.forEach(System.out::println);

        } catch (RuntimeException e) {
            depHome.rollbackTransaction();
            throw e; // or display error message
        }
    }

    @Test
    void testAllEmpleado() {

        EmpleadoDAOImpl empHome = new EmpleadoDAOImpl();
        try {
            empHome.beginTransaction();

            List<Empleado> listEmp = empHome.findAll();
            listEmp.forEach(System.out::println);

        } catch (RuntimeException e) {
            empHome.rollbackTransaction();
            throw e; // or display error message
        }

    }

    /**
     * 1. Lista el código de los departamentos de los empleados,
     * eliminando los códigos que aparecen repetidos.
     */
    @Test
    void test1() {

        EmpleadoDAOImpl empHome = new EmpleadoDAOImpl();
        try {
            empHome.beginTransaction();

            List<Empleado> listEmp = empHome.findAll();

            listEmp.stream()
                    .filter(empleado -> empleado.getDepartamento() != null)
                    .map(empleado -> empleado.getDepartamento().getCodigo())
                    .distinct()
                    .forEach(System.out::println);

        } catch (RuntimeException e) {
            empHome.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 2. Lista el nombre y apellidos de los empleados en una única columna, convirtiendo todos los caracteres en mayúscula
     */
    @Test
    void test2() {

        EmpleadoDAOImpl empHome = new EmpleadoDAOImpl();

        Function<Empleado, String> toStringWithFormat = (empleado) ->
                empleado.getNombre() + " "
                        + empleado.getApellido1() + " "
                        + empleado.getApellido2();

        try {
            empHome.beginTransaction();

            List<Empleado> listEmp = empHome.findAll();

            listEmp.stream()
                    .map(toStringWithFormat)
                    .forEach(System.out::println);

        } catch (RuntimeException e) {
            empHome.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 3. Lista el código de los empleados junto al nif, pero el nif deberá aparecer en dos columnas,
     * una mostrará únicamente los dígitos del nif y la otra la letra.
     */

    @Test
    void test3() {

        EmpleadoDAOImpl empHome = new EmpleadoDAOImpl();

        Function<Empleado, String> toStringWithFormat = (empleado) -> {
            String nifDigitos = empleado.getNif().substring(0, 8);
            char nifLetra = empleado.getNif().charAt(8);

            return nifDigitos + " " + nifLetra;
        };

        try {
            empHome.beginTransaction();

            List<Empleado> listEmp = empHome.findAll();

            listEmp.stream()
                    .map(toStringWithFormat)
                    .forEach(System.out::println);

        } catch (RuntimeException e) {
            empHome.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 4. Lista el nombre de cada departamento y el valor del presupuesto actual del que dispone.
     * Para calcular este dato tendrá que restar al valor del presupuesto inicial (columna presupuesto) los gastos que se han generado (columna gastos).
     * Tenga en cuenta que en algunos casos pueden existir valores negativos.
     */
    void test4() {

        DepartamentoDAOImpl depHome = new DepartamentoDAOImpl();

        try {
            depHome.beginTransaction();

            List<Departamento> listDep = depHome.findAll();


        } catch (RuntimeException e) {
            depHome.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 5. Lista el nombre de los departamentos y el valor del presupuesto actual
     * ordenado de forma ascendente.
     */
    @Test
    void test5() {

        DepartamentoDAOImpl depHome = new DepartamentoDAOImpl();

        try {
            depHome.beginTransaction();

            List<Departamento> listDep = depHome.findAll();

            listDep.stream()
                    .sorted(comparing(Departamento::getPresupuesto))
                    .map(departamento -> departamento.getNombre() + " " + departamento.getPresupuesto())
                    .forEach(System.out::println);


        } catch (RuntimeException e) {
            depHome.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 6. Devuelve una lista con el nombre y el presupuesto, de los 3 departamentos
     * que tienen mayor presupuesto
     */
    @Test
    void test6() {

        DepartamentoDAOImpl depHome = new DepartamentoDAOImpl();

        try {
            depHome.beginTransaction();

            List<Departamento> listDep = depHome.findAll();

            listDep.stream()
                    .sorted(comparing(Departamento::getPresupuesto).reversed())
                    .limit(3)
                    .map(departamento -> departamento.getNombre() + " " + departamento.getPresupuesto())
                    .forEach(System.out::println);

        } catch (RuntimeException e) {
            depHome.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 7. Devuelve una lista con el nombre de los departamentos y el presupesto,
     * de aquellos que tienen un presupuesto entre 100000 y 200000 euros
     */
    @Test
    void test7() {

        DepartamentoDAOImpl depHome = new DepartamentoDAOImpl();

        try {
            depHome.beginTransaction();

            List<Departamento> listDep = depHome.findAll();

            listDep.stream()
                    .filter(departamento -> departamento.getPresupuesto() > 100000 && departamento.getPresupuesto() < 200000)
                    .map(departamento -> departamento.getNombre() + " " + departamento.getPresupuesto())
                    .forEach(System.out::println);

        } catch (RuntimeException e) {
            depHome.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 8. Devuelve una lista con 5 filas a partir de la tercera fila de empleado ordenado
     * por código de empleado. La tercera fila se debe incluir en la respuesta.
     */
    @Test
    void test8() {

        EmpleadoDAOImpl empHome = new EmpleadoDAOImpl();
        try {
            empHome.beginTransaction();

            List<Empleado> listEmp = empHome.findAll();


        } catch (RuntimeException e) {
            empHome.rollbackTransaction();
            throw e; // or display error message
        }

    }

    /**
     * 9. Devuelve una lista con el nombre de los departamentos y el gasto,
     * de aquellos que tienen menos de 5000 euros de gastos.
     * Ordenada de mayor a menor gasto.
     */
    @Test
    void test9() {

        DepartamentoDAOImpl depHome = new DepartamentoDAOImpl();

        try {
            depHome.beginTransaction();

            List<Departamento> listDep = depHome.findAll();

            listDep.stream()
                    .filter(departamento -> departamento.getGastos() < 5000)
                    .sorted(comparing(Departamento::getGastos).reversed())
                    .map(departamento -> departamento.getNombre() + " " + departamento.getGastos())
                    .forEach(System.out::println);

        } catch (RuntimeException e) {
            depHome.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 10. Lista todos los datos de los empleados cuyo segundo apellido sea Díaz o Moreno
     */
    @Test
    void test10() {

        EmpleadoDAOImpl empHome = new EmpleadoDAOImpl();
        try {
            empHome.beginTransaction();

            List<Empleado> listEmp = empHome.findAll();

            listEmp.stream()
                    .filter(empleado -> empleado.getApellido2() != null)
                    .filter(empleado -> empleado.getApellido2().equalsIgnoreCase("Díaz")
                            || empleado.getApellido2().equalsIgnoreCase("Moreno"))
                    .forEach(System.out::println);

        } catch (RuntimeException e) {
            empHome.rollbackTransaction();
            throw e; // or display error message
        }

    }

    /**
     * 11. Lista los nombres, apellidos y nif de los empleados que
     * trabajan en los departamentos 2, 4 o 5
     */
    @Test
    void test11() {

        EmpleadoDAOImpl empHome = new EmpleadoDAOImpl();
        Set<Integer> departamentos = Set.of(2, 4, 5);
        try {
            empHome.beginTransaction();

            List<Empleado> listEmp = empHome.findAll();

            listEmp.stream()
                    .filter(empleado -> empleado.getDepartamento() != null)
                    .filter(empleado -> departamentos.contains(empleado.getDepartamento().getCodigo()))
                    .map(empleado -> empleado.getNombre()
                            + " " + empleado.getApellido1()
                            + " " + empleado.getApellido2()
                            + " " + empleado.getNif()
                    )
                    .forEach(System.out::println);

        } catch (RuntimeException e) {
            empHome.rollbackTransaction();
            throw e; // or display error message
        }

    }


    /**
     * 12. Devuelve el nombre del departamento donde trabaja el empleado
     * que tiene el nif 38382980M
     */
    @Test
    void test12() {

        EmpleadoDAOImpl empHome = new EmpleadoDAOImpl();
        try {
            empHome.beginTransaction();

            List<Empleado> listEmp = empHome.findAll();

            listEmp.stream()
                    .filter(empleado -> empleado.getNif().equals("38382980M"))
                    .map(empleado -> empleado.getDepartamento().getNombre())
                    .forEach(System.out::println);

        } catch (RuntimeException e) {
            empHome.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 13. Devuelve una lista con el nombre de los empleados que tienen los departamentos
     * que no tienen un presupuesto entre 100000 y 200000 euros.
     */
    @Test
    void test13() {

        EmpleadoDAOImpl empHome = new EmpleadoDAOImpl();
        try {
            empHome.beginTransaction();

            List<Empleado> listEmp = empHome.findAll();

            listEmp.stream()
                    .filter(empleado -> empleado.getDepartamento() != null)
                    .filter(empleado -> empleado.getDepartamento().getPresupuesto() < 100000
                            && empleado.getDepartamento().getPresupuesto() > 200000)
                    .map(Empleado::getNombre)
                    .forEach(System.out::println);

        } catch (RuntimeException e) {
            empHome.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 14. Calcula el valor mínimo del presupuesto de todos los departamentos.
     */

    @Test
    void test14() {

        DepartamentoDAOImpl depHome = new DepartamentoDAOImpl();

        try {
            depHome.beginTransaction();

            List<Departamento> listDep = depHome.findAll();

            listDep.stream()
                    .mapToDouble(Departamento::getPresupuesto)
                    .min()
                    .ifPresent(System.out::println);

        } catch (RuntimeException e) {
            depHome.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 15. Calcula el número de empleados que hay en cada departamento.
     * Tienes que devolver dos columnas, una con el nombre del departamento
     * y otra con el número de empleados que tiene asignados.
     */

    @Test
    void test15() {

        DepartamentoDAOImpl depHome = new DepartamentoDAOImpl();

        try {
            depHome.beginTransaction();

            List<Departamento> listDep = depHome.findAll();


        } catch (RuntimeException e) {
            depHome.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 16. Calcula el número total de empleados que trabajan en cada
     * unos de los departamentos que tienen un presupuesto mayor a 200000 euros.
     */

    @Test
    void test16() {

        DepartamentoDAOImpl depHome = new DepartamentoDAOImpl();

        try {
            depHome.beginTransaction();

            List<Departamento> listDep = depHome.findAll();

            Long cantidadDeEmpleados = listDep.stream()
                    .filter(departamento -> departamento.getPresupuesto() > 200000)
                    .map(Departamento::getEmpleados)
                    .count();

            System.out.println(cantidadDeEmpleados);

        } catch (RuntimeException e) {
            depHome.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 17. Calcula el nombre de los departamentos que tienen más de 2 empleados.
     * El resultado debe tener dos columnas, una con el nombre del departamento y
     * otra con el número de empleados que tiene asignados
     */

    @Test
    void test17() {

        DepartamentoDAOImpl depHome = new DepartamentoDAOImpl();

        try {
            depHome.beginTransaction();

            List<Departamento> listDep = depHome.findAll();


        } catch (RuntimeException e) {
            depHome.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 18. Lista todos los nombres de departamentos junto con los nombres y apellidos
     * de los empleados.
     */
    @Test
    void test18() {

        DepartamentoDAOImpl depHome = new DepartamentoDAOImpl();
        StringBuilder cadena = new StringBuilder();

        try {
            depHome.beginTransaction();

            List<Departamento> listDep = depHome.findAll();

            listDep.stream()
                    .map(departamento -> departamento.getNombre() + " " + departamento.getEmpleados())
                    .forEach(System.out::println);

        } catch (RuntimeException e) {
            depHome.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 19. Devuelve la media de empleados que trabajan en los departamentos
     */
    @Test
    void test19() {

        DepartamentoDAOImpl depHome = new DepartamentoDAOImpl();

        try {
            depHome.beginTransaction();

            List<Departamento> listDep = depHome.findAll();


        } catch (RuntimeException e) {
            depHome.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 20. Calcula el presupuesto máximo, mínimo  y número total de departamentos.
     */
    @Test
    void test20() {

        DepartamentoDAOImpl depHome = new DepartamentoDAOImpl();

        try {
            depHome.beginTransaction();

            List<Departamento> listDep = depHome.findAll();

            DoubleSummaryStatistics presupestos = listDep.stream()
                    .collect(summarizingDouble(Departamento::getPresupuesto));

            Long numeroDepartamientos = listDep.stream()
                    .distinct()
                    .count();
            System.out.println(presupestos.getMax());
            System.out.println(presupestos.getMin());
            System.out.println(numeroDepartamientos);


        } catch (RuntimeException e) {
            depHome.rollbackTransaction();
            throw e; // or display error message
        }
    }
}
