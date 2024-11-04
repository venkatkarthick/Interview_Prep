public class IntermediateJavaQues {
    public static void main(String[] args) {
        //Factorial Program In Java
        System.out.println("Factorial Program In Java : " + factorial(5));

        //Calculate Electricity Bill
        System.out.println("Calculate Electricity Bill : " + calElectricityBill(550));

        //Calculate Average Of N Numbers
        int[] arr = {23, 34, 45, 54};
        System.out.println("Calculate Average Of N Numbers : " + findAvg(arr));

        //Calculate Discount Of Product
        System.out.println("Calculate Discount Of Product : " + findDiscount(5000, 10));

        //Calculate Distance Between Two Points
        System.out.println("Calculate Distance Between Two Points : " + distanceBW2pts(3, 4, 7, 1));

        //Calculate Commission Percentage
        System.out.println("Calculate Commission Percentage : " + getCommissionPercentage(5000, 500));

        //Power In Java
        System.out.println("Power In Java : " + power(2, 4));

        //Calculate Batting Average
        System.out.println("Calculate Batting Average : " + getBattingAverage(1200, 50, 5));

        //Calculate CGPA
        int[] gradePtsArray = {9, 8, 7, 10};
        int[] creditHrsArray = {3, 4, 3, 2};
        System.out.println("Calculate CGPA : " + calculateCGPA(gradePtsArray, creditHrsArray));

        //Compound Interest
        //System.out.println("Compound Interest : " + getCompoundInterest(10000, 5, 4, 3));
    }

//    private static int getCompoundInterest(int amount, int interestRate, int compoundingFrequency, int time) {
//        int accumulatedAmount = (int) (amount * (Math.pow((1 + ( interestRate / compoundingFrequency * 100)), time * compoundingFrequency));
//        System.out.println(accumulatedAmount);
//        return accumulatedAmount - amount;
//    }

    private static double calculateCGPA(int[] gradePts, int[] creditHrs) {
        int totalCreditHrs = 0;
        int totalGradePoints = 0;
        for(int i = 0; i<gradePts.length; i++) {
            totalGradePoints += gradePts[i] * creditHrs[i];
            totalCreditHrs += creditHrs[i];
        }
        return (double) totalGradePoints/totalCreditHrs;
    }

    private static double getBattingAverage(int runsScored, int totalMatches, int notOuts) {
        return (double) runsScored / (totalMatches - notOuts);
    }

    private static int power(int n, int radix) {
        if(radix == 1) return n;
        int res = n;
        for(int i = 2; i <= radix; i++) {
            res *= n;
        }
        return res;
    }

    private static double getCommissionPercentage(int price, int commission) {
        return (double) commission / price * 100;
    }

    private static double distanceBW2pts(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2));
    }

    private static int findDiscount(int price, int discountPercentage) {
        return price - (price * discountPercentage/100);
    }

    private static int findAvg(int[] arr) {
        int sum = 0, n = arr.length;
        for(int a : arr) {
            sum += a;
        }
        return sum/n;
    }


    private static int calElectricityBill(int totalUnits) {
//        0-100 kWh: ₹3 per unit
//        101-300 kWh: ₹5 per unit
//        Above 300 kWh: ₹7 per unit
        int rate = 0;
        if(totalUnits < 100) {
            rate = totalUnits * 3;
        }
        if(totalUnits >= 100 && totalUnits <= 300) {
            rate = 100 * 3 + ((totalUnits - 100) * 5);
        }
        if(totalUnits > 300) {
            rate = 100 * 3 + 200 * 5 + ((totalUnits - 300) * 7);
        }
        return rate;
    }

    private static int factorial(int n) {
        if(n == 0) return 1;
        return n * factorial(n-1);
    }
}
