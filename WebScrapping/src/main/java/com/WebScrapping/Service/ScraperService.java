package com.WebScrapping.Service;

import com.WebScrapping.model.ScrapeResponce;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScraperService {
    public ScrapeResponce WebScrape(String url) throws IOException {
        Document doc = Jsoup.connect(url)
                .userAgent("Chrome/154.0.8037.58")
                .timeout(5000)
                .get();

        List<String> images=new ArrayList<>();
        List<String> videos=new ArrayList<>();
        List<String> links=new ArrayList<>();

        for(Element img:doc.select("img")){
            String src= img.absUrl("src");
            if(!src.isEmpty()){
                images.add((src));
            }
        }
        for(Element video:doc.select("video")){
            String src= video.absUrl("src");
            if(!src.isEmpty()){
                videos.add((src));
            }
        }
        for(Element link:doc.select("a[herf]")){
            String herf= link.absUrl("herf");
            if(!herf.isEmpty()){
                links.add((herf));
            }
        }
        ScrapeResponce responce  = new ScrapeResponce();
        responce.setImages(images);
        responce.setVideos(videos);
        responce.setLinks(links);
        return responce;
    }
}
