///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package org.sheba.urlchecker;
//
//import java.io.IOException;
//import org.jsoup.Connection;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//
///**
// *
// * @author kabir
// */
//public class WebPageParser {
//    public boolean isValidURL(String url) throws IOException{
//        Connection.Response response = Jsoup.connect(url).followRedirects(true).execute();
//        System.out.println(response.body());
////        System.out.println(document.head().text());
////        String metaURL = 
////              document.head().select("meta[property=og:url]").get(0)
////              .attr("content");
////        return url.equals(metaURL);
//        return true;
//    }
////    
////    public static void main(String[] args) throws IOException {
////        WebPageParser webPageParser = new WebPageParser();
////        webPageParser.isValidURL("https://www.sheba.xyz/home-pest-control");
////        
////    }
//}
