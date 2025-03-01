# **Calculator**

This task by CODECRAFT INFOTECH is a simple calculator where you can perform your basic arithmetic operations and calculations in your daily life.

---

## **Features**

#### **Basic Operations and Buttons**
  - Perform baasic operations like Addition(+), Subtraction(-), Multiplication(x) and Division(/).
  - Supports brackets () and decimal numbers.
  - C (Delete Button) to remove the last entered character.
  - AC (Clear All Button) to reset the entire input.
#### **Prevention of Invalid Inputs**
  - Prevents operator at the start(except minus(-)) and multiple operators at the end, e.g. +5, 85+/
  - Restricts multiple operators after a number e.g. 5--8, 81*/74
  - No closing bracket at the start and no consecutive brackets without an operator, e.g. )54, )(, (45)(25), (45(
  - Prevents more closing bracket than opening bracket and avoid operator in a bracket after a number, e.g. ((45))), (15+)
#### **UI and Dynamic Text Resizing**
  - Blinking cursor that blinks continuously.
  - As the number exceeds the screen width, they shrink to a readable size, and horizontal scroll is enabled.
  - The cursor also shrinks proportionally.
  - Clearing the input restores the cursor and text to thier original sizes.
  - Buttons, when clicked, briefly change their shape from circle to square, which enhances user experience.

---

## **Demo**

<video src="https://github.com/user-attachments/assets/7ca480b1-42d0-4486-a79c-ee5c6db6386b" controls="controls" style="max-width: 100%; height: auto;">
    Demo how the app works.
</video>

---

## **Libraries and Methods Used**
1. **Kotlin**: First-class and official programming language for Android development.
2. **Jetpack compose**: A toolkit for building Android apps that uses a declarative API to simplify and speed up UI development
3. **Material Components for Android**: For modular and customizable Material Design UI components.
4. **MVVM**: It is an architectural pattern that separates UI (View) from business logic (ViewModel) and data handling (Model) to improve maintainability and testability.
5. **Kotlin Coroutines**: They are a concurrency framework that simplifies asynchronous programming by allowing tasks to be written sequentially while managing threading and suspensions efficiently.
6. **Mozilla Rhino**: Rhino is a JavaScript engine written fully in Java and managed by the Mozilla Foundation as open source software. I used it for calculations.
7. **Jetpack Compose Animation APIs**: It is used for smooth UI transitions with animate*AsState, AnimatedVisibility, and updateTransition to enhance user experience.
8. **Splash API**: The Splash API in Android provides a customizable launch screen with a smooth transition into the app using the SplashScreen class.

---

## Lessons Learned

While building this app, I learned about:

- **TextMeasurer**: How to use textMeasurer() to dynamically resize the text at runtime.
- **ScrollState**: Understand its properties and behaviour for smooth scrolling.
- **Decoration Box**: How to use decoration box in text fields to customize it with additional decorations.
- **Handling complex calculator cases**: Manage multiple edge cases to ensure accurate input validations and operations.

---

## **Contact**
For any questions or feedback, feel free to contact me at sakhare1181likhit@gmail.com and also connect with me on LinkedIn at www.linkedin.com/in/likhit-sakhare and on Twitter at https://x.com/likhit_sakhare

