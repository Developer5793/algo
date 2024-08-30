//Clemens Osbahr, Matrikelnummer: 23453430
public class SignalPlotter {

    public static double[] createSamplingPoints(double firstLimit, double secondLimit, int numberOfPoints) {
        if (firstLimit == secondLimit) {
            numberOfPoints = 1;
        }
        double[] basePoints = new double[numberOfPoints];

        if (numberOfPoints == 1) {
            basePoints[0] = secondLimit;
            return basePoints;
        }

        basePoints[0] = firstLimit;
        basePoints[numberOfPoints - 1] = secondLimit;

        if (secondLimit > firstLimit) {
            double addFactor = (secondLimit - firstLimit) / (numberOfPoints - 1);
            for (int i = 1; i < numberOfPoints - 1; i++) {
                basePoints[i] = basePoints[i - 1] + addFactor;
            }
        }

        if (firstLimit > secondLimit) {
            double subFactor = (firstLimit - secondLimit) / (numberOfPoints - 1);
            for (int i = 1; i < numberOfPoints - 1; i++) {
                basePoints[i] = basePoints[i - 1] - subFactor;
            }

        }
        return basePoints;

    }

    public static double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    public static double[] applySigmoidToArray(double[] arrayToConvert) {
        double[] convertedArray = new double[arrayToConvert.length];

        for (int i = 0; i < convertedArray.length; i++) {
            convertedArray[i] = sigmoid(arrayToConvert[i]);
        }
        return convertedArray;
    }

    public static final int FIRST_LIMIT = -10;
    public static final int SECOND_LIMIT = 10;
    public static final int NUMBER_OF_POINTS = 10000;


    public static void plotSigmoid() {
        double[] Samplepoints = createSamplingPoints(FIRST_LIMIT, SECOND_LIMIT, NUMBER_OF_POINTS);
        double[] SigmoidArray = applySigmoidToArray(Samplepoints);
        PlotHelper.plot2D(Samplepoints, SigmoidArray);
    }

    public static void plotEcg() {
        //Erstellung des ECG-Bildes
        double[] ecgSignal = PlotHelper.readEcg("ecg.txt");
        int SAMPLING_RATE = 250;
        int firstLimit = 0;
        float secondLimit = (float) ecgSignal.length / SAMPLING_RATE;
        int numberofPoints = ecgSignal.length;
        double[] ecgTime = createSamplingPoints(firstLimit, secondLimit, numberofPoints);


        //Erstellung des ECG-Bildes mit R-Peaks
        int[] idxRPeaks = PlotHelper.readPeaks("rpeaks.txt");
        double[] rPeaks = new double[idxRPeaks.length];
        double[] timeRPeaks = new double[idxRPeaks.length];


        int idxRPeaksCounter = 0;

        for (int i = 0; i < ecgSignal.length; i++) {

            if (i == idxRPeaks[idxRPeaksCounter]) {
                rPeaks[idxRPeaksCounter] = ecgSignal[i];
                timeRPeaks[idxRPeaksCounter] = ecgTime[i];

                idxRPeaksCounter++;
            }
            if (idxRPeaksCounter == idxRPeaks.length) {
                break;
            }

        }

        //Aufruf der Wiedergabefunktionen
        computeHeartRate(timeRPeaks);
        PlotHelper.plotEcg(ecgTime, ecgSignal, timeRPeaks, rPeaks);


    }

    static void computeHeartRate(double[] basisPoints) {
        System.out.println("Heart Rate: ");
        double Heartrate;
        for (int i = 1; i < basisPoints.length; i++) {
            Heartrate = (basisPoints[i] - basisPoints[i - 1]) * 60;
            System.out.println(String.format("%.2f", Heartrate) + " bpm");
        }
    }

    public static void main(String[] args) {
        plotSigmoid();
        plotEcg();


    }

}
