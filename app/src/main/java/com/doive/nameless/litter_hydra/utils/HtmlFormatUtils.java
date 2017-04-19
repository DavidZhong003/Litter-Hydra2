package com.doive.nameless.litter_hydra.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlFormatUtils {

    public static String htmlImageMatchingScreen(String htmlText){

        Document doc      = Jsoup.parse(htmlText);
        Elements elements =doc.getElementsByTag("img");
        for (Element element : elements) {
            element.attr("width","100%").attr("height","auto");
        }

        return doc.toString();
    }
}