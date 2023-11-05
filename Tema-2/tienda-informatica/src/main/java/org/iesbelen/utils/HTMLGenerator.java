package org.iesbelen.utils;

import org.iesbelen.model.Producto;

import java.util.Optional;

public class HTMLGenerator {

    public static String getProductoHTML(Optional<Producto> productoOptional) {
        return productoOptional.isPresent()
                ? """
                <div class="productos">
                    <label for="id">Código</label>
                    <input id="id" value=" """ + productoOptional.get().getIdProducto() + """
                        "readonly />
                    
                    <label for="name">Nombre</label>
                    <input id="name" value=" """ + productoOptional.get().getNombre() + """
                        " readonly />
                    
                    <label for="price">Precio</label>
                    <input id="price" value=" """ + productoOptional.get().getPrecio() + """ 
                    " readonly />
                    
                    <label for="manufacturer-code">Codigo Fabricante</label>
                    <input id="manufacturer-code" value=" """ + productoOptional.get().getCodigo_fabricante() + """
                    " readonly />
                </div>
                """
                : "<p>Producto no disponible</p>";
    }
}
