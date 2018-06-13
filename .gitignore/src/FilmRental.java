import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

/* make Grzesiek Jozefowicz gjozef84@gmail.com
 * Version 1.8
 * */

public class FilmRental {

    public ArrayList<Film> filmArrayList;
    public ArrayList<Customer> customerArrayList;
    public FilesAndUttils filesAndUttils;
    //private JFrame frame;
    //private JLabel etykieta;
    //private JTextField textField;
    //public JPanel buttonPanel;

    public static void main(String[] args) {

        ArrayList<Customer> customerArrayList = new ArrayList<>();
        ArrayList<Film> filmArrayList = new ArrayList<>();

        FilesAndUttils filesAndUttils = new FilesAndUttils();
        filesAndUttils.loadFilms(filmArrayList); //ładujemy filmy z pliku do listy filmArrayList
        filesAndUttils.loadCustomer(customerArrayList); //ładujemy klientów z pliku do listy customerArrayList

        FilmRental filmRental = new FilmRental();
        Customer cust = new Customer(); //instancja klienta zeby mozna było używać metod clasy Customer
        Film film = new Film();         //instancja film zeby mozna było używać metod clasy Film
        Menu menu = new Menu();         //instancja menu zeby mozna było uzywac metod clasy Manu

        menu.menu(); //wyswietlenie menu

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nCo chcesz zrobić...?");
        String userChoice;
        userChoice = scanner.nextLine();
        String userChoiceD = "";

        do {

            String tabRentString = "";
            switch (userChoice) {
                //katalog klientów
                case "K":
                    cust.showAllCustomer(customerArrayList);
                    menu.submenuCust();
                    userChoice = scanner.nextLine();
                    userChoiceD = "DC";
                    break;

                //katalog filmów
                case "F":
                    film.showAllFilms(filmArrayList);
                    menu.submenuFilm();
                    userChoice = scanner.nextLine();
                    userChoiceD = "DF";
                    break;

                //wypozyczenie
                case "W":
                    String[] tabRentTemp = new String[]{};
                    ArrayList<String> tabRent = new ArrayList<String>(){};

                    System.out.println("Wybierz film do wypozyczenia z poniższej listy (wyświetla tylko dostępne pozycje):");

                    Film.showAvailableFilms(filmArrayList);
                    System.out.println("\nWybierz który film chcesz wypoźyczyć:\n(podaj nr id filmu)...");
                    int whichFilm = scanner.nextInt();
                    Film whichF = filmArrayList.get(whichFilm - 1);

                    Customer.showAllCustomerSortedName(customerArrayList);

                    System.out.println("\nWybierz klienta, który chce wypożyczyć film:");
                    int whichCustomer = scanner.nextInt();

                    Customer whichC = customerArrayList.get(whichCustomer - 1);
                    System.out.println("dlugosc tabRentTemp PRZED" + tabRentTemp.length);
                    tabRentTemp = whichC.getRentals().split(","); //splitem tworzy tabRentTemp ze stringa wypozyczonych filmow Rentals
                    System.out.println("dlugosc tabRentTemp " + tabRentTemp.length);

                    if (tabRentTemp.length < cust.maxRentals) { //sprawdza czy uzytkownik nie ma juz maxRentals
                        for (int i = 0; i <= tabRentTemp.length; i++) {
                            if (i == 0 && "".equals(tabRentTemp[0])) {tabRent.add(String.valueOf(whichF.getIdFilm())); break;}
                            else {
                                if (i < tabRentTemp.length) tabRent.add(tabRentTemp[i]);
                                else if (i == tabRentTemp.length) tabRent.add(String.valueOf(whichF.getIdFilm()));
                            }
                        }
                        System.out.println("dlugosc tabRent " + tabRent);

                        tabRentString = String.join(",", tabRent);
                        whichC.setRentals(tabRentString); //ustawia Rentals(wypozyczone filmy) na te zawarte w tabRentString

                        System.out.println("Klientowi " + whichC.getName() + " wypożyczono " + whichF.getTitleFilm() + "\nNaliczono opłatę: " + whichF.getPrice());
                        whichF.setPieces(whichF.getPieces() - 1); //ustawia ilosc filmow na stanie na ilosc-1
                        whichF.setRented(true); //ustawia stan Rented na true=wypozyczony
                        whichC.setSaldo(whichC.getSaldo() + whichF.getPrice());
                        filmArrayList.set(whichFilm - 1, whichF); //set aktualizuje stan whichFilm-1, tzn.ustawia setRented i zmienia ilosc sztuk
                        customerArrayList.set(whichCustomer - 1, whichC);
                    } else
                        System.out.println("Niestety klient ma wypożyczoną maksymalną (" + cust.maxRentals + "szt) dozwoloną ilość filmów.\nZwróć jakiś film żeby wypożyczyć następny.");

                    menu.submenu();
                    Scanner sc = new Scanner(System.in);
                    userChoice = sc.next();
                    break;

                //zwroty
                case "Z":

                    cust.showAsRentalCustomer(customerArrayList);
                    System.out.println("Wybierz klienta, który chce zwrócić film:\n");
                    whichCustomer = scanner.nextInt();
                    whichC = customerArrayList.get(whichCustomer - 1);

                    if (whichC.getRentals().equals(""))
                        System.out.println("Klient nie posiada wypożyczonych filmów.");
                    else {
                        System.out.println("Klient posiada wypozyczone:");
                        tabRentTemp = whichC.getRentals().split(",");

                        for (int i = 0; i < tabRentTemp.length; i++) {
                            System.out.println(filmArrayList.get(Integer.parseInt(tabRentTemp[i]) - 1));
                        }
                        System.out.println("\nWybierz film ktory chcesz zwrocic (podaj nr id filmu):");
                        whichFilm = scanner.nextInt();
                        whichF = filmArrayList.get(whichFilm - 1);
                        //
                        int j = 0;
                        String[] tabRentZ = new String[tabRentTemp.length - 1];

                        for (int i = 0; i < tabRentZ.length; i++) {
                            if (whichFilm != Integer.parseInt(tabRentTemp[j])) {
                                tabRentZ[i] = tabRentTemp[j];
                                j++;
                            } else {
                                i--;
                                j++;
                            }
                        }

                        for (int i = 0; i < tabRentZ.length; i++) {
                            if (i != tabRentZ.length)
                                tabRentString += tabRentZ[i] + ",";
                            else
                                tabRentString += tabRentZ[i];
                        }

                        whichF.setRented(false);
                        whichF.setPieces(whichF.getPieces() + 1);

                        whichC.setRentals(tabRentString);

                        System.out.println("Dokonano zwrotu " + whichF.getTitleFilm());
                        filmArrayList.set(whichFilm - 1, whichF);
                        customerArrayList.set(whichCustomer - 1, whichC);
                    }
                    if (!whichC.getRentals().isEmpty())
                        System.out.println("Klient posiada jeszcze wypozyczone filmy. Żeby zwrócić pozostałe wejdz ponownie do Z-zwroty.");

                    menu.submenu();
                    Scanner scc = new Scanner(System.in);
                    userChoice = scc.next();

                    break;

                //dodawanie pozycji
                case "D":
                    if (userChoiceD.isEmpty()) {
                        System.out.println("Co chcesz dodać? FILM(F) czy KLIENTA(K):");
                        userChoiceD = scanner.nextLine();
                        if (userChoiceD.equals("F") || userChoiceD.equals("DF")) {
                            filmArrayList.add(film.addFilm(filmArrayList.size()));
                            menu.submenu();
                        } else if (userChoiceD.equals("K") || userChoiceD.equals("DC")) {
                            customerArrayList.add(cust.addCustomer(customerArrayList.size()));
                            menu.submenu();
                        }
                    }
                    userChoice = scanner.nextLine();
                    break;

                //usuwanie klienta lub filmu
                case "U":

                    if (userChoiceD.isEmpty()) {
                        System.out.println("Co chcesz usunąć? FILM (F) czy KLIENTA (K):");
                        userChoiceD = scanner.nextLine();
                    }
                    if (userChoiceD.equals("F") || userChoiceD.equals("DF")) {
                        filesAndUttils.remove("removeFilm", filmArrayList);
                    } else if (userChoiceD.equals("K") || userChoiceD.equals("DC")) {
                        filesAndUttils.remove("removeCustomer", customerArrayList);
                    }

                    menu.submenu();
                    userChoice = scanner.nextLine();
                    break;

                case "O": //oplaty
                    System.out.println("Lista klientów, wybierz którego klienta chcesz sprawdzić:");
                    Customer.showAllCustomerSortedName(customerArrayList);
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Podaj który klient Cię interesuje: ");
                    int whichCust = scan.nextInt();
                    whichC = customerArrayList.get(whichCust-1);
                    System.out.println("\nKlient " + whichC.getName());
                    System.out.printf("Ma obecnie saldo wynoszące %4.2f zł\n", whichC.getSaldo());

                    menu.submenu();
                    userChoice = scanner.nextLine();
                    break;
                case "M": //menu
                    menu.menu();
                    System.out.println("\nCo chcesz zrobić...?");
                    userChoice = scanner.nextLine();
                    break;
                case "EXIT":
                    userChoice = "EXIT";
                    filesAndUttils.saveCustomer(customerArrayList);
                    filesAndUttils.saveFilms(filmArrayList);
                    System.exit(0);
                default:
                    menu.menu();
                    System.out.println("\nCo chcesz zrobić...?");
                    userChoice = scanner.nextLine();
            }
        } while (userChoice != "EXIT");

        filesAndUttils.saveFilms(filmArrayList);
        filesAndUttils.saveCustomer(customerArrayList);

        //filmRental.menuGUI();

    }

