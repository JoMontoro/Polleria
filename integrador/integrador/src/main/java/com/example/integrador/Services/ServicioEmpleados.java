/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.integrador.Services;

import com.example.integrador.Entidades_Model.Empleados;
import com.example.integrador.Repositorio.EmpleadoDAO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioEmpleados {
    @Autowired
    private EmpleadoDAO empleadoDAO;

    //Select
    @Transactional(readOnly = true)
    public List<Empleados> getList() {
        return empleadoDAO.findAll();
    }

    //INSERT Y UPDATE
    @Transactional
    public Empleados save(Empleados empleados) {
        return empleadoDAO.save(empleados);
    }

    //SELECT
    @Transactional(readOnly = true)
    public Empleados get(Long id) {
        return empleadoDAO.findById(id).orElse(null);
    }

    //DELETE
    @Transactional
    public void delete(Long id) {
        empleadoDAO.deleteById(id);
    }
    

public ByteArrayInputStream generarExcel() throws IOException {
    List<Empleados> empleados = empleadoDAO.findAll();
    HSSFWorkbook workbook = new HSSFWorkbook();
    HSSFSheet sheet = workbook.createSheet("Empleados Info");

    // Cargar y agregar imagen como logo
    ClassPathResource imgFile = new ClassPathResource("Static/img/Logo_gian.png");
    InputStream logoInputStream = imgFile.getInputStream();
    byte[] logoBytes = logoInputStream.readAllBytes();
    int pictureIndex = workbook.addPicture(logoBytes, HSSFWorkbook.PICTURE_TYPE_PNG);
    logoInputStream.close();

    HSSFPatriarch drawing = sheet.createDrawingPatriarch();
    HSSFClientAnchor anchor = new HSSFClientAnchor();
    anchor.setCol1(0);  // Columna donde inicia el logo
    anchor.setRow1(0);  // Fila donde inicia el logo
    anchor.setCol2(2);  // Columna hasta donde llega el logo
    anchor.setRow2(3);  // Fila hasta donde llega el logo
    drawing.createPicture(anchor, pictureIndex);

    // Estilos para el encabezado
    HSSFCellStyle headerStyle = workbook.createCellStyle();
    HSSFFont font = workbook.createFont();
    font.setBold(true); // Texto en negrita
    headerStyle.setFont(font);
    headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex()); // Fondo azul claro
    headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

    // Crear fila de encabezado debajo del logo
    HSSFRow headerRow = sheet.createRow(3);
    String[] headers = {"empleado_id", "nombre", "apellido", "dni", "cargo", "telefono", "correo_electronico"};
    for (int i = 0; i < headers.length; i++) {
        HSSFCell cell = headerRow.createCell(i);
        cell.setCellValue(headers[i]);
        cell.setCellStyle(headerStyle);
        sheet.autoSizeColumn(i); // Ajustar ancho de columna automáticamente
    }

    // Estilos para las filas de datos
    HSSFCellStyle dataStyle = workbook.createCellStyle();
    dataStyle.setWrapText(true); // Ajustar texto en celdas

    int dataRowIndex = 4;  // La primera fila de datos después del encabezado
    for (Empleados empleado : empleados) {
        HSSFRow dataRow = sheet.createRow(dataRowIndex++);
        dataRow.createCell(0).setCellValue(empleado.getEmpleado_id());
        dataRow.createCell(1).setCellValue(empleado.getNombre());
        dataRow.createCell(2).setCellValue(empleado.getApellido());
        dataRow.createCell(3).setCellValue(empleado.getDni());
        dataRow.createCell(4).setCellValue(empleado.getCargo());
        dataRow.createCell(5).setCellValue(empleado.getTelefono());
        dataRow.createCell(6).setCellValue(empleado.getCorreo_electronico());

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

