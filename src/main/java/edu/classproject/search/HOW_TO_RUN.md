# 🚀 How to Run the Project

## 🔧 Prerequisites

* Java JDK 17 or above
* Maven installed
* Command Prompt / PowerShell / Terminal

---

## 📦 Build the Project

Run the following command in the project root directory:

```bash
mvn package
```

### ✅ Expected Build Output

* `BUILD SUCCESS`
* JAR file created in `target/` folder

---

## ▶️ Run the Application

```bash
java -cp target/classes edu.classproject.bootstrap.SearchDemo
```

---

## 📊 Expected Output

```
Search: Burger
- Hot Burger Hub (id=r3) matched by name -> Spicy Burger
- Burger Palace (id=r4) matched by name -> Veg Burger
- Burger King (id=r1) matched by name -> Cheeseburger

Search: pizza
- Pizza Palace (id=r2) matched by name -> Margherita Pizza

Search: garlic
- Pizza Palace (id=r2) matched by item -> Garlic Bread
```

---

## 🧪 Run Tests (Optional)

```bash
mvn test
```

### ✅ Expected Test Result

* Tests run successfully
* No failures or errors

---

## 📝 Notes

* Search is **case-insensitive**
* Supports search by:

  * Restaurant name
  * Menu items
* Output includes:

  * Restaurant name
  * ID
  * Matching criteria

---
