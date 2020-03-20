import java.io.BufferedWriter;
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
    public static void setDescriptografado(String decifrado) throws Exception{
        JSONObject json = new JSONObject(ConexaoHttp.sendGet());
        json.put("decifrado",decifrado);
        BufferedWriter escrever = new BufferedWriter(new FileWriter("./arquivo/answer.json"));
        escrever.append(json.toString());
        escrever.close();
    }
    public static String getJson() throws Exception {
        JSONObject json = new JSONObject(new FileWriter("./arquivo/answer.json"));
        return json.toString();
    }
}