import java.math.BigInteger;
import java.security.SecureRandom;

public class RSAalgorithm{
    private BigInteger p,q,n,phi,e,d;
    private SecureRandom r;
    
    public RSAalgorithm(){
        r = new SecureRandom();

        p = new BigInteger(512, 100, r);
        q = new BigInteger(512, 100, r);
        
        System.out.println("Prime Numbers:-");
        System.out.println("p : "+p.intValue());
        System.out.println("q : "+q.intValue());

        n = p.multiply(q);
        phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        e = new BigInteger("2");
        while (phi.gcd(e).intValue()>1 || e.compareTo(phi)>=0){
            e = e.add(BigInteger.ONE);
        }

        d = e.modInverse(phi);
        System.out.println("Public key :("+n.intValue()+","+e.intValue()+")");
        System.out.println("Private key :("+n.intValue()+","+d.intValue()+")");
    }
    public BigInteger Encrypt(BigInteger msg){
        return msg.modPow(e,n);
    }
    public BigInteger Decrypt(BigInteger Cipher){
        return Cipher.modPow(d,n);
    }
    public static void main(String[] args){
        RSAalgorithm rsa = new RSAalgorithm();
        BigInteger msg = new BigInteger("15");
        BigInteger ET = rsa.Encrypt(msg);
        BigInteger DT = rsa.Decrypt(ET);

        System.out.println("Original msg : "+msg);
        System.out.println("Encrypted msg : "+ET);
        System.out.println("Decrypted msg : "+DT);
    }
}