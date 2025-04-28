import java.util.*;

public class Scheduler {

    public static void runSRTF(List<Process> processes) {
        System.out.println("\n--- Running Shortest Remaining Time First (SRTF) ---");

        List<Process> processList = new ArrayList<>();
        for (Process p : processes) {
            processList.add(new Process(p.pid, p.arrivalTime, p.burstTime, p.priority));
        }

        int time = 0, completed = 0;
        int n = processList.size();
        int idleTime = 0;

        while (completed != n) {
            Process current = null;
            int minRemainingTime = Integer.MAX_VALUE;

            for (Process p : processList) {
                if (p.arrivalTime <= time && p.remainingTime > 0 && p.remainingTime < minRemainingTime) {
                    minRemainingTime = p.remainingTime;
                    current = p;
                }
            }

            if (current == null) {
                idleTime++;
                time++;
                continue;
            }

            if (current.startTime == -1) {
                current.startTime = time;
            }

            current.remainingTime--;
            time++;

            if (current.remainingTime == 0) {
                current.finishTime = time;
                completed++;
            }
        }

        calculateAndPrintMetrics(processList, time, idleTime);
    }

    public static void runMLFQ(List<Process> processes) {
        System.out.println("\n--- Running Multi-Level Feedback Queue (MLFQ) ---");

        List<Process> processList = new ArrayList<>();
        for (Process p : processes) {
            processList.add(new Process(p.pid, p.arrivalTime, p.burstTime, p.priority));
        }

        Queue<Process> queue1 = new LinkedList<>(); // Highest priority queue
        Queue<Process> queue2 = new LinkedList<>(); // Lower priority queue
        int time = 0, completed = 0;
        int n = processList.size();
        int idleTime = 0;
        int quantum1 = 4;
        int quantum2 = 8;

        while (completed != n) {
            for (Process p : processList) {
                if (p.arrivalTime == time) {
                    queue1.add(p);
                }
            }

            Process current = null;

            if (!queue1.isEmpty()) {
                current = queue1.poll();
                int execTime = Math.min(current.remainingTime, quantum1);
                if (current.startTime == -1) current.startTime = time;

                for (int i = 0; i < execTime; i++) {
                    current.remainingTime--;
                    time++;
                    for (Process p : processList) {
                        if (p.arrivalTime == time) queue1.add(p);
                    }
                    if (current.remainingTime == 0) break;
                }

                if (current.remainingTime > 0) queue2.add(current);
                else current.finishTime = time;
                if (current.remainingTime == 0) completed++;
                continue;
            }

            if (!queue2.isEmpty()) {
                current = queue2.poll();
                int execTime = Math.min(current.remainingTime, quantum2);
                if (current.startTime == -1) current.startTime = time;

                for (int i = 0; i < execTime; i++) {
                    current.remainingTime--;
                    time++;
                    for (Process p : processList) {
                        if (p.arrivalTime == time) queue1.add(p);
                    }
                    if (current.remainingTime == 0) break;
                }

                if (current.remainingTime > 0) queue2.add(current);
                else current.finishTime = time;
                if (current.remainingTime == 0) completed++;
                continue;
            }

            idleTime++;
            time++;
        }

        calculateAndPrintMetrics(processList, time, idleTime);
    }

    private static void calculateAndPrintMetrics(List<Process> processList, int totalTime, int idleTime) {
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;
        double totalResponseTime = 0;

        for (Process p : processList) {
            int turnaroundTime = p.finishTime - p.arrivalTime;
            int waitingTime = turnaroundTime - p.burstTime;
            int responseTime = p.startTime - p.arrivalTime;

            totalWaitingTime += waitingTime;
            totalTurnaroundTime += turnaroundTime;
            totalResponseTime += responseTime;
        }

        double avgWaitingTime = totalWaitingTime / processList.size();
        double avgTurnaroundTime = totalTurnaroundTime / processList.size();
        double cpuUtilization = ((totalTime - idleTime) / (double) totalTime) * 100;
        double throughput = processList.size() / (double) totalTime;

        System.out.printf("Average Waiting Time: %.2f\n", avgWaitingTime);
        System.out.printf("Average Turnaround Time: %.2f\n", avgTurnaroundTime);
        System.out.printf("CPU Utilization: %.2f%%\n", cpuUtilization);
        System.out.printf("Throughput: %.2f processes/unit time\n", throughput);
    }
}
