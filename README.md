# TopQuiz-Application

## TopQuiz 🧠

### Introduction

**TopQuiz** is an interactive quiz application. This Java-based project combines Object-Oriented (OO) design and programming to create an engaging learning experience for children aged 5-10. It provides an interactive and fun way for users to test their knowledge in various subject areas. The application offers a graphical user interface (GUI) and includes multiple-choice questions as well as interactive gaming modes

### Features 🌟

- 📚 Multiple choice and fill-in-the-blank questions
- 🎮 Fun and interactive games
- 🌍 Subject areas like Geography, Spelling, and more
- 📊 Score tracking and visualization
- 📖 Question bank and database integration

### Target Users 👦👧

TopQuiz is designed for young learners, making it perfect for both home and school use.

### Implementation Requirements 🛠️

- Java API with Collection classes
- GUI toolkit (Java Swing or JavaFX)
- Graphics and animation
- Data persistence (text, serialization, or JDBC)
- Multi-threading for interactive features
- A variety of question types
- Encourage learning by not displaying correct answers for wrong responses.

### How to Use 🚀

1. Clone this repository.
2. Set up your Java development environment.
3. Customize subject areas and questions.
4. Build and run the application.
5. Enjoy interactive learning!

## Running the Project 🏃‍♂️🏃‍♀️

**NOTE:** To run the JAR file, Java Version jdk1.8.0 should be used. Follow these steps to check and set up Java 8:

1. Check whether you have Java 8 installed. Run this command in the terminal:
       java -version

2. If Java 8 is not available, please download and install it from the [Oracle website](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html).

3. Once it is installed, run the following code in the terminal, replacing XXX with the update number of your Java 8 installation:
       export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_XXX.jdk/Contents/Home

4. Check that it has switched to Java 8 by running the `java -version` command again.

5. Once Java version 8 is used, run the JAR file using the following command in the terminal:
       java -jar TopQuiz-master.jar

6. If the JAR is not running, please run the Main class in the source code.


### Getting Started 🚀

To get started with TopQuiz, follow these steps:

1. **Welcome Page**:
   - Upon opening the app, users are greeted with a Welcome page.
   - Users need to enter their username and click the 'Enter Quiz' button to proceed.

2. **Subject Selection Section**:
   - In this section, users can choose from three subject topics:
     1. Geography
     2. Animals
     3. Spellings
   - After selecting a subject, users should press the 'Start Quiz' button.

3. **Quiz Section**:
   - Users are taken to the Quiz Section, where they will encounter various types of questions.
   - The quiz consists of text-based questions (multiple choice) and image-based questions.
   - There are a total of 20 questions randomly selected from the chosen subject topics.
   - Users can switch between topics during the quiz using the Subject Section on the left. (Note: Users must answer a question before changing subjects.)

4. **Timer and Scoring**:
   - Each question has a timer set for 10 seconds, displayed above the question.
   - Correct answers earn users 10 points.
   - Users can view their current score on the right side of the screen.

5. **End Quiz**:
   - Users can choose to end the quiz at any time using the 'End Quiz' button.
   - After ending the quiz, they can view the Score Summary.

6. **Score Summary**:
   - The Score Summary includes:
     1. Total Score
     2. Questions Attempted
     3. Correct Answers
     4. A bar graph representation of the score summary for each subject topic.
   - Users can choose to replay the quiz using the 'Replay' button or end the quiz using the 'Exit' button.

7. **Data Storage**:
   - User data, including username, timestamp, chosen subjects, questions attempted, correct answers, and total score, is stored in a text file after the quiz.

### Usage 📝

1. Clone this repository.
2. Run the TopQuiz application.
3. Enter your username on the Welcome page.
4. Select a subject and start the quiz.
5. Answer questions within the time limit and accumulate points.
6. End the quiz to view your Score Summary.

### Data Storage 💾

User data is stored in a text file, recording quiz performance, allowing users to track their progress and achievements.
