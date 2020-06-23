package com.example.lyy.demo.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JsoupTest {


    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("http://nanrenvip.pw/s1s1s1/").get();
        String title = document.title();
        //获取html中的标题
        System.out.println("title :"+title);

        Elements links = document.select("a[href]");
        for (Element link : links){
            System.out.println("link : "+ link.attr("href"));
            System.out.println("text :"+ link.text());
        }
    }

}
