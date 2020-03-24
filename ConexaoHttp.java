import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


class ConexaoHttp{    
    public static String sendGet() throws Exception {

        String url = "https://api.codenation.dev/v1/challenge/dev-ps/generate-data?token=6ee96b479da667e5e678cb30ee985eadcd980d79";

        HttpURLConnection httpClient =
                (HttpURLConnection) new URL(url).openConnection();

        // optional default is GET
        httpClient.setRequestMethod("GET");

        //add request header
        httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(httpClient.getInputStream()))) {

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line);
            }

            //print result
            return response.toString();

        }

    }
    public static void sendPost() throws Exception {

        String charset = "UTF-8";
        File uploadFile1 = new File("./arquivo/answer.json");
        String requestURL = "https://api.codenation.dev/v1/challenge/dev-ps/submit-solution?token=6ee96b479da667e5e678cb30ee985eadcd980d79";
 
        try {
            MultipartUtility multipart = new MultipartUtility(requestURL, charset);
             
            multipart.addHeaderField("User-Agent", "CodeJava");
            multipart.addHeaderField("Test-Header", "Header-Value");
             
            multipart.addFormField("description", "Codenation");
            multipart.addFormField("keywords", "Desafio de César");
             
            multipart.addFilePart("fileUpload", uploadFile1);
 
            List<String> response = multipart.finish();
             
            System.out.println("SERVER REPLIED:");
             
            for (String line : response) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }

    }
}