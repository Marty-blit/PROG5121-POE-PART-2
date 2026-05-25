import java.util.*;

class Message {
    private final String messageID;
    private final int numMessagesSent;
    private String recipient;
    private String message;
    private String messageHash;

    public Message(String recipient, String message, int numMessagesSent) {
        this.messageID = String.valueOf(1000000 + new Random().nextInt(900000));
        this.recipient = recipient;
        this.message = message;
        this.numMessagesSent = numMessagesSent;
        this.messageHash = createMessageHash();
    }

    public boolean checkRecipientCell() {
        return recipient.length() <= 10 && recipient.startsWith("+");
    }

    public boolean checkMessageLength() {
        return message.length() <= 250;
    }

    public String createMessageHash() {
        String[] words = message.split(" ");
        String first = words[0].toUpperCase();
        String last = words[words.length - 1].toUpperCase();
        String idPart = messageID.substring(0, 2);
        return (idPart + ":" + numMessagesSent + ":" + first + last).toUpperCase();
    }

    @Override
    public String toString() {
        return "MessageID: " + messageID + "\nMessage Hash: " + messageHash 
                              + "\nRecipient: " + recipient + "\nMessage: " + message;
    }
}

public class QuickChat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Message> messages = new ArrayList<>();
        int totalSent = 0;
        boolean loggedIn = true; // assume login passed for simplicity

        System.out.println("Welcome to QuickChat.");

        System.out.print("How many messages do you want to send? ");
        int numMessages = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < numMessages; i++) {
            System.out.print("Enter recipient number: ");
            String recipient = sc.nextLine();

            System.out.print("Enter message: ");
            String msgText = sc.nextLine();

            Message msg = new Message(recipient, msgText, i);

            if (!msg.checkRecipientCell()) {
                System.out.println("Cell phone number is incorrectly formatted or does not contain an international code.");
                continue;
            }

            if (!msg.checkMessageLength()) {
                System.out.println("Please enter a message of less than 250 characters.");
                continue;
            }

            System.out.println("1. Send\n2. Disregard\n3. Store");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                messages.add(msg);
                totalSent++;
                System.out.println("Message successfully sent");
                System.out.println(msg);
            } else if (choice == 2) {
                System.out.println("Press 0 to delete the message");
            } else if (choice == 3) {
                System.out.println("Message successfully stored");
            }
        }

        System.out.println("Total messages sent: " + totalSent);
    }
}