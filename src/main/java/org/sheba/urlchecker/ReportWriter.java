/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.sheba.urlchecker;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author kabir
 */
public class ReportWriter {
    private static ReportWriter instance;
    private FileWriter writer;

    public ReportWriter() {
        try {
            writer = new FileWriter("report.txt");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static ReportWriter getInstance(){
        if(instance == null){
            instance = new ReportWriter();
        }
        return instance;
    }
    
    
    public void writeLine(String line){
        try {
            writer.write(line + "\n");
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void close() throws IOException{
        writer.close();
    }
}
