/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nirho.util;

import java.math.BigInteger;
import java.util.List;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.XWPFAbstractNum;
import org.apache.poi.xwpf.usermodel.XWPFChart;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFNumbering;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSimpleField;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STNumberFormat;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;

/**
 *
 * @author Alfredo
 */
public class ReporteUtil {
    
    public static void agregarLista(XWPFDocument document, List<String> lista){

        CTAbstractNum cTAbstractNum = CTAbstractNum.Factory.newInstance();
        
        CTLvl cTLvl = cTAbstractNum.addNewLvl();
        cTLvl.addNewNumFmt().setVal(STNumberFormat.BULLET);
        cTLvl.addNewLvlText().setVal("•");
        
        XWPFAbstractNum abstractNum = new XWPFAbstractNum(cTAbstractNum);
        XWPFNumbering numbering = document.createNumbering();
        BigInteger abstractNumID = numbering.addAbstractNum(abstractNum);
        BigInteger numID = numbering.addNum(abstractNumID);

        for (String string : lista) {
              XWPFParagraph p = document.createParagraph();
              p.setStyle("ParrafoNirho");
              p.setNumID(numID);
              XWPFRun lnewRun = p.createRun();
              lnewRun.setText(string); 
        }
        agregarSeparadorEnBlanco(document);
    }
    
    public static XWPFTable getTablaPorTitulo(XWPFDocument document, String title){
        for(XWPFTable t :document.getTables()){            
            String tableXML = t.getCTTbl().toString();
            String[] xml = tableXML.split(System.lineSeparator());
            for (String x : xml){
                if (x.contains("w:tblCaption")){
                    String caption = x.split("w:val=")[1].replace("/>", "");
                    caption = caption.replace("\"", "");
                    if(caption.equals(title)){
                        return t;
                    }
                }
            }
        }
        return null;
    }

    
    public static int getNumeroColumnasTabla(XWPFTable table){
        XWPFTableRow lastRow = table.getRows().get(table.getNumberOfRows() - 1);
        return lastRow.getTableICells().size(); 
    }
    
    public static XWPFChart getGraficoPorTitulo(XWPFDocument document, String titulo){
        XWPFChart chart = null;
        for (POIXMLDocumentPart part : document.getRelations()) {
            if (part instanceof XWPFChart) {
                chart = (XWPFChart) part;  
                chart.getTitle().getBody().toString();
                if(chart.getTitle().getBody().toString().equals(titulo)){
                    return chart;
                }
            }
        }
        return chart;
    }
    
    public static void agregarTablaContenido(XWPFDocument document){
        XWPFParagraph p = document.createParagraph();
        CTP ctP = p.getCTP();
        CTSimpleField toc = ctP.addNewFldSimple();
        toc.setInstr("TOC \\h");
        toc.setDirty(STOnOff.TRUE);
    }
    
    public static void agregarParrafo(XWPFDocument document, String texto){
        XWPFParagraph para = document.createParagraph();
        XWPFRun run = para.createRun();
        para.setStyle("ParrafoNirho");
        run.setText(texto);
        run.addBreak();
    }
    
    public static void agregarTitulo1(XWPFDocument document, String texto){
        XWPFParagraph paragraph=document.createParagraph();
        paragraph.setStyle("Titulo1Nirho");
        XWPFRun run=paragraph.createRun();
        run.setText(texto);
        run.addBreak();
    }
    
    public static void agregarSeparadorEnBlanco(XWPFDocument document){
        XWPFParagraph paragraph=document.createParagraph();
        XWPFRun run=paragraph.createRun();
        run.setText("");
        run.addBreak();
    }
    
    public static void agregarTitulo2(XWPFDocument document, String texto){
        XWPFParagraph paragraph=document.createParagraph();
        paragraph.setStyle("Titulo2Nirho");
        XWPFRun run=paragraph.createRun();
        run.setText(texto);
        run.addBreak();
    }
    
    public static void agregarTitulo(XWPFDocument document, String texto){
        XWPFParagraph para = document.createParagraph();
        para.setStyle("TituloNormalNirho");
        XWPFRun run = para.createRun();
        run.setText(texto);
        run.addBreak();
    }
    
    public static void crearTablaTitle(XWPFDocument document, List<String> headers, List<List<String>> content){
       
        XWPFTable table = document.createTable(content.get(0).size() + 1, content.size() + 1);
        table.getCTTbl().addNewTblPr().addNewTblW().setW(BigInteger.valueOf(10000));

        if(headers != null && headers.size() > 0){
            XWPFTableRow tableRow = table.getRow(0);
            for(int i = 0 ; i < headers.size(); i++){              
                XWPFParagraph p1 = table.getRow(0).getCell(i).getParagraphs().get(0);
                p1.setAlignment(ParagraphAlignment.CENTER);
                p1.setVerticalAlignment(TextAlignment.CENTER);
                p1.setStyle("ParrafoNirho");
                XWPFRun r1 = p1.createRun();
                r1.setBold(true);
                r1.setText(headers.get(i));
                tableRow.getCell(i).getCTTc().addNewTcPr().addNewShd().setFill("b8c426");
            }
        }
        
        for(int i = 1 ; i <= content.size(); i++){
            XWPFTableRow tableRow = table.getRow(i);
            for(int j = 0 ; j < content.get(i - 1).size(); j++){
                tableRow.getCell(j).setText(content.get(i - 1).get(j));
            }
        }
           
    }
    
