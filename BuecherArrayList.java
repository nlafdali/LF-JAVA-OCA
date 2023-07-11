import java.util.*;

public class BuecherArrayList {
	
    public static void main(String[] args) {
        ArrayList<String> Buecher = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        int antwort = 0;
        String antwort2 = "";
        String neuesbuch = "";
        int ind = 0;

        while (antwort != 4) {
            System.out.println("\nWas wollen Sie tun?\n1: Ein Buch eingeben\n2: ein Buch ersetzen\n3: Bücher anschauen\n4: Programm beenden ");

            antwort = scan.nextInt();
            scan.nextLine();
            if(antwort == 1) {
                System.out.print("Bitte ein Buch eingeben: ");
                String Buch = scan.nextLine();
                Buecher.add(Buch);
            }
            if(antwort == 2){
                System.out.println("Welches Buch wollen Sie ersetzen?");
                antwort2 = scan.nextLine();
                System.out.println("Was für ein Buch wollen Sie stattdessen dahin stellen?");
                neuesbuch = scan.nextLine();
                ind = Buecher.indexOf(antwort2);
                Buecher.set(ind, neuesbuch);
                System.out.println("Das Buch wurde ersetzt!");
            }

            if (antwort == 3) {
                for(int i = 0; i < Buecher.size(); i++) {
                    System.out.println(Buecher.get(i));
                }

            if(antwort == 4) {
                System.out.println("Programm beendet!");
                break;
            }
            }


        }
    }
}
