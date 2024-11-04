package com.example.integrador.Services;

import com.example.integrador.Entidades_Model.Clientes;
import com.example.integrador.Repositorio.ClientesDAO;

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

    // Estilos para el encabezado
    HSSFCellStyle headerStyle = workbook.createCellStyle();
    HSSFFont font = workbook.createFont();
    font.setBold(true); // Texto en negrita
    headerStyle.setFont(font);
    headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex()); // Fondo azul claro
    headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

    // Crear fila de encabezado
    HSSFRow headerRow = sheet.createRow(0);
    String[] headers = {"cliente_id", "nombre", "apellido", "Correo_Electronico", "telefono", "direccion"};
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
    for (Clientes cliente : clientes) {
        HSSFRow dataRow = sheet.createRow(dataRowIndex++);
        dataRow.createCell(0).setCellValue(cliente.getCliente_id());
        dataRow.createCell(1).setCellValue(cliente.getNombre());
        dataRow.createCell(2).setCellValue(cliente.getApellido());
        dataRow.createCell(3).setCellValue(cliente.getCorreo_Electronico());
        dataRow.createCell(4).setCellValue(cliente.getTelefono());
        dataRow.createCell(5).setCellValue(cliente.getDireccion());

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
