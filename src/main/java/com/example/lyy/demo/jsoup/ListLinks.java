package com.example.lyy.demo.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ListLinks {
    public static void main(String[] args) throws IOException {
        //将url赋值，初始化
        String url = "http://nanrenvip.pw/fanhaoku/49SSNI-82413eX.html";
        print("url is %s...", url);
        //获取页面内容
        Document doc = Jsoup.connect(url).get();
        //将a标签下的href元素取出
        Elements links = doc.select("a[href]");
        //将含src的元素取出 例如：src="s.gif"
        Elements media = doc.select("[src]");
        //将link标签下的href元素取出
        Elements imports = doc.select("link[href]");
        //输出含src的元素的个数
        print("\nMedia: (%d)", media.size());
        for (Element src : media) {
            //将tagname为img标签的取出，并输出
            if (src.tagName().equals("img"))
                //第一个为tagname,输出的为img标签；第二个为src等于号后面的参数值，为此处为url网址，第三个为图片宽，第四个为图片高，第五个为alt的参数值，此例为空
                print(" * %s: <%s> %sx%s (%s)",
                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
                        trim(src.attr("alt"), 20));
            else
                //若tagname不为img，则仅输出三项内容
                print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
        }
        //输出含link>href元素的个数
        print("\nImports: (%d)", imports.size());
        //循环每个link>href元素
        for (Element link : imports) {
            //第一个为tagname,输出的为link标签；第二个为href等于号后面的参数值，为此处为url网址;第三个为rel等号后面的参数值
            print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"), link.attr("rel"));
        }
        //输出含a>href元素的个数
        print("\nLinks: (%d)", links.size());
        for (Element link : links) {
            //第一个为tagname,输出的为a标签；第二个为href等于号后面的参数值，为此处为url网址;第三个为link标签含的文本值，若超过35个字符则，截取前35个字符
            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
        }
    }
    //重写print
    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }
    //重写trim
    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
}