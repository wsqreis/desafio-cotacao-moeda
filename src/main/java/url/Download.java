package url;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Download implements Runnable{

    String link;
    File out;

    public Download(String link, File out){
        this.link = link;
        this.out = out;
    }

    public void run(){
        try{
            if (exists(link)) {
                URL url = new URL(link);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                double fileSize = (double) http.getContentLengthLong();
                BufferedInputStream in = new BufferedInputStream(http.getInputStream());
                FileOutputStream fos = new FileOutputStream(this.out);
                BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
                byte[] buffer = new byte[1024];
                double downloaded = 0.00;
                int read;
                double percentDownloaded;

                while ((read = in.read(buffer, 0, 1024)) >= 0) {
                    bout.write(buffer, 0, read);
                    downloaded += read;
                    percentDownloaded = (downloaded * 100) / fileSize;
                    String percent = String.format("%.4f", percentDownloaded);
                    //System.out.println("Downloaded " + percent + "% of the file");
                }

                bout.close();
                in.close();
                //System.out.println("Download complete");
            }
        }
        catch (IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public static boolean exists(String URLName){
        try {
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection con =
                    (HttpURLConnection) new URL(URLName).openConnection();
            con.setRequestMethod("HEAD");
            return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        }
        catch (Exception e) {
            return false;
        }
    }

}
