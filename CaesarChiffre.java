//Clemens Osbahr, Matrikelnummer: 23453430
public class CaesarChiffre {
    static final String GERMAN_LANGUAGE_PATTERN = "Werden zwei Glasstaebe mit einem Wolltuch gerieben, dann kann man feststellen, dass sich die beiden Staebe gegenseitig abstossen. Wird das gleiche Experiment mit zwei Kunststoffstaeben wiederholt, dann bleibt das Ergebnis gleich, auch diese beiden Staebe stossen sich gegenseitig ab. Im Gegensatz dazu ziehen sich ein Glas und ein Kunststoffstab gegenseitig an. Diese mit den Gesetzen der Mechanik nicht zu erklaerende Erscheinung fuehrt man auf Ladungen zurueck. Da sowohl Anziehung als auch Abstossung auftritt, muessen zwei verschiedene Arten von Ladungen existieren. Man unterscheidet daher positive und negative Ladungen.";
    static final String ENCRYPTED_MESSAGE = "ugjt iwv! fw jcuv fgp eqfg igmpcemv wpf fkt uq twjo wpf gjtg gtyqtdgp. ykg fw ukgjuv, kuv fkgugu xgtuejnwguugnwpiuxgthcjtgp ugjt ngkejv |w mpcemgp. mqornk|kgtvgtg xgthcjtgp ygtfgp kp cpfgtgp xgtcpuvcnvwpigp pcgjgt dgvtcejvgv.";
   // static final String dd = "abcddd fffffffffff";
   // static final String aa = "abcas sdsdsdsdsds";


    public static int getIndexOfMaximumEntry(int[] values) {
        int maximum = values[0];
        int maxIndex = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > maximum) {
                maximum = values[i];
                maxIndex = i;
            }

        }
        return maxIndex;
    };


    public static int[] getHistogram(String Text){
        int[] histogram = new int[255];
        Text = Text.toLowerCase();
        for (int i = 0; i < Text.length(); i++) {
            histogram[Text.charAt(i)]++;
            };
        return histogram;
    }

    static final char SEPARATOR = ' ';

    public static char getSignificantLetter(String Text){

        int[] histogram = getHistogram(Text);

        char significantLetter = (char) getIndexOfMaximumEntry(histogram);

        int quantity=histogram[0];
        int quota =0;

        for (int i = 1; i < histogram.length; i++) {
            if(histogram[i]>quantity){
                quantity = histogram[i];

            }
        };
        int sumOfAppearances = 0;
        for (int i = 0; i < histogram.length; i++) {
            sumOfAppearances += histogram[i];
        };

        float temp = (float)quantity/sumOfAppearances *100;
        quota = Math.round(temp);

        System.out.println("Most significant letter: "+ significantLetter);
        System.out.println("Quantity: "+ quantity + " times (" + quota +" % of whole text)");

        return significantLetter;

    }

    public static int getShift(String encryptedText, String languagePattern){
        char sigOfPattern = getSignificantLetter(languagePattern);;
        char sigOfChiffre = getSignificantLetter(encryptedText);
        int shift = sigOfPattern-sigOfChiffre;
        System.out.println("Most significant letter in the pattern text: "+ sigOfPattern);
        System.out.println("Most significant letter in the encrypted text: "+ sigOfChiffre);
        System.out.println("Resulting shift: "+ shift);
        return shift;

    }

    public static String decode(String encryptedText, String languagePattern){
       int shift =  getShift(encryptedText, languagePattern);
       char[] lettersEncryptedText = encryptedText.toCharArray();
       for (int i = 0; i < lettersEncryptedText.length; i++) {
           if ((int) lettersEncryptedText[i] >= 97+shift && lettersEncryptedText[i] <= 122+shift) {
               lettersEncryptedText[i] = (char) (lettersEncryptedText[i] + shift);
           }
       }
       String decoded = new String(lettersEncryptedText);
       return decoded;
    }


    public static void main(String[] args) {

        int[] testarray = {1, 2, 3, 4, 5, 6, 7, 8, 19, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
        int[] testarray2 = {19, 2, 3, 4, 5, 6, 7, 8, 19, 10, 11, 12, 13, 14, 15, 16, 17, 18, 22};
        int[] testarray3 = {19, 19, 19, 10, 11, 12, 13, 14, 15, 16, 17, 18};
        int[] testarray4 = {1};

        int testresult1;
        int testresult2;
        int testresult3;
        int testresult4;

        testresult1 = getIndexOfMaximumEntry(testarray);
        testresult2 = getIndexOfMaximumEntry(testarray2);
        testresult3 = getIndexOfMaximumEntry(testarray3);
        testresult4 = getIndexOfMaximumEntry(testarray4);

        System.out.println(testresult1);
        System.out.println(testresult2);
        System.out.println(testresult3);
        System.out.println(testresult4);


        String decodedText = decode(ENCRYPTED_MESSAGE, GERMAN_LANGUAGE_PATTERN);

        System.out.println("Unreadable encrypted input text: " + ENCRYPTED_MESSAGE);
        System.out.println("Readable decoded output text: " + decodedText);

        /*Entschl?sselter Text:
            Readable decoded output text: sehr gut! du hast den code geknackt und dir so ruhm und ehre erworben. wie du siehst, ist dieses verschluesselungsverfahren sehr leicht zu knacken. kompliziertere verfahren werden in anderen veranstaltungen naeher betrachtet.
           Schl?ssel:
            "Werden zwei Glasstaebe mit einem Wolltuch gerieben, dann kann man feststellen, dass sich die beiden Staebe gegenseitig abstossen. Wird das gleiche Experiment mit zwei Kunststoffstaeben wiederholt, dann bleibt das Ergebnis gleich, auch diese beiden Staebe stossen sich gegenseitig ab. Im Gegensatz dazu ziehen sich ein Glas und ein Kunststoffstab gegenseitig an. Diese mit den Gesetzen der Mechanik nicht zu erklaerende Erscheinung fuehrt man auf Ladungen zurueck. Da sowohl Anziehung als auch Abstossung auftritt, muessen zwei verschiedene Arten von Ladungen existieren. Man unterscheidet daher positive und negative Ladungen.";
         */

        }

    }

