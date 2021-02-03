package ro.softica.iWantPS5;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
@Scope
public class EndlessWorker extends Thread {
    DiscordWebhook webhook;

    public EndlessWorker() {
        webhook = new DiscordWebhook("https://discord.com/api/webhooks/806268134977306624/UR5IdX4WBVCb4re4t1aqUmRirLm88M67LGA5-TB2pDW5tHOAkII96e2jw5dGsRWJgYYP");
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            System.out.println(LocalDateTime.now() + " - Searching...");

            try {
                detectPS5OnEmag();
                detectPS5OnOrange();
                detectPS5OnAltex();
                detectPS5OnMediaGalaxy();
            } catch (Throwable t) {
                t.printStackTrace();
            }

            System.out.println(LocalDateTime.now() + " - None found! Sleeping...");
            sleep(60000);
        }
    }

    private void detectPS5OnEmag() throws IOException {
        String link = "https://www.emag.ro/consola-playstation-5-so-9396406/pd/DNKW72MBM/";
        Document doc = Jsoup.connect(link)
                .userAgent("Opera")
                .get();
        Elements addToCartButtons = doc.select(".yeahIWantThisProduct");

        if (!addToCartButtons.isEmpty()) {
            yellPS5Found(link, doc, "@everyone PS5 GASIT PE EMAG!!!", "PS5 gasit pe EMAG");
            yellPS5Found(link, doc, "@everyone PS5 GASIT PE EMAG!!!", "PS5 gasit pe EMAG");
            yellPS5Found(link, doc, "@everyone PS5 GASIT PE EMAG!!!", "PS5 gasit pe EMAG");
        }
    }

    private void detectPS5OnOrange() throws IOException {
        String link = "https://www.orange.ro/magazin-online/obiecte-conectate/consola-playstation-5";
        Document doc = Jsoup.connect(link)
                .userAgent("Opera")
                .get();
        Elements addToCartButtons = doc.select("#productprice");

        if (!addToCartButtons.isEmpty()) {
            yellPS5Found(link, doc, "@everyone PS5 GASIT PE ORANGE!!!", "PS5 gasit pe ORANGE");
            yellPS5Found(link, doc, "@everyone PS5 GASIT PE ORANGE!!!", "PS5 gasit pe ORANGE");
            yellPS5Found(link, doc, "@everyone PS5 GASIT PE ORANGE!!!", "PS5 gasit pe ORANGE");
        }
    }

    private void detectPS5OnAltex() throws IOException {
        String link = "https://altex.ro/console-ps5/cpl/";
        Document doc = Jsoup.connect(link)
                .userAgent("Opera")
                .get();;
        Elements addToCartButtons = doc.select("div.text-alertYellow");

        if (addToCartButtons.isEmpty()) {
             yellPS5Found(link, doc, "@everyone PS5 GASIT PE ALTEX!!!", "PS5 gasit pe ALTEX");
             yellPS5Found(link, doc, "@everyone PS5 GASIT PE ALTEX!!!", "PS5 gasit pe ALTEX");
             yellPS5Found(link, doc, "@everyone PS5 GASIT PE ALTEX!!!", "PS5 gasit pe ALTEX");
        }
    }

    private void detectPS5OnMediaGalaxy() throws IOException {
        String link = "https://mediagalaxy.ro/console-ps5/cpl/";
        Document doc = Jsoup.connect(link)
                .userAgent("Opera")
                .get();;
        Elements addToCartButtons = doc.select("div.text-alertYellow");

        if (addToCartButtons.isEmpty()) {
             yellPS5Found(link, doc, "@everyone PS5 GASIT PE MEDIA GALAXY!!!", "PS5 gasit pe MEDIA GALAXY");
             yellPS5Found(link, doc, "@everyone PS5 GASIT PE MEDIA GALAXY!!!", "PS5 gasit pe MEDIA GALAXY");
             yellPS5Found(link, doc, "@everyone PS5 GASIT PE MEDIA GALAXY!!!", "PS5 gasit pe MEDIA GALAXY");
        }
    }

    private void yellPS5Found(String link, Document doc, String s, String s2) throws IOException {
        webhook.setContent(s);
        webhook.setAvatarUrl("https://s13emagst.akamaized.net/products/32502/32501277/images/res_8964c0a2056c6f1fc96531e6f5721fb2.jpg");
        webhook.setUsername("iWantPS5");
        webhook.setTts(false);
        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setTitle(s2)
                .setDescription(link)
                .setColor(Color.RED)
                .addField("Produs", doc.title(), false)
                .addField("Link", link, false)
                .setImage("https://s13emagst.akamaized.net/products/32502/32501277/images/res_8964c0a2056c6f1fc96531e6f5721fb2.jpg")
                .setUrl(link));
        webhook.execute();
        System.out.println(s2);
        System.out.println(s2);
        System.out.println(s2);
        System.out.println(s2);
        System.out.println(s2);
        System.out.println(s2);
        System.out.println(s2);
        System.out.println(s2);
        System.out.println(s2);
    }


    private void testEmag() throws IOException {
        String link = "https://www.emag.ro/telefon-mobil-samsung-galaxy-s21-dual-sim-128gb-8gb-ram-5g-phantom-grey-sm-g991bzadeue/pd/DPLGTDMBM/";
        Document doc = Jsoup.connect(link).get();
        Elements addToCartButtons = doc.select(".yeahIWantThisProduct");

        if (addToCartButtons.isEmpty()) {
            webhook.setContent("@AcerX Crawler-ul nu mai merge pe EMAG!");
            webhook.setAvatarUrl("https://edsurge.imgix.net/uploads/post/image/10801/shutterstock_493677196-1519849569.jpg");
            webhook.setUsername("iWantPS5");
            webhook.setTts(false);
            webhook.execute();
        }
    }

    private void testOrange() throws IOException {
        String link = "https://www.orange.ro/magazin-online/obiecte-conectate/controller-fara-fir-dualsense-ps5";
        Document doc = Jsoup.connect(link).get();
        Elements addToCartButtons = doc.select("#productprice");

        if (addToCartButtons.isEmpty()) {
            webhook.setContent("@AcerX Crawler-ul nu mai merge pe Orange!");
            webhook.setAvatarUrl("https://edsurge.imgix.net/uploads/post/image/10801/shutterstock_493677196-1519849569.jpg");
            webhook.setUsername("iWantPS5");
            webhook.setTts(false);
            webhook.execute();
        }
    }
}
