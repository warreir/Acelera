class Descriptografar{    
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
}