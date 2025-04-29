CPU Scheduling Simulator
---
Project Overview
This project is a CPU Scheduling Simulator created for CS 3502 (Operating Systems) at OwlTech Systems.
It implements two advanced scheduling algorithms from scratch:

- Shortest Remaining Time First (SRTF)

- Multi-Level Feedback Queue (MLFQ)

The simulator measures and compares scheduling performance using key metrics such as:

- Average Waiting Time (AWT)

- Average Turnaround Time (ATT)

- CPU Utilization

- Throughput

- (Optional) Response Time
---
Technologies Used
---
Language: Java

Development Environment: Any (tested with VSCode and IntelliJ)

No external libraries required

---
Code Structure
---
Main.java	Entry - point. Loads sample processes and runs both SRTF and MLFQ scheduling algorithms.
Process.java -	Defines the Process class holding process attributes (PID, arrival time, burst time, priority, etc.).
Scheduler.java -	Contains logic for SRTF and MLFQ scheduling and computes performance metrics for analysis.

---
Sample Output
-
Processes Loaded:
PID=1 Arrival=0 Burst=8 Priority=2
PID=2 Arrival=1 Burst=4 Priority=1
PID=3 Arrival=2 Burst=9 Priority=3
PID=4 Arrival=3 Burst=5 Priority=2

--- Running Shortest Remaining Time First (SRTF) ---
Average Waiting Time: 6.50
Average Turnaround Time: 13.00
CPU Utilization: 100.00%
Throughput: 0.15 processes/unit time

--- Running Multi-Level Feedback Queue (MLFQ) ---
Average Waiting Time: 11.75
Average Turnaround Time: 18.25
CPU Utilization: 100.00%
Throughput: 0.15 processes/unit time

---
Project Highlights
-
✅ Two New Algorithms: SRTF and MLFQ implemented from scratch.

✅ Performance Evaluation: Automatically measures AWT, ATT, CPU Utilization, and Throughput.

✅ Simple and Expandable: Easy to modify or add additional scheduling algorithms (e.g., HRRN, Aging, Dynamic Quantum).

---
Notes
-
This project was built from scratch, not based on the provided Windows Forms C# starter code.

Focus was on console-based simplicity and cross-platform compatibility.

Developed to simulate real-world scheduling trade-offs like efficiency, fairness, and responsiveness.
 ---
License
-
This project is open-sourced.

---
Author
-
Shivam Bavariya




