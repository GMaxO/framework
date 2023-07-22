package studies.multithreads.homeworkthree;

import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException{

        Random random = new Random();
        String[] texts = new String[100000];
        for (int i = 0; i < texts.length; i++)
            texts[i] = Methods.generateText("abc", 3 + random.nextInt(3));

        Thread palindrome = new Thread(() -> {
            for (String text : texts) {
                if (Methods.palindrome(text) && !Methods.identicalLetters(text)) {
                    Methods.incrementCounter(text.length());
                }
            }
        });
        palindrome.start();

        Thread identicalLetters = new Thread(() -> {
            for (String text : texts) {
                if (Methods.identicalLetters(text)) {
                    Methods.incrementCounter(text.length());
                }
            }
        });
        identicalLetters.start();

        Thread lettersInAscendingOrder = new Thread(() -> {
            for (String text : texts) {
                if (!Methods.identicalLetters(text) && Methods.lettersInAscendingOrder(text)) {
                    Methods.incrementCounter(text.length());
                }
            }
        });
        lettersInAscendingOrder.start();
        identicalLetters.join();
        lettersInAscendingOrder.join();
        palindrome.join();

        System.out.println("Красивых слов с длиной 3: " + Methods.counter3 + " шт");
        System.out.println("Красивых слов с длиной 4: " + Methods.counter4+ " шт");
        System.out.println("Красивых слов с длиной 5: " + Methods.counter5 + " шт");
    }
}