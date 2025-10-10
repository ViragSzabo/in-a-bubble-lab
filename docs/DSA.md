# 🧠 Data Structures & Algorithms within In A Bubble
## 📖 Overview
The **In A Bubble Lab** project is designed as an experimental playground for exploring custom data structures and algorithmic logic through both code and visualisation.
While inspired by classical Java collections and data structures, each implementation carries its own personality — optimised for simplicity, transparency, and GUI-based demonstration.

## ⚙️ Implemented Data Structures
### 1. FlowMaster - Custom Queue (Stream Controller Team)
**Concept:** It represents a _Queue (FIFO)_ to manage ordered data flow.
#### Core Operations
* _add(T item)_ → Enqueues an element
* _poll()_ → Dequeues and returns the next element
* _peek()_ → Returns next without removal
#### Algorithmic Notes
* **Time complexity:** O(1) for add/remove _(ArrayList-based)_
* Ideal for simulating processes or pipelines
##### Usage in Lab
Represents how _bubbles (data units)_ flow through different phases of an experiment.

### 2. PileDriver - Custom Stack (Layered Storage Team)
**Concept:** It represents a _Stack (LIFO)_ structure used to model hierarchical/layered data.
#### Core Operations
* _push(T item)_ → Adds to the top
* _pop()_ → Removes from the top
* _peek()_ → Returns the top element without removing
#### Algorithmic Notes
* **Time complexity:** O(1) for push/pop
* Often used in recursive or undo-type logic
##### Usage in Lab
Used to store intermediate results during dataset manipulation/simulation steps.

### 3. UniqueVault - Custom HashSet (Uniqueness Enforcer Team)
**Concept:** It represents a self-implemented _HashSet_ structure with simplified hashing & collision management logic.
#### Core Operations
* Computes a hash
* Uses an internal array/list for storage
* Ensures uniqueness by comparing before insertion
#### Algorithmic Notes
* **Time complexity:** O(1) for add/check/remove
* **Worst case:** O(n) if hash collisions are high
##### Usage in Lab
Used for ensuring datasets/experimental samples contain unique elements.

## 🧩 Algorithms
| Algorithm         | Purpose                              | Status | Notes                                         |
| ----------------- | ------------------------------------ | ------ | --------------------------------------------- |
| **Bubble Sort**   | Visual sorting of elements           | ✅      | Great for visualization and educational demos |
| **Smart Bubble Sort**    | A more efficient way of sorting elements    | ✅     | Great for visualization and educational demos                            |
| **Linear Search** | Simple element lookup                | ✅      | Demonstrates algorithmic flow                 |
| **Binary Search** | Optimized searching in sorted arrays | ✅     | To be added in advanced module                |

## 🧮 Design Philosophy
Every structure and algorithm is designed to be:
* **Transparent:** Easy to trace and visualise.
* **Customizable:** Open to modification by contribution.
* **Educational:** Code readability is prioritised over raw performance.
