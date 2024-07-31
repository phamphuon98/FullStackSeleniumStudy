package selenium;

import java.util.Scanner;

public class DemoApplication {

    public static void main(String[] args) {

        while (true) {
            Scanner scanner = new Scanner(System.in); // Create a Scanner object
            System.out.println("Enter privateKey: ");
            String privateKey = scanner.nextLine(); // Read user input
            System.out.println("Private Key is: " + privateKey);
            System.out.println("==================================");
            System.out.println();
            System.out.println("Enter text: ");// Output user input
            String data = scanner.nextLine(); // Read user input
            System.out.println("Text is: " + privateKey); // Output user input
            System.out.println("==================================");
            System.out.println();
            try {
                // String privateKey =
                // "MEECAQAwEwYHKoZIzj0CAQYIKoZIzj0DAQcEJzAlAgEBBCDpUMTnrVBDcGxDh7cd2xcevqaE/qvO7PqKuh02+EjaNA==";
                // String data = "515c3bdb-ba9d-336b-9124-fbae85fe3274";
                String signedData = KeySecurity.signData(data, privateKey);
                System.out.println("Signature: ");
                System.out.println(signedData);
                System.out.println("=====================================================");
                System.out.println("=====================================================");
                System.out.println("=====================================================");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
