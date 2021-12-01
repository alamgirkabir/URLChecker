/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.sheba.urlchecker;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author kabir
 */
public class CSVFeedfileParser {

    public ArrayList<LinkParam> parse(String fileName) throws IOException {
        ArrayList<LinkParam> urls = new ArrayList();
        try (Reader reader = Files.newBufferedReader(Paths.get(fileName));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);) {
            for (CSVRecord csvRecord : csvParser) {
                String link = csvRecord.get(7);
                String iosLink = csvRecord.get(13);
                String androidLink = csvRecord.get(16);
                
                
                if(link.equals("link")){
                    continue;
                }
                LinkParam linkParam = new LinkParam();
                linkParam.setWebLink(link);
                linkParam.setAndroidLink(androidLink);
                linkParam.setIosLink(iosLink);
                urls.add(linkParam);
            }
        }
        return urls;
    }

    public static void main(String[] args) throws IOException {
        String fileName = "/home/kabir/Downloads/combine_products.csv";
        CSVFeedfileParser cSVFeedfileParser = new CSVFeedfileParser();
        cSVFeedfileParser.parse(fileName);
    }
}