    public static void crearTablaFirstRowTitle(XWPFDocument document, List<String> headers, List<List<String>> content){
       
        int rows = content.size();
        int cols = content.get(0).size() + 1;
        
        XWPFTable table = document.createTable(rows, cols);
        table.getCTTbl().addNewTblPr().addNewTblW().setW(BigInteger.valueOf(10000));
        
        for(int i = 0 ; i < rows; i++){
            XWPFTableRow tableRow = table.getRow(i);
            XWPFParagraph p = tableRow.getCell(0).getParagraphs().get(0);
            tableRow.getCell(0).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(3500));
            p.setAlignment(ParagraphAlignment.CENTER);
            p.setVerticalAlignment(TextAlignment.CENTER);
            p.setStyle("ParrafoNirho");
            XWPFRun r = p.createRun();
            r.setBold(true);
            r.setText(headers.get(i));
            tableRow.getCell(0).getCTTc().addNewTcPr().addNewShd().setFill("b8c426");
        }
        
        for(int i = 0 ; i < rows; i++){
            XWPFTableRow tableRow = table.getRow(i);
            for(int j = 1 ; j < cols; j++){
                tableRow.getCell(j).setText(content.get(i).get(j - 1));
            }
        }

    }
    
    
    public static void crearTablaFuncion(XWPFDocument document, String funcion, List<String> headers, List<List<String>> content){
        
        int rows = content.size() + 2;
        int cols = content.get(0).size();
        
        XWPFTable table = document.createTable(rows, cols);
        table.setWidth(1800);

        mergeCellHorizontally(table, 0, 0, 1);
        mergeCellHorizontally(table, 0, 2, cols - 2);
        
        XWPFTableRow tableRowH = table.getRow(0);
        XWPFParagraph c1 = table.getRow(0).getCell(0).getParagraphs().get(0);
        c1.setAlignment(ParagraphAlignment.CENTER);
        c1.setVerticalAlignment(TextAlignment.CENTER);
        XWPFRun rc1 = c1.createRun();
        rc1.setBold(true);
        rc1.setText("Función");
        tableRowH.getCell(0).getCTTc().addNewTcPr().addNewShd().setFill("b8c426");
        
        XWPFParagraph x1 = table.getRow(0).getCell(2).getParagraphs().get(0);
        x1.setAlignment(ParagraphAlignment.CENTER);
        x1.setVerticalAlignment(TextAlignment.CENTER);
        XWPFRun rcx1 = x1.createRun();
        rcx1.setBold(true);
        rcx1.setText(funcion);

        if(headers != null && headers.size() > 0){
            XWPFTableRow row1 = table.getRow(1);
            for(int i = 0 ; i < headers.size(); i++){              
                XWPFParagraph p1 = table.getRow(1).getCell(i).getParagraphs().get(0);
                p1.setAlignment(ParagraphAlignment.CENTER);
                p1.setVerticalAlignment(TextAlignment.CENTER);
                XWPFRun r1 = p1.createRun();
                r1.setBold(true);
                r1.setText(headers.get(i));
                row1.getCell(i).getCTTc().addNewTcPr().addNewShd().setFill("b8c426");
            }
        }
        
        for(int i = 0 ; i < content.size(); i++){
            XWPFTableRow tableRow = table.getRow(i + 2);
            for(int j = 0 ; j < content.get(i).size(); j++){
                tableRow.getCell(j).setText(content.get(i).get(j));
            }
        }
            
    }
    
    
    public static void mergeCellHorizontally(XWPFTable table, int row, int fromCol, int toCol) {
        XWPFTableCell cell = table.getRow(row).getCell(fromCol);
        CTTcPr tcPr = cell.getCTTc().getTcPr();
        if (tcPr == null){
            cell.getCTTc().addNewTcPr();
            tcPr = cell.getCTTc().getTcPr();
        }
        if (tcPr.isSetGridSpan()) {
            tcPr.getGridSpan().setVal(BigInteger.valueOf(toCol-fromCol+1));
        } else {
            tcPr.addNewGridSpan().setVal(BigInteger.valueOf(toCol-fromCol+1));
        }
        for(int colIndex = toCol; colIndex > fromCol; colIndex--) {
            table.getRow(row).getCtRow().removeTc(colIndex);
        }
    }
        
    public static void reemplazarParrafo(XWPFDocument document, String claveParrafo, String nuevoTexto){
        for(XWPFParagraph p: document.getParagraphs()){
            for (XWPFRun r : p.getRuns()) {
                String text = r.text();
                if(text != null){
                    if(text.contains(claveParrafo)){
                        r.setText(nuevoTexto, 0);
                    }
                }
            }
        }
    }
    
    public static void nuevaPagina(XWPFDocument document){
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setPageBreak(true);
    }
}
