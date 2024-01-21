import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Oblig_2 {
    public static String ROT13(String S)
    {
        char[] C = S.toCharArray();
        for (int i = 0; i < S.length(); i++)
        {
            char c = C[i];
            if       (c >= 'a' && c <= 'm') c += 13;
            else if  (c >= 'A' && c <= 'M') c += 13;
            else if  (c >= 'n' && c <= 'z') c -= 13;
            else if  (c >= 'N' && c <= 'Z') c -= 13;
            C[i] = c;
        }
        return String.valueOf(C);
    }

    public static String krypter(String S){
        String kryptert_S = ROT13(S);
        Queue<String> queue = new LinkedList<>();
        Stack<String> stack = new Stack<>();
        StringBuilder ferdigKryptert = new StringBuilder();
        for (int i = 0; i < S.length()/2; i++){
            queue.add(String.valueOf(kryptert_S.charAt(i)));
        }
        for (int i = S.length()/2; i < S.length(); i++) {
            stack.push(String.valueOf(kryptert_S.charAt(i)));
        }
        for (int i = 0; i < S.length(); i++){
            ferdigKryptert.append(i % 2 == 0 ? stack.pop() : queue.poll());
        }
        return ferdigKryptert.toString();
    };

    public static String dekrypter(String S){
        StringBuilder dekryptert = new StringBuilder();
        Queue<String> queue = new LinkedList<>();
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < S.length(); i++){
            if (i % 2 == 0) stack.push(String.valueOf(S.charAt(i)));
            else queue.add(String.valueOf(S.charAt(i)));
        }
        for (int i = 0; i < S.length()/2; i++) {
            dekryptert.append(queue.poll());
        }
        for (int i = S.length()/2; i < S.length(); i++) {
            dekryptert.append(stack.pop());
        }

        return ROT13(dekryptert.toString());

    }

}

class Main {
    public static void main(String[] args) {
        Oblig_2 noe = new Oblig_2();
        Scanner scanner = new Scanner(System.in);
        /*Her så tester jeg at krypteringen og de krypteringen funker som forventet: */
        System.out.println(Oblig_2.krypter("ABCDEFGHKLMNOPQRSTUVWXYZ"));
        System.out.println(Oblig_2.dekrypter("MNLOKPJQIRHSGTFUEXDYCZBA"));
        /*Har også laget et lite testprogram som man kan benytte for å teste algoritmen: */
        System.out.println("Skriv det du ønsker å kryptere her: ");
        String S = scanner.nextLine();
        System.out.println(": " + Oblig_2.krypter(S));
        System.out.println("Skriv det du ønsker å dekryptere her: ");
        String K = scanner.nextLine();
        System.out.println(": " + Oblig_2.dekrypter(K));

    }
}