

import java.util.ArrayList;
import java.util.Scanner;

public class Film {
    private int IdFilm;
    private String titleFilm;
    private String typeFilm;
    private String directorFilm;
    private String descriptionFilm;
    private String castFilm = "";
    private int premierDate;
    private int pieces;
    private double price;
    private boolean isRented = false;

    public Film() {
    }

    public Film(int IdFilm, String titleFilm, String typeFilm, String directorFilm, String descriptionFilm, String castFilm, int premierDate, int pieces, double price, boolean isRented) {
        this.IdFilm = IdFilm;
        this.titleFilm = titleFilm;
        this.typeFilm = typeFilm;
        this.directorFilm = directorFilm;
        this.descriptionFilm = descriptionFilm;
        this.castFilm = castFilm;
        this.premierDate = premierDate;
        this.pieces = pieces;
        this.price = price;
        this.isRented = isRented;
    }

    public int getIdFilm() {return IdFilm;}

    public void setIdFilm(int idFilm) {IdFilm = idFilm;}

    public boolean isRented() {return isRented;}

    public String getTitleFilm() {return titleFilm;}

    public void setTitleFilm(String titleFilm) {this.titleFilm = titleFilm;}

    public String getTypeFilm() {return typeFilm;}

    public void setTypeFilm(String typeFilm) {this.typeFilm = typeFilm;}

    public String getDirectorFilm() {return directorFilm;}

    public void setDirectorFilm(String directorFilm) {this.directorFilm = directorFilm;}

    public String getDescriptionFilm() {return descriptionFilm;}

    public void setDescriptionFilm(String descriptionFilm) {this.descriptionFilm = descriptionFilm;}

    public String getCastFilm() {return castFilm;}

    public void setCastFilm(String castFilm) {this.castFilm = castFilm;}

    public int getPremierDate() {return premierDate;}

    public void setPremierDate(int premierDate) {this.premierDate = premierDate;}

    public int getPieces() {return pieces;}

    public void setPieces(int pieces) {this.pieces = pieces;}

    public double getPrice() {return price;}

    public void setPrice(double price) {this.price = price;}

    public boolean getIsRented() {return isRented;}

    public void setRented(boolean rented) {isRented = rented;}


    @Override
    public String toString() {
        return "Id film=" + IdFilm + ' ' +
                "title=" + titleFilm +
                ", type=" + typeFilm +
                ", director=" + directorFilm +
                //", description=" + descriptionFilm +
                //", cast=" + castFilm +
                ", premier date=" + premierDate +
                ", pieces=" + pieces +
                ", price=" + price +
                ", is rented=" + isRented +
                '}';
    }

    /**
     * Metoda wypisuje liste wszystkich filmow
     * @param arrayList
     */

    public static void showAllFilms(ArrayList<Film> arrayList){
        System.out.println("Katalog filmów (sort po nazwie):");

        arrayList.stream()
                .sorted((s1, s2) -> s1.getTitleFilm().compareTo(s2.getTitleFilm()))
                .forEach(System.out::println);
    }

    /**
     * Metoda wypisuje liste wszystkich dostępnych filmow, czyli Pieces>0
     * @param arrayList
     */

    public static void showAvailableFilms(ArrayList<Film> arrayList){
        System.out.println("Katalog filmów (sort po nazwie):");

        arrayList.stream()
                .filter(l -> l.getPieces()>0)
                .sorted((s1, s2) -> s1.getTitleFilm().compareTo(s2.getTitleFilm()))
                .forEach(System.out::println);
    }

    /**
     * Metoda tworzy i zwraca obiekt Film wg. podanych przez użytkownika danych
     * @param lastId
     * @return Film
     */
    public Film addFilm(int lastId){
        System.out.println("Dodawanie nowego filmu:");
        Film addFilm = new Film();
        Scanner sc = new Scanner(System.in);
        addFilm.setIdFilm(lastId+1);
        //String data;
        System.out.println("Podaj nazwę filmu:");
        addFilm.setTitleFilm(sc.nextLine());
        System.out.println("Podaj kategorię filmu (dramat, komedia itp.):");
        addFilm.setTypeFilm(sc.nextLine());
        System.out.println("Podaj reżysera filmu:");
        addFilm.setDirectorFilm(sc.nextLine());
        System.out.println("Wprowadz krótki opis filmu:");
        addFilm.setDescriptionFilm(sc.nextLine());
        System.out.println("Podaj rok premiery filmu:");
        addFilm.setPremierDate(Integer.parseInt(sc.nextLine()));
        System.out.println("Ile sztuk chcesz wprowadzić na stan:");
        addFilm.setPieces(Integer.parseInt(sc.nextLine()));
        System.out.println("Ile film ma kosztować (cena = ZZ.GR):");
        addFilm.setPrice(Double.parseDouble(sc.nextLine()));
        System.out.println("Dodano film "  + addFilm.getTitleFilm());

        return addFilm;
    }
}
