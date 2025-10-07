![Build Status](https://img.shields.io/github/actions/workflow/status/ViragSzabo/in-a-bubble-lab/build.yml?branch=main)
![Contributions Welcome](https://img.shields.io/badge/contributions-welcome-brightgreen)
![Open Issues](https://img.shields.io/github/issues/ViragSzabo/in-a-bubble-lab)
![Open PRs](https://img.shields.io/github/issues-pr/ViragSzabo/in-a-bubble-lab)
![Version](https://img.shields.io/github/v/release/ViragSzabo/in-a-bubble-lab)
![License](https://img.shields.io/github/license/ViragSzabo/in-a-bubble-lab)

# 🫧 In A Bubble Lab
Welcome to the lab where three teams of data structures — Queue, Stack, and HashSet — work together _(and sometimes compete)_ to organise, search, and sort things out in the middle of a chaos of datasets. Bubble and SmartBubble are racing to see who tidies things up the quickest, while Sequential and Binary Search compete to see who finds their targets the fastest.
Think of it as a lab experiment: each data structure has its own _personality_, each algorithm has its own _strategy_ to work, and you as the user are the lab supervisor controlling the experiments.

## Project Organiser
Track tasks and the test plan in a Notion workspace:
- [Tasks Tracker](https://www.notion.so/27ee1fdd31ff80c2a94cf8293c73016c?pvs=21)
- [Timeline](https://www.notion.so/Timeline-27ee1fdd31ff80a1a237db5b74cf8f61?pvs=21)
- [Test Plan](https://www.notion.so/Test-Plan-27ee1fdd31ff80af8d62da415b80880a?pvs=21)
- [Datasets Library](https://www.notion.so/Datasets-Library-27ee1fdd31ff803184c0c3e30967a8a5?pvs=21)
- [Issues/Bugs Tracker](https://www.notion.so/Issues-Bugs-Tracker-27ee1fdd31ff800686a2faeda815dd22?pvs=21)

# 🧭 Table of Contents
1. [The Cast](#the-cast)  
   - [Data Structures: The Teams](#data-structures-the-teams)  
   - [Algorithms: The Strategies](#algorithms-the-strategies)  
   - [GUI: The Control Panel](#gui-the-control-panel)  
   - [Dataset: The Test Subjects](#dataset-the-test-subjects)  
2. [🧪 The Lab Schedule](#-the-lab-schedule)  
3. [⚙️ How To Run The Lab](#️-how-to-run-the-lab)  
4. [🛠 Recommended Tools](#-recommended-tools)  
5. [📊 Rules](#-rules)  
6. [🔮 Further Extensions](#-further-extensions)  
7. [📜 License](#-licensed)  
8. [🚫 Ignore Private Data](#-ignore-private-data)  
9. [✨ Author](#-author)

# 🎭 The Cast
Meet the main participants of the lab experiment within the In A Bubble Lab.
## Data Structures: The Teams
Custom-built, generic data structures that are ready to take on any dataset thrown their way.
### Queue: The Steady Organiser
- Works First-In-First-Out (FIFO).
- **Methods:** enqueue, dequeue, peek, isEmpty, size.
- Keeps the order intact, like a line of lab assistants following the rules.
### Stack: The Last-Minute Hero
- Works Last-In-First-Out (LIFO)
- **Methods:** push, pop, peek, isEmpty.
- Great at handling sudden changes — always tackling the newest tasks first.
### HashSet: The Efficient Collector
- Ensures uniqueness, fast lookup, and collision handling.
- **Methods:** add, remove, contains.
- Does not believe in keeping drafts around the labs.
## Algorithms: The Strategies
Algorithms are the strategies our characters use to perform their tasks.
### Sorting: The Clean-Up Crew
Arrange the dataset in order, based on object comparison or custom comparators.
**Bubble:** The classic cleaner, based on object comparison or custom comparators, aka O(n²).
**Smart Bubble:** The optimised cleaner, based on stopping early when things are already tidy.
### Searching: The Trackers
Find elements in the dataset in order, based on object comparison or custom comparators.
**Sequential:** The one who scans everything methodically, works from anywhere.
**Binary:** The sharp, efficient and picky one, as it only works when the lab is sorted out.
## GUI: The Control Panel
Where you can see the **execution times**, observe closely as the **supervisor** who works fastest and most efficiently. See how the strategies are being performed. The panel lets you:
**Import:** Bring new experiments/ideas (datasets) into the lab.
**Convert:** Assign datasets to Queue, Stack, or HashSet.
**Execute:** Run sorting or searching experiments on the full dataset or a portion of it.
## Dataset: The Test Subjects
- **Use:** a collection of items that the lab manipulates (datasets).
- **Convert:** load into a specific team (data structure) for the experiment.
- **Apply:** transform between structures to test efficiency.
- **Algorithms:** operate on them to demonstrate the flexibility and power of In A Bubble Lab.

# 🧪 The Lab Schedule
Every good experiment follows a schedule. In The Bubble Lab, here's how the teams will come into work in the upcoming days:
1. **🧱⚙️ Phase 1: Building The Teams (Data Structures)**
_Day 1, Morning Shift (9:00 - 12:00)_
Construction of Queue, Stack, and HashSet with the necessary getters/setters, methods for manipulation (get, add, delete, etc.) and performance of mutual comparisons per field. Training with the basics first, and learn how to handle datasets.
_Lunch Break (12:00 - 13:00)_
2. **🧹🐾 Phase 2: Training The Strategies (Algorithms)**
_Day 1, Afternoon Shift (13:00 - 17:00)_
The Clean-Up Crew (Bubble, Smart Bubble) starts practising their routine. 
Following, the Researchers start tracking the items. Prepare to work with the data structures and generic types.
3. **🎛️🖥️ Phase 3: Equipping The Lab (GUI)**
_Day 2, Morning Shift (9:00 - 12:00)_
The Control Panel is getting installed — where the performance stats (execution times) are visible — and the supervisor imports datasets, assigns them to teams, and then triggers strategies. 
4. **🧬📊 Phase 4: Inviting The Test Subjects (Datasets)**
_Day 2, Afternoon Shift (13:00 - 17:00)_
The Test Subjects arrive at the lab to be converted, shuffled, and applied across all teams mentioned above. Experiments are running efficiently, and results can be observed.
5. **📜🎉 Phase 5: Presenting The Final Analysis (Interview)**
_Day 3, Morning Shift (9:00 - 12:00)_
The In The Bubble Lab demonstrates a good work ethic. The supervisor is also satisfied with the teams, the crew members, the trackers and the strategists. At the end, everyone gets their final report card.

# ⚙️ How To Run The Lab
1. 1. Clone the repository:
   git clone https://github.com/ViragSzabo/in-a-bubble-lab.git
2. Open the project in your IDE (e.g., IntelliJ, Eclipse).
3. Build and run the application.
4. Use the GUI Control Panel to import datasets and start experiments.

# 🛠 Recommended Tools
- Java (Generics, Collections, OOP principles)
- Swing / JavaFX (GUI)
- Custom-built data structures and algorithms

# 📊 Rules
- **Three data structures (Queue, Stack, HashSet)** – custom-built with generics.
- **Four algorithms (Bubble, Smart Bubble, Sequential Search, Binary Search)** – all functional and generic.
- **Comparisons and custom comparators** supported.
- **GUI** with import, conversion, and execution of algorithms.
- **Execution times** displayed in seconds (rounded to the nearest tenth).
- **Dataset** import, conversion, and application across all data structures.

# 🔮 Further Extensions
- Visualisation of sorting & searching algorithms.
- Adding more algorithms (Quicksort, MergeSort, Hash-Based Search).
- Exporting results and performance logs.

# 📜 Licensed 
This project is licensed under the MIT License – see the [LICENSE](LICENSE) file for details.

# 🚫 Ignore Private Data
The repository includes a `.gitignore` file to prevent unnecessary files (IDE configs, build artefacts, etc.) from being tracked.

# ✨ Author
Developed by Virag Szabo as part of the Data Structures & Algorithms final assignment.
