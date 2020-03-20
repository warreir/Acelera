import java.io.BufferedWriter;
import java.io.FileWriter;

class index{    
    public static void main(final String[] args) throws Exception {
        String textoCifrado = "";
        int chave = 0;
        try{
            //pega o retorno do metodo get e salva em um arquivo json.
            BufferedWriter escrever = new BufferedWriter(new FileWriter("./arquivo/answer.json"));
            escrever.append(ConexaoHttp.sendGet());
            escrever.close();
            //pega o retorno do metodo get e trata os dados em uma função e retorna valores especificos
            textoCifrado = TratarJson.getCifrado();
            chave =  TratarJson.getChave();
            
        }catch (Exception exception){
            System.out.println("Erro de exceção: " + exception.getMessage());
        }

        // melhorar para regex
        textoCifrado = textoCifrado.replace(".", "");
        textoCifrado = textoCifrado.replace(":", "");
        String[] a = textoCifrado.split(" ");
        String textoDescriptografado = "";
        int aux=0;

        //Juntar todas as palavras descriptografadas
        for(final String texto: a){
            aux++;
            final String portugues = Descriptografar.decriptar(chave, texto);
            if(aux==1){
                textoDescriptografado = textoDescriptografado + portugues+": ";
            }else if(aux==13){
                textoDescriptografado = textoDescriptografado + portugues+". ";
            }else{
                textoDescriptografado = textoDescriptografado + portugues+" ";
            }
        }
        textoDescriptografado = CriptografarSha1.criptografar(textoDescriptografado);
        TratarJson.setDescriptografado(textoDescriptografado);
        
        ConexaoHttp.sendPost();
    }
}