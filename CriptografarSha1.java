import java.math.BigInteger;
import java.security.MessageDigest;

class CriptografarSha1 {
    public static String criptografar(String textoDescriptografado) {
        try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
	        digest.reset();
	        digest.update(textoDescriptografado.getBytes("utf8"));
            String texto = String.format("%040x", new BigInteger(1, digest.digest()));
            return texto;
		} catch (Exception e){
			e.printStackTrace();
		}
        return "";
    }
}