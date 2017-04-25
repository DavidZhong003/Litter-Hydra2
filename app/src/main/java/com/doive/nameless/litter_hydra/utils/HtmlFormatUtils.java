package com.doive.nameless.litter_hydra.utils;

import android.support.annotation.NonNull;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import static android.R.string.no;

public class HtmlFormatUtils {

    public static String htmlImageMatchingScreen( String htmlText){
        if (htmlText==null){
            return null;
        }
        Document doc      = Jsoup.parse(htmlText);
        Elements elements =doc.getElementsByTag("img");
        for (Element element : elements) {
            element.attr("width","100%").attr("height","auto");
        }

        return doc.toString();
    }
}