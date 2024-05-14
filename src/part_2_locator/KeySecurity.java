package part_2_locator;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@SuppressWarnings({ "java:S112", "java:S1118" })
public class KeySecurity {
    public static final String ALGORITHM = "SHA256withECDSA"; // Updated to use ECDSA for EC keys

    // Generate a key pair using Elliptic Curve Cryptography
    public static Key generateKeyPair() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        keyGen.initialize(256); // Use an appropriate key size for the EC curve

        KeyPair keyPair = keyGen.genKeyPair();

        // Convert keys to strings
        String publicKeyString = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
        String privateKeyString = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());

        // Return the key pair as a Key object
        return new Key(privateKeyString, publicKeyString);
    }

    @SuppressWarnings({ "fb-contrib:MDM_STRING_BYTES_ENCODING" })
    // Sign data using the private key
    public static String signData(String data, String privateKeyString) throws Exception {
        // Decode the private key from Base64
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyString);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        // Sign the data
        Signature signature = Signature.getInstance(ALGORITHM);
        signature.initSign(privateKey);
        signature.update(data.getBytes());
        byte[] signedData = signature.sign();

        // Return the signed data as a Base64-encoded string
        return Base64.getEncoder().encodeToString(signedData);
    }

    @SuppressWarnings({ "fb-contrib:MDM_STRING_BYTES_ENCODING" })
    // Verify the signature using the public key
    public static boolean verifySignature(String data, String signatureString, String publicKeyString)
            throws Exception {
        // Decode the public key from Base64
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyString);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        // Decode the signature
        byte[] signatureBytes = Base64.getDecoder().decode(signatureString);

        // Verify the signature
        Signature verifier = Signature.getInstance(ALGORITHM);
        verifier.initVerify(publicKey);
        verifier.update(data.getBytes());
        return verifier.verify(signatureBytes);
    }

    @SuppressWarnings({ "java:S6206" })
    // Inner Key class to hold private and public keys as strings
    public static final class Key {
        private final String privateKey;
        private final String publicKey;

        public Key(String privateKey, String publicKey) {
            this.privateKey = privateKey;
            this.publicKey = publicKey;
        }

        public String getPrivateKey() {
            return privateKey;
        }

        public String getPublicKey() {
            return publicKey;
        }
    }
}

