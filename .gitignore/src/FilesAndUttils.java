
import java.io.*;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class FilesAndUttils extends FilmRental {

    Properties defaultProps = new Properties();

    FileInputStream in;

    {
        try {
            in = new FileInputStream("C:\\Users\\Grzesiek\\IdeaProjects\\FilmRental\\src\\setting.properties");
            defaultProps.load(in);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String pathf = defaultProps.getProperty("filmFile");
    String pathc = defaultProps.getProperty("custFile");

    File filmFile = new File(pathf);
    File custFile = new File(pathc);


    /**
     * metoda zapisuje do pliku podanego jako @param File file listę podaną jako @param ArrayList dane wszystkich filmów
     *
     * @param arrayList
     */

    public void saveFilms(ArrayList<Film> arrayList) {
        try {
            BufferedWriter save = new BufferedWriter(new FileWriter(filmFile));
            //BufferedWriter save = Files.newBufferedWriter(pathfilm);

            for (Film film : arrayList) {
                save.write(film.getIdFilm() + ";");
                save.write(film.getTitleFilm() + ";");
                save.write(film.getTypeFilm() + ";");
                save.write(film.getDirectorFilm() + ";");
                save.write(film.getDescriptionFilm() + ";");
                save.write(film.getCastFilm() + ";");
                save.write(film.getPremierDate() + ";");
                save.write(film.getPieces() + ";");
                save.write(film.getPrice() + ";");
                save.write(film.getIsRented() + "\n");
            }
            System.out.println("Zapisano dane filmów do pliku: " + filmFile.toString());
            save.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * metoda zapisuje do pliku podanego jako @param File file listę podaną jako @param ArrayList dane wszystkich klientów
     *
     * @param arrayList
     */
    public void saveCustomer(ArrayList<Customer> arrayList) {
        try {
            BufferedWriter save = new BufferedWriter(new FileWriter(custFile));
            //BufferedWriter save = Files.newBufferedWriter(pathcust);

            for (Customer customer : arrayList) {
                save.write(customer.getIdCustomer() + ";");
                save.write(customer.getName() + ";");
                save.write(customer.getAdres() + ";");
                save.write(customer.getRentals() + ";");
                save.write(customer.getReservation() + ";");
                save.write(customer.getSaldo() + "\n");
            }
            System.out.println("Zapisano dane klientów do pliku: " + custFile.toString());
            save.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Metoda odczytuje z pliku File file dane filmów do listy @param ArrayList
     *
     * @param filmArrayList
     */

    public void loadFilms(ArrayList<Film> filmArrayList) {

        try {
            String rowFilm = null;
            BufferedReader fileBuff = new BufferedReader(new FileReader(filmFile));
            //BufferedReader fileBuff = Files.newBufferedReader(pathfilm);

            while ((rowFilm = fileBuff.readLine()) != null) {
                makeFilm(rowFilm, filmArrayList);
            }
            fileBuff.close();

        } catch (Exception ex) {
            System.out.println("Nie można odczytać pliku.");
            ex.printStackTrace();
        }
    }

    /**
     * Metoda odczytuje z pliku File file dane klientów do listy @param ArrayList
     *
     * @param customerArrayList
     */

    public void loadCustomer(ArrayList<Customer> customerArrayList) {

        try {
            String row = null;
            BufferedReader fileBuff = new BufferedReader(new FileReader(custFile));

            while ((row = fileBuff.readLine()) != null) {
                makeCustomer(row, customerArrayList);
            }
            fileBuff.close();

        } catch (Exception ex) {
            System.out.println("Nie można odczytać pliku.");
            ex.printStackTrace();
        }
    }

    public void makeFilm(String rowData, ArrayList<Film> filmArrayList) {
        String[] data = rowData.split(";");
        Film film = new Film(parseInt(data[0]), data[1], data[2], data[3], data[4], data[5], parseInt(data[6]), parseInt(data[7]), parseDouble(data[8]), Boolean.valueOf(data[9]));
        filmArrayList.add(film);
    }

    public void makeFilm(Film newFilm, ArrayList<Film> filmArrayList) {
        Film film = new Film(newFilm.getIdFilm(), newFilm.getTitleFilm(), newFilm.getTypeFilm(), newFilm.getDirectorFilm(), newFilm.getDescriptionFilm(), newFilm.getCastFilm(), newFilm.getPremierDate(), newFilm.getPieces(), newFilm.getPrice(), newFilm.getIsRented());
        filmArrayList.add(film);
        System.out.println("Utworzono nowy film " + newFilm.getTitleFilm());
    }

    public void makeCustomer(String rowData, ArrayList<Customer> customerArrayList) {

        String[] data = rowData.split(";");
        Customer customer = new Customer(parseInt(data[0]), data[1], data[2], data[3], data[4], parseDouble(data[5]));
        customerArrayList.add(customer);
        //System.out.println("Utworzono nowego klienta " + data[0]);
    }

    //metoda przeciazona nasluchuje Obiektu newCustomer oraz ArrayList do której ma zapisac
    public void makeCustomer(Customer newCustomer, ArrayList<Customer> customerArrayList) {
        Customer customer = new Customer(newCustomer.getIdCustomer(), newCustomer.getName(), newCustomer.getAdres(), newCustomer.getRentals(), newCustomer.getReservation(), newCustomer.getSaldo());
        customerArrayList.add(customer);
        System.out.println("Utworzono nowego klienta " + newCustomer.getName());
    }

    public void remove(String whatremove, ArrayList<?> arrayList) {
        Scanner scan = new Scanner(System.in);
        if (whatremove.equals("removeCustomer")) {
            Customer.showAllCustomerSortedName((ArrayList<Customer>) arrayList);
            System.out.println("\nPodaj numer klienta, którego chcesz usunąć:");
            int which = (Integer.parseInt(scan.nextLine()))-1;
            System.out.println("czy napewno chcesz usunąć klienta " + arrayList.get(which) + "?\nY/N");
            String yORn = scan.nextLine();

            if (yORn.equals("Y")) {
                //FilesAndUttils.removeSelect(which, (ArrayList<Customer>) arrayList);
                System.out.println("Usunieto z bazy " + arrayList.get(which));
                arrayList.remove(which);
            } else if (yORn.equals("N")) {
                System.out.println("Nie usuwasz nic.");
            }

        } else if (whatremove.equals("removeFilm")) {
            Film.showAllFilms((ArrayList<Film>) arrayList);
            System.out.println("\nPodaj numer filmu, który chcesz usunąć:");
            int which = (Integer.parseInt(scan.nextLine()))-1;
            System.out.println("czy napewno chcesz usunąć film " + arrayList.get(which) + "?\nY/N");

            String yORn = scan.nextLine();

            if (yORn.equals("Y")) {
                System.out.println("Usunieto z bazy " + arrayList.get(which));
                arrayList.remove(which);
            } else {
                System.out.println("Nie usuwasz nic.");
            }
        } else System.out.println("Nie podano co ma byc usuniete.");
    }

    /*public static void removeSelect(int whichRemove, ArrayList<?> arrayList) {
        System.out.println("Usunieto z bazy " + arrayList.get(whichRemove));
        arrayList.remove(whichRemove);
    }*/

}
