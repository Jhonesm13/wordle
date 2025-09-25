import java.text.Normalizer;
import java.util.Random;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String palavra = getPalavraAleatoria().toLowerCase();
    private static final String palavraLimpa = limpaInput(palavra).toLowerCase();
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String RESET = "\033[0m";
    public static final String RED = "\033[0;31m";

    public static void main(String[] args) {
        System.out.println("Palavra sorteada: " + palavraLimpa);

        for(int i=0;i<6;i++){
            Boolean result = descobrePalavra();
            if(result) {
                System.out.println("Você acertou a palavra: "+palavra);
                break;
            } else {
                if(i==5) {
                    System.out.println("\nVocê perdeu.");
                }
            }    
        }
        
        scanner.close();
    }

    public static Boolean descobrePalavra() {
        boolean palavraValida = false;
        String palavraEscolhida;

        do {
            System.out.println("\nDigite a palavra:");
            palavraEscolhida = scanner.nextLine();
            palavraEscolhida = limpaInput(palavraEscolhida);

            if(palavraEscolhida.length() != 5) {
               System.out.println("Digite uma palavra de 5 letras.");
                continue; 
            }
            
            if (!existeNoDicionario(palavraEscolhida)) {
                System.out.println("Essa palavra não é válida.");
                continue;
            }

            palavraValida = true;
        } while(!palavraValida);

        if(palavraEscolhida.equals(palavraLimpa)) {
            return true;
        } else {
            for(int i=0;i<palavraEscolhida.length();i++){
                if(palavraLimpa.contains(String.valueOf(palavraEscolhida.charAt(i)))){
                    // Todo: verificar se a letra ta no lugar correto, ai sim fica verde, se não amarelo
                    System.out.print(GREEN + " " + String.valueOf(palavraEscolhida.charAt(i)) + RESET);
                } else {
                    System.out.print(RED + " " + String.valueOf(palavraEscolhida.charAt(i)) + RESET);
                }
            }
            return false;
        }
    }

    public static boolean existeNoDicionario(String palavra) {        
        for (String palavraDicionario : WordleDictionary.WORDS) {
            String palavraDicionarioLimpa = limpaInput(palavraDicionario);
            
            if (palavraDicionarioLimpa.equals(palavra)) {
                return true;
            }
        }
        return false;
    }

    public static String limpaInput(String palavra) {
        String palavraLimpa = Normalizer.normalize(palavra, Normalizer.Form.NFD).trim();
        return palavraLimpa.replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
    }

    public static String getPalavraAleatoria() {
        Random random = new Random();
        int index = random.nextInt(WordleDictionary.WORDS.length);
        return WordleDictionary.WORDS[index];
    }
}
