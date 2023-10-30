package org.iesbelen.anotaciones;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;


@aCredential(usuario = "usuario1",
        hashPasswd = "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918")
@aCredential(usuario = "usuario2",
        hashPasswd = "ac9689e2272427085e35b9d3e3e8bed88cb3434828b43b86fc0596cad4c6e270")
public class Login {

    private final Set<Credential> credentials;

    public Login() {
        this.credentials = new HashSet<>();
    }

    public boolean add(Credential credential) {
        return credentials.add(credential);
    }

    public boolean login(Credential credential) throws NoSuchAlgorithmException {
        return credentials.contains(credential);
    }

    public static Credential createCredentialBySystemIn() throws NoSuchAlgorithmException {
        Scanner sc = new Scanner(System.in);
        String name, pass;

        System.out.print("Introduzca el nombre de usuario: ");
        name = sc.nextLine();

        System.out.print("Introduzca la password: ");
        pass = sc.nextLine();

        return new Credential(name, hashPassword(pass));
    }

    private static Credential createCredentialByAnnotation(aCredential aCredential) {
        return new Credential(aCredential.usuario(), aCredential.hashPasswd());
    }

    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest;

        digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(
                password.getBytes(StandardCharsets.UTF_8));

        return bytesToHex(encodedhash);
    }

    private static String bytesToHex(byte[] byteHash) {

        StringBuilder hexString = new StringBuilder(2 * byteHash.length);
        for (int i = 0; i < byteHash.length; i++) {
            String hex = Integer.toHexString(0xff & byteHash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

    public static Login cargadorDeContexto() {
        Class<Login> loginClass = Login.class;
        Login newLogin = new Login();
        List<aCredential> aCredentials = Arrays.asList(loginClass.getAnnotationsByType(aCredential.class));

        aCredentials.stream()
                .map(Login::createCredentialByAnnotation)
                .forEach(newLogin::add);

        return newLogin;
    }
}