    /*public void menuGUI() {
        JFrame frame = new JFrame("WYPOZYCZALNIA");
        JButton buttonCustomer = new JButton("KLIENCI");
        JButton buttonFilms = new JButton("FILMY");
        JButton buttonRental = new JButton("Wypozyczenie");
        JButton buttonReturn = new JButton("Zwroty");
        JButton buttonAdded = new JButton("Dodawanie, edycja");
        JButton buttonCost = new JButton("Opłaty");
        JButton buttonExit = new JButton("EXIT");
        //JLabel etykieta = new JLabel("Etykieta");
        JTextField textField = new JTextField();
        textField.setSize(50, 50);
        frame.getContentPane().add(textField);
        textField.setText("HUJ WIE JAK To DZIALA");

        buttonCustomer.addActionListener(new ButtonCustomerListener());
        buttonFilms.addActionListener(new ButtonFilmsListener());
        buttonRental.addActionListener(new ButtonRentalListener());
        buttonReturn.addActionListener(new ButtonReturnListener());
        buttonAdded.addActionListener(new ButtonAddedListener());
        buttonCost.addActionListener(new ButtonCostListener());
        buttonExit.addActionListener(new ButtonExitListener());

        //buttonPanel = this

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.NORTH, buttonCustomer);
        frame.getContentPane().add(BorderLayout.NORTH, buttonFilms);
        frame.getContentPane().add(BorderLayout.CENTER, buttonRental);
        frame.getContentPane().add(BorderLayout.CENTER, buttonReturn);
        frame.getContentPane().add(BorderLayout.NORTH, buttonAdded);
        frame.getContentPane().add(BorderLayout.NORTH, buttonCost);
        frame.getContentPane().add(BorderLayout.SOUTH, buttonExit);
        //frame.getContentPane().add(BorderLayout.SOUTH, etykieta);
        frame.getContentPane().add(BorderLayout.SOUTH, textField);
        frame.setSize(600, 300);
        frame.setLayout(new GridLayout(2, 7));
        frame.setVisible(true);
    }

    private class ButtonCustomerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            //textField.setText("Wyswietlanie Customer");
            JFrame frameCustomer = new JFrame("Katalog klientów");

            frameCustomer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            frameCustomer.setSize(300, 300);
            frameCustomer.setLayout(new GridLayout(1, 1));
            frameCustomer.setVisible(true);
            //JTextPane textArea = new JTextPane();
            JTextArea customerArea = new JTextArea(10, 20);

            JScrollPane panel = new JScrollPane(customerArea);
            customerArea.setEditable(false);
            customerArea.setText("DUPADUPA");

            //JOptionPane.showMessageDialog(null, "Nacisnales przycisk CUSTOMER");
        }
    }

    private class ButtonFilmsListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Nacisnales przycisk FILMS");
        }
    }

    private class ButtonRentalListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Nacisnales przycisk RENTAL");
        }
    }

    private class ButtonReturnListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Nacisnales przycisk RETURN");
        }
    }

    private class ButtonAddedListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            //JOptionPane.showMessageDialog(null, "Nacisnales przycisk ADDED");
            //JTextField textField = new JTextField(20);
            JFrame frameAdded = new JFrame("Dodawanie");
            frameAdded.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            frameAdded.setSize(200, 200);
            frameAdded.setLayout(new GridLayout());
            frameAdded.setVisible(true);
            JTextField textField = new JTextField("Podaj nazwę filmu...");
            frameAdded.getContentPane().add(textField);
            //textField.setText("wpisz coś");
            textField.selectAll();
        }
    }

    private class ButtonCostListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Nacisnales przycisk COST");
        }
    }

    private class ButtonExitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Nacisnales przycisk exit");
            System.exit(0);
        }
    }*/
}
