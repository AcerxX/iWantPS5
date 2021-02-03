package ro.softica.iWantPS5;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;

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
//        webhook.setContent("Alerta PS5 pornita!");
//        webhook.setAvatarUrl("https://edsurge.imgix.net/uploads/post/image/10801/shutterstock_493677196-1519849569.jpg");
//        webhook.setUsername("iWantPS5");
//        webhook.setTts(false);
//        try {
//            webhook.execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        while (true) {
            System.out.println("Searching...");

            testEmag();
            detectPS5OnEmag();

            testOrange();
            detectPS5OnOrange();

            System.out.println("All good! Sleeping...");
            sleep(30000);
        }
    }

    private void detectPS5OnEmag() throws IOException {
        String link = "https://www.emag.ro/consola-playstation-5-so-9396406/pd/DNKW72MBM/";
        Document doc = Jsoup.connect(link).get();
        Elements addToCartButtons = doc.select(".yeahIWantThisProduct");

        if (!addToCartButtons.isEmpty()) {
            yellPS5Found(link, doc, "@everyone PS5 GASIT PE EMAG!!!", "PS5 gasit pe EMAG");
            yellPS5Found(link, doc, "@everyone PS5 GASIT PE EMAG!!!", "PS5 gasit pe EMAG");
            yellPS5Found(link, doc, "@everyone PS5 GASIT PE EMAG!!!", "PS5 gasit pe EMAG");
        }
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

    private void detectPS5OnOrange() throws IOException {
        String link = "https://www.orange.ro/magazin-online/obiecte-conectate/consola-playstation-5";
        Document doc = Jsoup.connect(link).get();
        Elements addToCartButtons = doc.select("#productprice");

        if (!addToCartButtons.isEmpty()) {
            yellPS5Found(link, doc, "@everyone PS5 GASIT PE ORANGE!!!", "PS5 gasit pe ORANGE");
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
    }
}
