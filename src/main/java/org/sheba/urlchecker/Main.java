/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.sheba.urlchecker;

import java.util.ArrayList;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 *
 * @author kabir
 *
 * https://api.sheba.xyz/v3/categories/209/services?lat=23.7980791&lng=90.4048319
 * https://api.sheba.xyz/v3/services/1885?lat=23.7980791&lng=90.4048319
 */
public class Main {

//    public static String getFinalURL(String url) throws IOException {
//        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
//        HttpURLConnection.setFollowRedirects(true);
//        con.connect();
//        con.getInputStream();
//
//        if (con.getResponseCode() == HttpURLConnection.HTTP_MOVED_PERM || con.getResponseCode() == HttpURLConnection.HTTP_MOVED_TEMP) {
//            String redirectUrl = con.getHeaderField("Location");
//            return getFinalURL(redirectUrl);
//        }
//        return url;
//    }
    public static void main(String[] args) {
        try {

            Options options = new Options();
            Option config = Option.builder("f").longOpt("file")
                    .argName("file")
                    .hasArg()
                    .required(true)
                    .desc("set excel file").build();
            options.addOption(config);

            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);
//            WebPageParser webPageParser = new WebPageParser();

            if (cmd.hasOption("f")) {
                String fileName = cmd.getOptionValue("f");
                CSVFeedfileParser cSVFeedfileParser = new CSVFeedfileParser();
                ArrayList<LinkParam> urls = cSVFeedfileParser.parse(fileName);

//                ArrayList<String> urls = new ArrayList<>();
//                urls.add("https://www.sheba.xyz/home-pest-control");
                for (LinkParam url : urls) {
                    boolean valid = SeleniumDriverPage.getInstance().isValidURL(url.getWebLink());
                    if (!valid) {
                        ReportWriter.getInstance().writeLine("************" + url.getWebLink());
                    } else {
                        ReportWriter.getInstance().writeLine(url.getWebLink());
                    }
                    if (url.getAndroidLink().startsWith("zxing://host_sub-category_service_detail")) {
                        ServiceRequest serviceRequest = new ServiceRequest();
//                        String s = "zxing://host_sub-category_service_detail/?url=https://www.sheba.xyz/service-details/2?category_id=10";
                        String[] p = url.getAndroidLink().split("url=");
                        String[] val = p[1].split("\\?");
                        String[] val1 = val[0].split("/");
                        String st = val1[val1.length - 1];
                        Integer id = Integer.parseInt(st);
                        if (!serviceRequest.isValidService(id)) {
                            ReportWriter.getInstance().writeLine("Invalid: " + url.getAndroidLink());
                        } else {
                            ReportWriter.getInstance().writeLine(url.getAndroidLink());
                        }
                    } else if (url.getAndroidLink().startsWith("zxing://host_sub-category/")) {
                        ServiceRequest serviceRequest = new ServiceRequest();
//                        String s = "zxing://host_sub-category/?url=https://www.sheba.xyz/sub-category/10";
                        String[] p = url.getAndroidLink().split("url=");
                        String[] val = p[1].split("\\?");
                        String[] val1 = val[0].split("/");
                        String st = val1[val1.length - 1];
                        Integer id = Integer.parseInt(st);
                        if (!serviceRequest.isValidServiceCategory(id)) {
                            ReportWriter.getInstance().writeLine("Invalid: " + url.getAndroidLink());
                        } else {
                            ReportWriter.getInstance().writeLine(url.getAndroidLink());
                        }
                    }
//                   
                }
                ReportWriter.getInstance().close();
                SeleniumDriverPage.getInstance().close();

            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
