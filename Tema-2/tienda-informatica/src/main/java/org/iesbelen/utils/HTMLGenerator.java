package org.iesbelen.utils;

import org.iesbelen.model.Producto;

import java.util.Optional;

public class HTMLGenerator {

    public static String getProductoHTML(Optional<Producto> productoOptional) {
        return productoOptional.isPresent()
                ? """
                <div class="producto">
                      <p><strong>Id Producto: </strong>
                      """ + productoOptional.get().getIdProducto() + """ 
                      </p>
                      
                      <p><strong>Nombre: </strong>
                      """ + productoOptional.get().getNombre() + """ 
                      </p>
                      
                      <p><strong>Precio: </strong>
                      """ + productoOptional.get().getPrecio() + """ 
                      </p>
                      
                      <p><strong>Id Fabricante: </strong>
                      """ + productoOptional.get().getCodigo_fabricante() + """ 
                      </p>
                </div>
                """
                : "<p>Producto no disponible</p>";
    }
}
