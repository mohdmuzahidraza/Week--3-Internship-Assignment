import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StudentMarksRecord {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> marksList = new ArrayList<>();

        System.out.print("How many students? ");
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            System.out.print("Enter marks of Student " + i + ": ");
            int marks = sc.nextInt();
            marksList.add(marks);
        }

        int highest = Collections.max(marksList);
        int lowest  = Collections.min(marksList);

        System.out.println("\n--- Student Marks Record ---");
        for (int i = 0; i < marksList.size(); i++) {
            System.out.println("Student " + (i + 1) + " : " + marksList.get(i));
        }

        System.out.println("\nHighest Marks : " + highest);
        System.out.println("Lowest  Marks : " + lowest);

        sc.close();
    }
}