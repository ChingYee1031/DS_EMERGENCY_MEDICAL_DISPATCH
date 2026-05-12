# Emergency Medical Dispatch System
[cite_start]This project simulates a real-time ambulance dispatch system using graph-based routing and priority-based call handling to reduce response times[cite: 3, 8].

---

## 📌 Prerequisites

* **Java 21** — Latest LTS version
* **Maven** — Included via `./mvnw` wrapper
* **Git** — For version control and collaboration

---

## 🚀 Getting Started

### 1. Clone the repository
In a terminal (or File Explorer → right-click → Open in Terminal):
```bash
git clone https://github.com/ChingYee1031/DS_EMERGENCY_MEDICAL_DISPATCH.git
cd emergency_medical_dispatch

```

### 2. Environment Setup (Optional)

Ensure your `JAVA_HOME` is pointing to **JDK 21**. If using PowerShell, you can set it temporarily for the current session:

```powershell
$env:JAVA_HOME = "C:\Program Files\Java\jdk-21"
$env:Path = "$env:JAVA_HOME\bin;" + $env:Path

```

---

## ⚖️ Repo Rules

* **Do not push directly to `main`.** Always use a branch for features or bug fixes.
* **Feature Workflow:** 1. Create a branch: `git checkout -b feature/your-feature-name`
2. Push the branch: `git push origin feature/your-feature-name`
3. Open a **Pull Request (PR)** on GitHub to merge into `main`.
* **Stability:** Keep `main` stable; merge only after the team has verified the code runs successfully.

---

## 💻 Running the Application

### One command (Maven Wrapper)

From the project root:

```bash
./mvnw spring-boot:run

```

### Manual start (IDE)

* **Main Application:** Locate `EmergencyMedicalDispatchApplication.java` in `src/main/java/` and click **Run**.
* **Access the App:** Open your browser to [http://localhost:8080]
* **To stop:** Press `Ctrl + C` in the terminal

---

## 📂 Project Structure

| Path | Description |
| --- | --- |
| `src/main/java/` | Backend logic including **Dijkstra Pathfinding** and **Priority Queues**.

 |
| `src/main/resources/static/` | Frontend files (HTML, CSS, JavaScript). |
| `src/main/resources/templates/` | Dynamic UI templates (if using Thymeleaf). |
| `pom.xml` | Maven dependencies and project configuration. |

---

## 🛠 Tech Stack

* **Frontend:** HTML5, CSS3, JavaScript
* **Backend:** Java 21, Spring Boot 4.0.6
* **Data Structures (Grading Criteria):**
* 
**Graph:** Dijkstra's Algorithm for computing the shortest route ETA.


* 
**Priority Queue (Max-Heap):** Severity-based prioritization (Severity 1: High, 2: Medium, 3: Low) .


* 
**Queue (FIFO):** Handling for non-urgent calls when all ambulances are busy.



---