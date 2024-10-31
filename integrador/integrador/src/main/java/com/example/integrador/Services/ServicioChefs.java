
package com.example.integrador.Services;

import com.example.integrador.Entidades_Model.Chefs;
import com.example.integrador.Repositorio.ChefsDAO;
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
public class ServicioChefs {
    @Autowired
    private ChefsDAO chefsdao;
    
    //Select
    @Transactional(readOnly = true)
    public List<Chefs> getList() {
        return chefsdao.findAll();
    }

    //INSERT Y UPDATE
    @Transactional
    public Chefs save(Chefs chefs) {
        return chefsdao.save(chefs);
    }

    //SELECT
    @Transactional(readOnly = true)
    public Chefs get(Long id) {
        return chefsdao.findById(id).orElse(null);
    }

    //DELETE
    @Transactional
    public void delete(Long id) {
        chefsdao.deleteById(id);
    }
    
    public ByteArrayInputStream generarExcel() throws IOException {
        List<Chefs> chefs = chefsdao.findAll();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Chefs Info");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("chefid");
        row.createCell(1).setCellValue("nombre");
        row.createCell(2).setCellValue("apellido");
        row.createCell(3).setCellValue("especialidad");
        row.createCell(4).setCellValue("telefono");
        row.createCell(5).setCellValue("CorreoElectronico");

        int dataRowIndex = 1;
        for (Chefs chef : chefs) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(chef.getChefid());
            dataRow.createCell(1).setCellValue(chef.getNombre());
            dataRow.createCell(2).setCellValue(chef.getApellido());
            dataRow.createCell(3).setCellValue(chef.getEspecialidad());
            dataRow.createCell(4).setCellValue(chef.getTelefono());
            dataRow.createCell(5).setCellValue(chef.getCorreo_electronico());
            dataRowIndex ++;
        }
        ByteArrayOutputStream ops= new ByteArrayOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();
        return new ByteArrayInputStream(ops.toByteArray());
    }
    
    
    
    
    
    
    
    
    
    
    
}
