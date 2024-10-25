
package com.example.integrador.Services;

import com.example.integrador.Entidades_Model.Clientes;
import com.example.integrador.Repositorio.ClientesDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioClientes {
    @Autowired
    private ClientesDAO clientesDAO;
    
     //Select
    @Transactional(readOnly=true)
    public List<Clientes> getList() {
        return clientesDAO.findAll();
    }
    
    //INSERT Y UPDATE
    @Transactional
    public Clientes save(Clientes clientes) {
        return clientesDAO.save(clientes);
    }
    
    //SELECT
    @Transactional(readOnly=true)
    public Clientes get(Long id) {
        return clientesDAO.findById(id).orElse(null);
    }
    
    //DELETE
    @Transactional
    public void delete(Long id) {
        clientesDAO.deleteById(id);
    }
    
}
