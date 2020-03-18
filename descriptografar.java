import javax.net.ssl.HttpsURLConnection;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class descriptografar{    
    public static String decriptar(final int chave, final String textoCifrado) {
        final StringBuilder texto = new StringBuilder();
        final int tamanhoTexto = textoCifrado.length();
        for (int c = 0; c < tamanhoTexto; c++) {
           int letraDecifradaASCII = ((int) textoCifrado.charAt(c)) - chave;
            while (letraDecifradaASCII < 97) {
                letraDecifradaASCII += 26;
            }
            texto.append((char) letraDecifradaASCII);
            //System.out.println(letraDecifradaASCII);
        }
        return texto.toString();
    }
    public static void main(final String[] args) {
        //pegar o json do get
        String json ="";
        String textoCifrado = "";
        int chave = 0;
        try{
            json = sendGet();
        }catch(Exception e){
            System.out.println(e);
        }

        //quebrar em arrays
        final String[] splitVirgulas =  json.split(",");

        //pegar o array quebrado e quebrar de novo em 2 para que consiga pegar o value
        //depois fazer com que eja criado um map para que o primeiro valor seja o identificador do map
        int aux = 0;
        for(String splitVirgula : splitVirgulas) {
            final String[] splitDoisPontos = splitVirgula.split(":",2);
            if(aux == 0){
                chave = Integer.parseInt(splitDoisPontos[1]);
            }else if(aux==2){
                textoCifrado = splitDoisPontos[1];
            }
            aux++;
        }
        String[] a = textoCifrado.split(" ");
        String textoDescriptografado = "";
        aux=0;
        for(final String texto: a){
            aux++;
            final String portugues = decriptar(chave, texto);
            if(aux==1){
                textoDescriptografado = textoDescriptografado + portugues+": ";
            }else if(aux==13){
                textoDescriptografado = textoDescriptografado + portugues+". ";
            }else{
                textoDescriptografado = textoDescriptografado + portugues+" ";
            }
            
        }
        System.out.println("\n"+textoCifrado);
        //System.out.println("\n"+certo);
        System.out.println("\n"+textoDescriptografado);
    }
    private static String sendGet() throws Exception {

        String url = "https://api.codenation.dev/v1/challenge/dev-ps/generate-data?token=6ee96b479da667e5e678cb30ee985eadcd980d79";

        HttpURLConnection httpClient =
                (HttpURLConnection) new URL(url).openConnection();

        // optional default is GET
        httpClient.setRequestMethod("GET");

        //add request header
        httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = httpClient.getResponseCode();

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

    private void sendPost() throws Exception {

		// url is missing?
        //String url = "https://selfsolve.apple.com/wcResults.do";
        String url = "https://httpbin.org/post";

        HttpsURLConnection httpClient = (HttpsURLConnection) new URL(url).openConnection();

        //add reuqest header
        httpClient.setRequestMethod("POST");
        httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
        httpClient.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

        // Send post request
        httpClient.setDoOutput(true);
        try (DataOutputStream wr = new DataOutputStream(httpClient.getOutputStream())) {
            wr.writeBytes(urlParameters);
            wr.flush();
        }

        int responseCode = httpClient.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(httpClient.getInputStream()))) {

            String line;
            StringBuilder response = new StringBuilder();

            while ((line = in.readLine()) != null) {
                response.append(line);
            }

            //print result
            System.out.println(response.toString());

        }

    }






}