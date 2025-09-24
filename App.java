import java.util.Random;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String palavra = getPalavraAleatoria();

        System.out.println("Palavra sorteada: " + palavra);

        for(int i=0;i<6;i++){
            Boolean result = descobrePalavra(palavra);
            if(result) {
                System.out.println("Você acertou a palavra: "+palavra);
                break;
            }else {
                System.out.println("Errouuuu");
            }    
        }
        
        scanner.close();

    }

    public static Boolean descobrePalavra(String palavra) {
        System.out.println("Digite a palavra:");
        String palavraEscolhida = scanner.nextLine();
        if(palavraEscolhida.equals(palavra)) {
            return true;
        }else {
            return false;
        }
    }

    public static String getPalavraAleatoria() {
        Random random = new Random();
        int index = random.nextInt(WordleDictionary.WORDS.length);
        return WordleDictionary.WORDS[index];
    }
}
