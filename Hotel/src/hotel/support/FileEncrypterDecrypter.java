package hotel.support;

import hotel.controller.LoggerController;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;
import org.jasypt.util.text.BasicTextEncryptor;

public class FileEncrypterDecrypter {

    public static void encrypt(String client, String license, String expiration_date) throws InvalidKeyException, IOException {
        new File("user.properties").delete();
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("hotel");

        String clientEncrypt = textEncryptor.encrypt(client);
        String licenseEncrypt = textEncryptor.encrypt(license);
        String expiration_dateEncrypt = textEncryptor.encrypt(expiration_date);

        System.out.println(clientEncrypt);
        System.out.println(licenseEncrypt);
        System.out.println(expiration_dateEncrypt);

        File userProperties = new File("user.properties");
        if(!userProperties.exists()) {
            userProperties.createNewFile();
        }
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(userProperties));
            FileOutputStream fos = new FileOutputStream(userProperties);
            fos.write(("client=ENC(" + clientEncrypt + ")\n").getBytes());
            fos.write(("license=ENC(" + licenseEncrypt + ")\n").getBytes());
            fos.write(("expiration_date=ENC(" + expiration_dateEncrypt + ")\n").getBytes());

            fos.flush();
            fos.close();
        } catch (FileNotFoundException ex) {
            LoggerController.log(FileEncrypterDecrypter.class, ex);
        } catch (IOException ex) {
            LoggerController.log(FileEncrypterDecrypter.class, ex);
        }
    }

    public static String[] decrypt() throws InvalidAlgorithmParameterException, InvalidKeyException, IOException {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("hotel");
        
        Properties props = new EncryptableProperties(encryptor);
        try {
            props.load(new FileInputStream("user.properties"));
        } catch (FileNotFoundException ex) {
            LoggerController.log(FileEncrypterDecrypter.class, ex);
        } catch (IOException ex) {
            LoggerController.log(FileEncrypterDecrypter.class, ex);
        }
        
        String client = props.getProperty("client");
        String license = props.getProperty("license");
        String expiration_date = props.getProperty("expiration_date");
        
        System.out.println(client);
        System.out.println(license);
        System.out.println(expiration_date);
        
        String[] array = new String[] {client, license, expiration_date};
        return array;
    }

    public static boolean writeUserProperties(String client, String license, String expiration_date) {
        return false;
    }

    public static boolean readUserProperties() {
        return false;
    }
}
