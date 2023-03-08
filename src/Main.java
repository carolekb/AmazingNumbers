
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InputMismatchException {
        boolean flag = true;
        System.out.println("Welcome to Amazing Numbers!");

        do {
            if(flag){
                System.out.println();
                System.out.println("Supported requests:\n" +
                        "- enter a natural number to know its properties;\n" +
                        "- enter two natural numbers to obtain the properties of the list:\n" +
                        "  * the first parameter represents a starting number;\n" +
                        "  * the second parameter shows how many consecutive numbers are to be printed;\n" +
                        "- two natural numbers and properties to search for;\n" +
                        "- a property preceded by minus must not be present in numbers;\n" +
                        "- separate the parameters with one space;\n" +
                        "- enter 0 to exit.");
            }
            flag = false;
            long liczba = 0;
            String liczbaS ;
            String[] property = {"", "", "", "", "", "", "", "", "","", "", "", "", "", "", "", "", "","", ""};
            long liczba2 = 0;
            boolean isEven ;
            boolean isBuzz = false;
            boolean isDuck = false;
            boolean isPalindromic = true;
            boolean isGapful = false;
            boolean isSpy = false;
            boolean isSunny = false;
            boolean isSquare = false;
            boolean isJumping = true;
            boolean isHappy = false;
            boolean jest = false;
            boolean jest2 = false;
            boolean error = false;
            String[] tab = new String[2];
            Scanner scanner = new Scanner(System.in);
            System.out.println();
            System.out.print("Enter a request: ");
            String wpis = scanner.nextLine();
            if(wpis.equals("") || wpis.charAt(0) == ' '){
                flag = true;
                continue;
            }
            //   ------   checks if there are any chars different than digits and space
            tab = wpis.split(" ");

            for(int i = 0 ; i < tab[0].length() ; i++){
                if(tab[0].charAt(i) < 48 || tab[0].charAt(i) > 57 ){
                    error = true;
                    System.out.println("The first parameter should be a natural number or zero.");
                    break;
                }
            }
            if(error) continue;
            if(tab.length > 1) {
                for(int i = 0 ; i < tab[1].length() ; i++){
                    if(tab[1].charAt(i) < 48 || tab[1].charAt(i) > 57 ){
                        error = true;
                        System.out.println("The second parameter should be a natural number.");
                        break;
                    }
                }
                if(tab[1].equals("0")) {
                    error = true;
                    System.out.println("The second  parameter should be a natural number.");
                }
                if(error) continue;
                jest = true;
            }
            //   ---------  let's check if given properties are lowercase(if not we lowercase them
            //   ---------- and if they match our properties
            if(tab.length > 2) {
                int wrongCount = 0;
                String wrongProperty = "";
                for (int k = 0; k < tab.length - 2; k++) {
                    char c;
                    StringBuilder sb = new StringBuilder(tab[k + 2]);
                    for (int i = 0; i < sb.length(); i++) {
                        c = sb.charAt(i);
                        if (c < 97 && c != 32 && c != '-') {
                            sb.deleteCharAt(i);
                            c += 32;
                            sb.insert(i, c);
                        }
                    }
                    property[k] = sb.toString();
                    if (!property[k].equals("even") && !property[k].equals("odd") && !property[k].equals("buzz")
                            && !property[k].equals("duck") && !property[k].equals("palindromic")
                            && !property[k].equals("gapful") && !property[k].equals("spy")
                            && !property[k].equals("sunny") && !property[k].equals("square")
                            && !property[k].equals("jumping")
                            && !property[k].equals("happy") && !property[k].equals("sad")
                            && !property[k].equals("-even") && !property[k].equals("-odd") && !property[k].equals("-buzz")
                            && !property[k].equals("-duck") && !property[k].equals("-palindromic")
                            && !property[k].equals("-gapful") && !property[k].equals("-spy")
                            && !property[k].equals("-sunny") && !property[k].equals("-square")
                            && !property[k].equals("-jumping")
                            && !property[k].equals("-happy") && !property[k].equals("-sad")) {
                        wrongCount++;
                        wrongProperty = property[k];
                        error = true;
                    }
                }
                if(wrongCount == 1){
                    System.out.printf("The property [%s] is wrong.\n" +
                            "Available properties: [buzz, duck, palindromic, gapful, spy, even, " +
                            "odd, sunny, square, jumping, happy, sad]\n\n", wrongProperty);
                }else if(wrongCount > 1){
                    System.out.printf("The properties ... are wrong.\n" +
                            "Available properties: [buzz, duck, palindromic, gapful, spy, even, " +
                            "odd, sunny, square, jumping, happy, sad]\n\n");
                }
                if(error) continue;

                if(isMutuallyExclusive(tab.length, property)) continue;
                jest2 = true;
            }

            if(tab[0].equals("0")) {
                System.out.println();
                System.out.println("Goodbye!");
                System.exit(0);
            }
            if(jest) {
                for(int i = 0 ; i < tab[1].length() ; i++){
                    liczba2 = liczba2 * 10;
                    liczba2 = liczba2 + tab[1].charAt(i) - 48;
                }
                for(int i = 0 ; i < tab[0].length() ; i++){
                    liczba = liczba * 10;
                    liczba = liczba + tab[0].charAt(i) - 48;
                }
                long count = 0;
                for(long m = 0 ; jest2 || m < liczba2 ; m++){
                    if(jest2 && count == liczba2) break;
                    if(m > 800000000) break;
                    // ------ isEven
                    if (liczba % 2 == 0) {
                        isEven = true;
                    } else isEven = false;
                    // ------- isBuzz
                    if (liczba % 10 == 7 || liczba % 7 == 0) {
                        isBuzz = true;
                    }else isBuzz = false;
                    //  ----   isDuck
                    String liczbaSS = Long.toString(liczba);
                    int n = 0;
                    while (n < liczbaSS.length() && liczbaSS.charAt(n) == '0') {
                        n++;
                    }
                    isDuck = false;
                    for (int i = n; i < liczbaSS.length() - 1; i++) {
                        if (liczbaSS.charAt(i + 1) == '0') isDuck = true;
                    }
                    //    ---  isPalindromic
                    isPalindromic = true;
                    for (int i = 0; i < liczbaSS.length() / 2; i++) {
                        if (liczbaSS.charAt(i) != liczbaSS.charAt(liczbaSS.length() - i - 1)) {
                            isPalindromic = false;
                        }
                    }
                    // ------ isGapful
                    isGapful = false;
                    if(liczbaSS.length() >= 3)
                        if(liczba % ((liczbaSS.charAt(0) - 48) * 10 + liczbaSS.charAt(liczbaSS.length() - 1) - 48) == 0){
                            isGapful = true;
                        }
                    // ------- isSpy
                    isSpy = false;
                    long sumaCyfr = 0;
                    long iloczynCyfr = 1;
                    for(int i = 0 ; i < liczbaSS.length() ; i++){
                        sumaCyfr += liczbaSS.charAt(i) - 48;
                        iloczynCyfr *= liczbaSS.charAt(i) - 48;
                    }
                    if(sumaCyfr == iloczynCyfr) {
                        isSpy = true;
                    }
                    //  ------  isSunny
                    isSunny = Math.floor(Math.sqrt(liczba + 1)) == Math.sqrt(liczba + 1);
                    //  ------- isSquare
                    isSquare = Math.floor(Math.sqrt(liczba)) == Math.sqrt(liczba);
                    //   ------ isJumping
                    isJumping = true;
                    for(int i = 0 ; i < liczbaSS.length() - 1 ; i++){
                        if(Math.abs(liczbaSS.charAt(i) - liczbaSS.charAt(i + 1)) != 1){
                            isJumping = false;
                        }
                    }
                    //  ----- isHappy
                    isHappy = true;
                    long temp = liczba;
                    long[] arrtemp = new long[100];
                    for(int j = 0 ; temp != 1 ; j++ ){
                        arrtemp[j] = temp;
                        String stemp = Long.toString(temp);
                        temp = 0;
                        for (int i = 0; i < stemp.length(); i++) {
                            temp += (stemp.charAt(i) - 48) * (stemp.charAt(i) - 48);
                        }
                        for(int k = 0 ; k < j + 1; k++){
                            if(arrtemp[k] == temp){
                                isHappy = false;
                                break;
                            }
                        }
                        if(!isHappy) break;
                    }
                    //   --------------------
                    if(jest2) {
                        boolean wydrukuj = true;
                        for(int k = 0 ; k < tab.length - 2; k++){
                            if(property[k].equals("even") && !isEven){
                                wydrukuj = false;
                                break;
                            }else if(property[k].equals("odd") && isEven){
                                wydrukuj = false;
                                break;
                            }else if(property[k].equals("buzz") && !isBuzz){
                                wydrukuj = false;
                                break;
                            }else if(property[k].equals("duck") && !isDuck){
                                wydrukuj = false;
                                break;
                            }else if(property[k].equals("palindromic") && !isPalindromic){
                                wydrukuj = false;
                                break;
                            }else if(property[k].equals("gapful") && !isGapful){
                                wydrukuj = false;
                                break;
                            }else if(property[k].equals("spy") && !isSpy){
                                wydrukuj = false;
                                break;
                            }else if(property[k].equals("sunny") && !isSunny){
                                wydrukuj = false;
                                break;
                            }else if(property[k].equals("square") && !isSquare){
                                wydrukuj = false;
                                break;
                            }else if(property[k].equals("jumping") && !isJumping){
                                wydrukuj = false;
                                break;
                            }else if(property[k].equals("happy") && !isHappy){
                                wydrukuj = false;
                                break;
                            }else if(property[k].equals("sad") && isHappy){
                                wydrukuj = false;
                                break;
                            }else if(property[k].equals("-even") && isEven){
                                wydrukuj = false;
                                break;
                            }else if(property[k].equals("-odd") && !isEven){
                                wydrukuj = false;
                                break;
                            }else if(property[k].equals("-buzz") && isBuzz){
                                wydrukuj = false;
                                break;
                            }else if(property[k].equals("-duck") && isDuck){
                                wydrukuj = false;
                                break;
                            }else if(property[k].equals("-palindromic") && isPalindromic){
                                wydrukuj = false;
                                break;
                            }else if(property[k].equals("-gapful") && isGapful){
                                wydrukuj = false;
                                break;
                            }else if(property[k].equals("-spy") && isSpy){
                                wydrukuj = false;
                                break;
                            }else if(property[k].equals("-sunny") && isSunny){
                                wydrukuj = false;
                                break;
                            }else if(property[k].equals("-square") && isSquare){
                                wydrukuj = false;
                                break;
                            }else if(property[k].equals("-jumping") && isJumping){
                                wydrukuj = false;
                                break;
                            }else if(property[k].equals("-happy") && isHappy){
                                wydrukuj = false;
                                break;
                            }else if(property[k].equals("-sad") && !isHappy){
                                wydrukuj = false;
                                break;
                            }
                        }
                        if(wydrukuj) {
                            drukuj(liczba, isEven, isBuzz, isDuck, isPalindromic, isGapful, isSpy, isSunny, isSquare, isJumping, isHappy);
                            count++;
                        }

                    }else{
                        drukuj(liczba, isEven, isBuzz, isDuck, isPalindromic, isGapful, isSpy, isSunny, isSquare, isJumping, isHappy);
                    }
                    liczba ++;
                }
                System.out.println();
            }else {
                // ------ isEven
                if (tab[0].charAt(tab[0].length() - 1) % 2 == 0) {
                    isEven = true;
                } else isEven = false;
                // ------- isBuzz
                for(int k = 0 ; k < tab[0].length() ; k++) {
                    liczba = liczba * 10;
                    liczba = liczba + tab[0].charAt(k) - 48;
                }

                if (liczba % 10 == 7 || liczba % 7 == 0) {
                    isBuzz = true;
                }
                //  ----   isDuck

                int n = 0;
                while (n < tab[0].length() && tab[0].charAt(n) == '0') {
                    n++;
                }
                for (int i = n; i < tab[0].length() - 1; i++) {
                    if (tab[0].charAt(i + 1) == '0') isDuck = true;
                }
                //    ---  isPalindromic
                isPalindromic = true;
                for (int i = 0; i < tab[0].length() / 2; i++) {
                    if (tab[0].charAt(i) != tab[0].charAt(tab[0].length() - i - 1)) {
                        isPalindromic = false;
                    }
                }
                //    ----    isGapful
                if(tab[0].length() >= 3)
                    if(liczba % ((tab[0].charAt(0) - 48) * 10 + tab[0].charAt(tab[0].length() - 1) - 48) == 0){
                        isGapful = true;
                    }
                // ------- isSpy
                isSpy = false;
                long sumaCyfr = 0;
                long iloczynCyfr = 1;
                for(int i = 0 ; i < tab[0].length() ; i++){
                    sumaCyfr += tab[0].charAt(i) - 48;
                    iloczynCyfr *= tab[0].charAt(i) - 48;
                }
                if(sumaCyfr == iloczynCyfr) {
                    isSpy = true;
                }
                //    isSunny
                isSunny = Math.floor(Math.sqrt(liczba + 1)) == Math.sqrt(liczba + 1);
                //   isSquare
                isSquare = Math.floor(Math.sqrt(liczba)) == Math.sqrt(liczba);
                //   ------ isJumping
                isJumping = true;
                for(int i = 0 ; i < tab[0].length() - 1 ; i++){
                    if(Math.abs(tab[0].charAt(i) - tab[0].charAt(i + 1)) != 1){
                        isJumping = false;
                    }
                }
                //  ----- isHappy
                isHappy = true;
                long temp = liczba;
                long[] arrtemp = new long[100];
                for(int j = 0 ; temp != 1 ; j++ ){
                    arrtemp[j] = temp;
                    String stemp = Long.toString(temp);
                    temp = 0;
                    for (int i = 0; i < stemp.length(); i++) {
                        temp += (stemp.charAt(i) - 48) * (stemp.charAt(i) - 48);
                    }
                    for(int k = 0 ; k < j + 1; k++){
                        if(arrtemp[k] == temp){
                            isHappy = false;
                            break;
                        }
                    }
                    if(!isHappy) break;
                }
                // -------------------------
                System.out.println();
                System.out.printf(String.format("Properties of %,d\n", liczba));
                System.out.printf("even: %s\n", isEven ? "true" : "false");
                System.out.printf("odd: %s\n", !isEven ? "true" : "false");
                System.out.printf("buzz: %s\n", isBuzz ? "true" : "false");
                System.out.printf("duck: %s\n", isDuck ? "true" : "false");
                System.out.printf("palindromic: %s\n", isPalindromic ? "true" : "false");
                System.out.printf("gapful: %s\n", isGapful ? "true" : "false");
                System.out.printf("spy: %s\n", isSpy ? "true" : "false");
                System.out.printf("sunny: %s\n", isSunny ? "true" : "false");
                System.out.printf("square: %s\n", isSquare ? "true" : "false");
                System.out.printf("jumping: %s\n", isJumping ? "true" : "false");
                System.out.printf("happy: %s\n", isHappy ? "true" : "false");
                System.out.printf("sad: %s\n", !isHappy ? "true" : "false");

            }
        }while(true);
    }

    public static void drukuj(long liczba, boolean isEven, boolean isBuzz, boolean isDuck,
                              boolean isPalindromic , boolean isGapful, boolean isSpy,
                              boolean isSunny, boolean isSquare, boolean isJumping, boolean isHappy){
        System.out.printf("\n%d is ", liczba);
        System.out.printf("%s", isEven ? "even" : "odd");
        System.out.printf("%s", isBuzz ? ", buzz" : "");
        System.out.printf("%s", isDuck ? ", duck" : "");
        System.out.printf("%s", isPalindromic ? ", palindromic" : "");
        System.out.printf("%s", isGapful ? ", gapful" : "");
        System.out.printf("%s", isSpy ? ", spy" : "");
        System.out.printf("%s", isSunny ? ", sunny" : "");
        System.out.printf("%s", isSquare ? ", square" : "");
        System.out.printf("%s", isJumping ? ", jumping" : "");
        System.out.printf("%s", isHappy ? ", happy" : "");
        System.out.printf("%s", !isHappy ? ", sad" : "");
    }

    public static boolean isMutuallyExclusive(int length, String[]property){
        boolean error = false;
        for(int k = 0 ; k < length - 2 ; k++) {
            for (int kk = k + 1; kk < length - 2; kk++) {
                if (property[k].equals("even") && property[kk].equals("odd")) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                            "There are no numbers with these properties.\n\n", property[k], property[kk]);
                    error = true;
                    break;
                }
                if (property[k].equals("odd") && property[kk].equals("even")) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                            "There are no numbers with these properties.\n\n", property[k], property[kk]);
                    error = true;
                    break;
                }
                if (property[k].equals("duck") && property[kk].equals("spy")) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                            "There are no numbers with these properties.\n\n", property[k], property[kk]);
                    error = true;
                    break;
                }
                if (property[k].equals("spy") && property[kk].equals("duck")) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                            "There are no numbers with these properties.\n\n", property[k], property[kk]);
                    error = true;
                    break;
                }
                if (property[k].equals("sunny") && property[kk].equals("square")) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                            "There are no numbers with these properties.\n\n", property[k], property[kk]);
                    error = true;
                    break;
                }
                if (property[k].equals("square") && property[kk].equals("sunny")) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                            "There are no numbers with these properties.\n\n", property[k], property[kk]);
                    error = true;
                    break;
                }
                if (property[k].equals("happy") && property[kk].equals("sad")) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                            "There are no numbers with these properties.\n\n", property[k], property[kk]);
                    error = true;
                    break;
                }
                if (property[k].equals("sad") && property[kk].equals("happy")) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                            "There are no numbers with these properties.\n\n", property[k], property[kk]);
                    error = true;
                    break;
                }
                if (property[k].equals("-even") && property[kk].equals("-odd")) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                            "There are no numbers with these properties.\n\n", property[k], property[kk]);
                    error = true;
                    break;
                }
                if (property[k].equals("-odd") && property[kk].equals("-even")) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                            "There are no numbers with these properties.\n\n", property[k], property[kk]);
                    error = true;
                    break;
                }
                if (property[k].equals("-duck") && property[kk].equals("-spy")) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                            "There are no numbers with these properties.\n\n", property[k], property[kk]);
                    error = true;
                    break;
                }
                if (property[k].equals("-spy") && property[kk].equals("-duck")) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                            "There are no numbers with these properties.\n\n", property[k], property[kk]);
                    error = true;
                    break;
                }
                if (property[k].equals("-sunny") && property[kk].equals("-square")) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                            "There are no numbers with these properties.\n\n", property[k], property[kk]);
                    error = true;
                    break;
                }
                if (property[k].equals("-square") && property[kk].equals("-sunny")) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                            "There are no numbers with these properties.\n\n", property[k], property[kk]);
                    error = true;
                    break;
                }
                if (property[k].equals("-happy") && property[kk].equals("-sad")) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                            "There are no numbers with these properties.\n\n", property[k], property[kk]);
                    error = true;
                    break;
                }
                if (property[k].equals("-sad") && property[kk].equals("-happy")) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                            "There are no numbers with these properties.\n\n", property[k], property[kk]);
                    error = true;
                    break;
                }
                if (property[k].equals("even") && property[kk].equals("-even")) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                            "There are no numbers with these properties.\n\n", property[k], property[kk]);
                    error = true;
                    break;
                }
                if (property[k].equals("odd") && property[kk].equals("-odd")) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                            "There are no numbers with these properties.\n\n", property[k], property[kk]);
                    error = true;
                    break;
                }
                if (property[k].equals("duck") && property[kk].equals("-duck")) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                            "There are no numbers with these properties.\n\n", property[k], property[kk]);
                    error = true;
                    break;
                }
                if (property[k].equals("spy") && property[kk].equals("-spy")) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                            "There are no numbers with these properties.\n\n", property[k], property[kk]);
                    error = true;
                    break;
                }
                if (property[k].equals("sunny") && property[kk].equals("-sunny")) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                            "There are no numbers with these properties.\n\n", property[k], property[kk]);
                    error = true;
                    break;
                }
                if (property[k].equals("square") && property[kk].equals("-square")) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                            "There are no numbers with these properties.\n\n", property[k], property[kk]);
                    error = true;
                    break;
                }
                if (property[k].equals("happy") && property[kk].equals("-happy")) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                            "There are no numbers with these properties.\n\n", property[k], property[kk]);
                    error = true;
                    break;
                }
                if (property[k].equals("sad") && property[kk].equals("-sad")) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                            "There are no numbers with these properties.\n\n", property[k], property[kk]);
                    error = true;
                    break;
                }
                if (property[k].equals("-even") && property[kk].equals("even")) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                            "There are no numbers with these properties.\n\n", property[k], property[kk]);
                    error = true;
                    break;
                }
                if (property[k].equals("-odd") && property[kk].equals("odd")) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                            "There are no numbers with these properties.\n\n", property[k], property[kk]);
                    error = true;
                    break;
                }
                if (property[k].equals("-duck") && property[kk].equals("duck")) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                            "There are no numbers with these properties.\n\n", property[k], property[kk]);
                    error = true;
                    break;
                }
                if (property[k].equals("-spy") && property[kk].equals("spy")) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                            "There are no numbers with these properties.\n\n", property[k], property[kk]);
                    error = true;
                    break;
                }
                if (property[k].equals("-sunny") && property[kk].equals("sunny")) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                            "There are no numbers with these properties.\n\n", property[k], property[kk]);
                    error = true;
                    break;
                }
                if (property[k].equals("-square") && property[kk].equals("square")) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                            "There are no numbers with these properties.\n\n", property[k], property[kk]);
                    error = true;
                    break;
                }
                if (property[k].equals("-happy") && property[kk].equals("happy")) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                            "There are no numbers with these properties.\n\n", property[k], property[kk]);
                    error = true;
                    break;
                }
                if (property[k].equals("-sad") && property[kk].equals("sad")) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                            "There are no numbers with these properties.\n\n", property[k], property[kk]);
                    error = true;
                    break;
                }
            }
        }
        return error;
    }
}
