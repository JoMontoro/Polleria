
package com.example.integrador.Services;

import com.example.integrador.Entidades_Model.Chefs;
import com.example.integrador.Repositorio.ChefsDAO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
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

    // Estilos para el encabezado
    HSSFCellStyle headerStyle = workbook.createCellStyle();
    HSSFFont font = workbook.createFont();
    font.setBold(true); // Texto en negrita
    headerStyle.setFont(font);
    headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex()); // Fondo azul claro
    headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

    // Crear fila de encabezado
    HSSFRow headerRow = sheet.createRow(0);
    String[] headers = {"chefid", "nombre", "apellido", "especialidad", "telefono", "CorreoElectronico"};
    for (int i = 0; i < headers.length; i++) {
        HSSFCell cell = headerRow.createCell(i);
        cell.setCellValue(headers[i]);
        cell.setCellStyle(headerStyle);
        sheet.autoSizeColumn(i); // Ajustar ancho de columna automáticamente
    }

    // Estilos para las filas de datos
    HSSFCellStyle dataStyle = workbook.createCellStyle();
    dataStyle.setWrapText(true); // Ajustar texto en celdas

    int dataRowIndex = 1;
    for (Chefs chef : chefs) {
        HSSFRow dataRow = sheet.createRow(dataRowIndex++);
        dataRow.createCell(0).setCellValue(chef.getChefid());
        dataRow.createCell(1).setCellValue(chef.getNombre());
        dataRow.createCell(2).setCellValue(chef.getApellido());
        dataRow.createCell(3).setCellValue(chef.getEspecialidad());
        dataRow.createCell(4).setCellValue(chef.getTelefono());
        dataRow.createCell(5).setCellValue(chef.getCorreo_electronico());

        // Aplicar estilo a las celdas de datos
        for (int i = 0; i < headers.length; i++) {
            dataRow.getCell(i).setCellStyle(dataStyle);
            sheet.autoSizeColumn(i); // Ajustar ancho de cada columna
        }
    }

    ByteArrayOutputStream ops = new ByteArrayOutputStream();
    workbook.write(ops);
    workbook.close();
    ops.close();

    return new ByteArrayInputStream(ops.toByteArray());
}
    
    
    
    
    
    
    
    
    
    
    
}
