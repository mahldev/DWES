package org.iesbelen.model;

public class FabricanteDTO extends Fabricante {

    private int cantidadProductos;

    public int getCantidadProductos() {
        return cantidadProductos;
    }

    public void setCantidadProductos(int cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }

    public static FabricanteDTO crearFabricanteDTOdeFabricante(Fabricante fabricante, int cantidadProductos) {
        FabricanteDTO fabricanteDTO = new FabricanteDTO();

        fabricanteDTO.setIdFabricante(fabricante.getIdFabricante());
        fabricanteDTO.setNombre(fabricante.getNombre());
        fabricanteDTO.setCantidadProductos(cantidadProductos);

        return fabricanteDTO;
    }
}
