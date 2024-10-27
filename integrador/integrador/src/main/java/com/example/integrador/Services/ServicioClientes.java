package com.example.integrador.Services;

import com.example.integrador.Entidades_Model.Clientes;
import com.example.integrador.Repositorio.ClientesDAO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioClientes {

    @Autowired
    private ClientesDAO clientesDAO;

    //Select
    @Transactional(readOnly = true)
    public List<Clientes> getList() {
        return clientesDAO.findAll();
    }

    //INSERT Y UPDATE
    @Transactional
    public Clientes save(Clientes clientes) {
        return clientesDAO.save(clientes);
    }

    //SELECT
    @Transactional(readOnly = true)
    public Clientes get(Long id) {
        return clientesDAO.findById(id).orElse(null);
    }

    //DELETE
    @Transactional
    public void delete(Long id) {
        clientesDAO.deleteById(id);
    }

    public ByteArrayInputStream generarExcel() throws IOException {
        List<Clientes> clientes = clientesDAO.findAll();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Cliente Info");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("cliente_id");
        row.createCell(1).setCellValue("nombre");
        row.createCell(2).setCellValue("apellido");
        row.createCell(3).setCellValue("Correo_Electronico");
        row.createCell(4).setCellValue("telefono");
        row.createCell(5).setCellValue("direccion");

        int dataRowIndex = 1;
        for (Clientes cliente : clientes) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(cliente.getCliente_id());
            dataRow.createCell(1).setCellValue(cliente.getNombre());
            dataRow.createCell(2).setCellValue(cliente.getApellido());
            dataRow.createCell(3).setCellValue(cliente.getCorreo_Electronico());
            dataRow.createCell(4).setCellValue(cliente.getTelefono());
            dataRow.createCell(5).setCellValue(cliente.getDireccion());
            dataRowIndex ++;
        }
        ByteArrayOutputStream ops= new ByteArrayOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();
        return new ByteArrayInputStream(ops.toByteArray());
    }
}
