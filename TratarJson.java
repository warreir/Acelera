import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.JSONObject;

class TratarJson{    
    public static int getChave() throws Exception {
        JSONObject json = new JSONObject(ConexaoHttp.sendGet());
        return json.getInt("numero_casas");
    }
    public static String getCifrado() throws Exception {
        JSONObject json = new JSONObject(ConexaoHttp.sendGet());
        return json.getString("cifrado");
    }
    public static void setDescriptografado(String decifrado, String resumo) throws Exception{
        JSONObject json = new JSONObject(ConexaoHttp.sendGet());
        json.put("resumo_criptografico",resumo);
        json.put("decifrado",decifrado);
        BufferedWriter escrever = new BufferedWriter(new FileWriter("./arquivo/answer.json"));
        escrever.append(json.toString());
        escrever.close();
    }
    public static String getJson() throws Exception {
        BufferedReader leitura = new BufferedReader(new FileReader("./arquivo/answer.json"));
        JSONObject json = new JSONObject(leitura.readLine());
        return json.toString();
    }
}