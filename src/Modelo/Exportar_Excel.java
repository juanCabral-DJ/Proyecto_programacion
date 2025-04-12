package Modelo;

import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class Exportar_Excel<T>{

	    public abstract void exportarAExcel(ArrayList<T> T, String rutaArchivo);

	}

