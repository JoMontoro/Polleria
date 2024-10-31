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
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
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
        HSSFSheet sheet = workbook.createSheet("Empleado Info");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("empleado_id");
        row.createCell(1).setCellValue("nombre");
        row.createCell(2).setCellValue("apellido");
         row.createCell(3).setCellValue("Dni");
        row.createCell(4).setCellValue("Cargo");
        row.createCell(5).setCellValue("telefono");
        row.createCell(6).setCellValue("CorreoElectronico");

        int dataRowIndex = 1;
        for (Empleados empleado : empleados) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(empleado.getEmpleado_id());
            dataRow.createCell(1).setCellValue(empleado.getNombre());
            dataRow.createCell(2).setCellValue(empleado.getApellido());
            dataRow.createCell(3).setCellValue(empleado.getDni());
            dataRow.createCell(4).setCellValue(empleado.getCargo());
            dataRow.createCell(5).setCellValue(empleado.getTelefono());
             dataRow.createCell(5).setCellValue(empleado.getCorreo_electronico());
            dataRowIndex ++;
        }
        ByteArrayOutputStream ops= new ByteArrayOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();
        return new ByteArrayInputStream(ops.toByteArray());
    }
}

