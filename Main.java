import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Process> processes = new ArrayList<>();

        // Sample Test Data: (PID, Arrival Time, Burst Time, Priority)
        processes.add(new Process(1, 0, 8, 2));
        processes.add(new Process(2, 1, 4, 1));
        processes.add(new Process(3, 2, 9, 3));
        processes.add(new Process(4, 3, 5, 2));

        // Print loaded processes
        System.out.println("Processes Loaded:");
        for (Process p : processes) {
            System.out.printf("PID=%d Arrival=%d Burst=%d Priority=%d\n", p.pid, p.arrivalTime, p.burstTime, p.priority);
        }

        // Run Schedulers
        Scheduler.runSRTF(processes);
        Scheduler.runMLFQ(processes);
    }
}
