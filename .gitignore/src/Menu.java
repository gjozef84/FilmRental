
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Scanner;


public class Menu  {

    /**
     * Metoda wyświetla menu w formie tekstowej
     */

    public void menu() {
        System.out.println("====================  WITAJ w wypożyczalni filmów Movie-Rental  ====================\n" +
                "  MENU \n"+
                "*  Przeglądanie:\n" +
                "     - katalog KLIENTÓW (K)\n"+
                "     - katalog FILMÓW (F)\n"+
                "*  Wypozyczenie, rezerwacja (W)\n" +
                "*  Zwroty (Z)\n" +
                "*  (D)odawanie, (U)suwanie, (E)dycja zbiorów\n" +
                "*  Opłaty, saldo (O)\n" +
                "*  EXIT (kończy i zapisuje pliki)\n" +
                "                                               do odbsługi Menu uzywaj K,F,W,Z,D,O,EXIT\n"+
                "======================================================================================");
    }

    public static void submenu(){
        System.out.println("\nCo chcesz zrobić...?");
        System.out.println("M - powrót do Menu, D - dodanie klienta, W - wypozyczenie filmu, EXIT - wyjście");
    }

    public static void submenuFilm(){
        System.out.println("\nCo chcesz zrobić...?");
        System.out.println("M - powrót do Menu, D - dodanie filmu, U - usunięcie filmu, W - wypozyczenie filmu, EXIT - wyjście");
    }

    public static void submenuCust(){
        System.out.println("\nCo chcesz zrobić...?");
        System.out.println("M - powrót do Menu, D - dodanie klienta, U - usunięcie klienta, EXIT - wyjście");
    }


}
