# StyleMatch — Personalized Fashion Recommendation System

A Java-based fashion recommendation system built as a Data Structures & Algorithms semester project. StyleMatch demonstrates practical implementation of core DSA concepts through an interactive clothing catalog experience.

---

## 📌 Project Overview

StyleMatch allows users to browse a clothing catalog, build outfits, track browse history, and receive smart product recommendations — all powered by fundamental data structures.

---

## 🧱 Data Structures Used

| Data Structure | Used In | Purpose |
|---|---|---|
| **Queue** | `NewArrivalsFeed` | Displays newly arrived products in FIFO order |
| **ArrayList** | `StyleTagPreferences` | Stores and manages user style tags with index-based access |
| **Doubly Linked List** | `BrowseHistory` | Tracks user browsing and purchase history with bidirectional traversal |
| **Stack** | `OutfitBuilder` | Builds outfits with undo (pop) functionality |
| **Graph** | `ProductGraph` | Models product compatibility relationships |
| **Tree** | `CategoryTree` | Organizes products in a hierarchical category structure |
| **HashMap** | `BrowseHistory`, `ProductGraph` | Fast lookups for categories, tags, and product IDs |
| **HashSet** | `ProductGraph` | Tracks visited nodes in BFS/DFS traversals |

---

## ⚙️ Algorithms Implemented

- **Breadth First Search (BFS)** — Finds compatible product recommendations from a starting product
- **Depth First Search (DFS)** — Builds an outfit path through compatible products
- **Tag Scoring Algorithm** — Matches user style preferences against product tags
- **Style Pattern Detection** — Analyzes browse history to detect dominant style preferences

---

## 📁 Project Structure

```
StyleMatch/
│
├── src/
│   └── StyleMatch.java        # Complete source code (all classes in one file)
│
├── products.txt               # Product catalog data (loaded at runtime)
├── README.md                  # Project documentation
```

---

## ▶️ How to Run

### Requirements
- Java JDK 21 or later

### Steps

1. **Clone the repository**
   ```bash
   https://github.com/aalijahriaz-lgtm/StyleMatch
   ```

2. **Ensure `products.txt` is in the same folder as `StyleMatch.java`**

3. **Compile the source code**
   ```bash
   javac StyleMatch.java
   ```

4. **Run the program**
   ```bash
   java StyleMatch
   ```

5. **Follow the on-screen prompts** to enter your name, style tags, browse products, build outfits, and receive recommendations.

---

## 💾 Output Files Generated

| File | Description |
|---|---|
| `<username>_outfit.txt` | Saves the user's current outfit |
| `<username>recommendations.txt` | Saves graph-based product recommendations |

---

## 📦 Sample `products.txt` Format

Each line follows this format:
```
ID, Name, Category, SubCategory, Price, Color, Fit
```

Example:
```
1, Classic White T-Shirt, Men, T-Shirts, 19.99, White, Regular
2, Slim Fit Jeans, Men, Jeans, 49.99, Blue, Slim
```

Valid categories: `Men`, `Women`, `Accessories`

---

## 👨‍💻 Features

- 🛍️ **New Arrivals Feed** — Queue-based display of latest products
- 🏷️ **Style Tag Matching** — ArrayList of preferences scored against product tags
- 📜 **Browse History** — Doubly linked list with style pattern detection
- 👗 **Outfit Builder** — Stack-based builder with undo support and file export
- 🔗 **Smart Recommendations** — Graph BFS + budget filter + category scoring
- 🌲 **Category Browser** — Tree-based browsing with price filtering

---

## 🛠️ Java Version

```
Java JDK 21
```

---

*BS Software Engineering | 4th Semester | Data Structures & Algorithms Project*
