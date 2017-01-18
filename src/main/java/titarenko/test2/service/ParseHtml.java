package titarenko.test2.service;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import titarenko.test2.domain.BookMaker;
import titarenko.test2.enums.BookMakerStarEnum;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyMac on 17.01.17.
 */

@Service
public class ParseHtml {

    @Autowired
    private BookMakerSerice bookMakerSerice;


    public void saveHtmlBookmaker() throws IOException {
        List<BookMaker> old = bookMakerSerice.getAll();
        List<BookMaker> newB = new ArrayList<>();
        String fromHtmlComputer = getFromHtmlComputer("/Users/MyMac/Desktop/BETS/bookmakers.html");
        Document doc = Jsoup.parse(fromHtmlComputer);
        Element select = doc.getElementsByAttributeValue("class", "table table-striped table-hover").get(1);
        Element tbody = select.getElementsByTag("tbody").first();
        Elements trs = tbody.getElementsByTag("tr");
        for (int i = 0; i < trs.size(); i++) {
            Element tr = trs.get(i);
            Elements children = tr.children();
            String name = children.get(0).text();
            String text2 = children.get(4).child(0).attr("class");
            String text3 = children.get(5).child(0).attr("class");
            String text4 = children.get(6).child(0).attr("class");
            String text5 = children.get(7).child(0).attr("class");
            for (BookMaker book : old) {
                if(book.getName().equals(name)){
                    book.setReliability(getStarCorrect(text2));
                    book.setCutting(getStarCorrect(text3));
                    book.setAmountArbs(getStarCorrect(text4));
                    book.setCoefficients(getStarCorrect(text5));
                    newB.add(book);
                }
            }
        }
        bookMakerSerice.save(newB);
    }

    public String getStarCorrect(String text){
        if(text.contains("1")){
            return BookMakerStarEnum.one.toString();
        }
        if(text.contains("2")){
            return BookMakerStarEnum.two.toString();
        }
        if(text.contains("3")){
            return BookMakerStarEnum.three.toString();
        }
        if(text.contains("4")){
            return BookMakerStarEnum.four.toString();
        }
        if(text.contains("5")){
            return BookMakerStarEnum.five.toString();
        }
        return null;
    }

    public String getFromHtmlComputer(String path) throws IOException {
        InputStream is = new FileInputStream(path);
        return new String(IOUtils.toByteArray(is), "UTF-8");
    }
}
