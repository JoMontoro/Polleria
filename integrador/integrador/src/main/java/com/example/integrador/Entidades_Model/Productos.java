
package com.example.integrador.Entidades_Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table (name = "productos")
public class Productos {
     @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long producto_id;
    
    private String nombre_producto;
    private String descripcion;
    private Long precio;
    private Integer stock;
    private Long proveedor_id;
    private Long almacen_id;
    private String imagen_url;
    private String categoria;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "proveedor_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Proveedores proveedor;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "almacen_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Almacenes almacen;

}