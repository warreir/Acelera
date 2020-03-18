import org.json.JSONObject;

class TratarJson{    
    public static int getChave() throws Exception {
        // trata o json pra que  seja enviado a chave e o texto criptografado.
        JSONObject json = new JSONObject(ConexaoHttp.sendGet());
        return json.getInt("numero_casas");
    }
    public static String getCifrado() throws Exception {
        JSONObject json = new JSONObject(ConexaoHttp.sendGet());
        return json.getString("cifrado");
    }
}