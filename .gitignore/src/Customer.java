

import java.util.ArrayList;
import java.util.Scanner;

public class Customer extends FilesAndUttils{
    private int IdCustomer = 1;
    private String name;
    private String adres;
    Integer maxRentals = Integer.parseInt(defaultProps.getProperty("maxRentals"));
    //private String rentals = "";
    //private String[] rentalsTab = new String[maxRentals];
    private String rentals = "";
    private String reservation = "";
    public Double saldo = 0.00;



    public Customer() {
    }

    public Customer(int idCustomer, String name, String adres, String rentals, String reservation, double saldo) {
        this.IdCustomer = idCustomer;
        this.name = name;
        this.adres = adres;
        this.rentals = rentals;
        this.reservation = reservation;
        this.saldo = saldo;
    }


    public int getIdCustomer() {return IdCustomer;}

    public void setIdCustomer(int idCustomer) {IdCustomer = idCustomer;}

    public void setSaldo(Double saldo) {this.saldo = saldo;}

    public String getName() {
        return name;
    }

    public void setName(String name) {this.name = name;}

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {this.adres = adres;}

    public String getRentals() {
        return rentals;
    }

    public void setRentals(String rentals) {this.rentals = rentals;}

    public String getReservation() {return reservation;}

    public void setReservation(String reservation) {this.reservation = reservation;}

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {this.saldo = saldo;}

    @Override
    public String toString() {
        return "Id Customer=" + IdCustomer + ' '+
                "name=" + name +
                " adres=" + adres +
                " rentals=" + rentals +
                " reservation=" + reservation +
                " kwota wypozyczeń =" + saldo;
    }

    /**
     * Metoda wyświetla liste wszystkich klientów z @param arrayList posortowaną wg. nazwisk roznąco
     * @param arrayList
     */
    public static void showAllCustomerSortedName(ArrayList<Customer> arrayList) {
        System.out.println("Katalog klientów (sort po nazwisku):");

        arrayList.stream()
                .sorted((s1, s2) -> s1.getName().compareTo(s2.getName()))
                //.filter(l -> l.getName()
                .forEach(System.out::println);
    }

    /**
     * Metoda wyświetla liste wszystkich klientow z @param arrayList
     * @param arrayList
     */

    public void showAllCustomer(ArrayList<Customer> arrayList) {
        System.out.println("Katalog klientów:");

        arrayList.stream()
                //.filter(l -> l.getName()
                .forEach(System.out::println);
    }

    /**
     * Metoda wyświetla listę wszystkich klientów z @param arrayList którzy mają jakiś wypożyczony film
     * @param arrayList
     */

    public void showAsRentalCustomer(ArrayList<Customer> arrayList) {
        System.out.println("Katalog klientów którzy mają wypozyczone filmy:");

        arrayList.stream()
                .filter(l -> !l.getRentals().equals(""))
                .sorted((s1, s2) -> s1.getName().compareTo(s2.getName()))
                .forEach(System.out::println);
    }

    /**
     * Metoda wyświetla strumień zawierający klienta podanego jako @param name z listy @param arrayList
     * @param arrayList
     * @param name
     */
    public void showOneCustomer(ArrayList<Customer> arrayList, String name) {
        System.out.println("Karta klienta o nazwisku: "+name);

        arrayList.stream()
                .filter(l -> l.getName() == name)
                .forEach(System.out::println);
    }

    /**
     * Metoda tworzy i zwraca obiekt Customer wg. podanych przez użytkownika danych
     * @param lastId
     * @return
     */
    public Customer addCustomer(int lastId) {
        System.out.println("Dodawanie nowego klienta:");
        Customer addCustomer = new Customer();
        Scanner sc = new Scanner(System.in);
        addCustomer.setIdCustomer(lastId+1);
        System.out.println("Podaj nazwisko i imię:");
        addCustomer.setName(sc.nextLine());
        System.out.println("Podaj adres (Miejscowość Ulica Nr):");
        addCustomer.setAdres(sc.nextLine());
        System.out.println("Dodano " + addCustomer.getIdCustomer() +" klienta " + addCustomer.getName());

        return addCustomer;
    }
}
