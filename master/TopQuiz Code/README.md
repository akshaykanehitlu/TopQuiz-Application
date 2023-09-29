**README FILE**

The following TopQuiz Application is a desktop application that uses a graphical user interface (GUI) and 
enables users to assess their expertise in a particular subject. The assessment methods used by the application 
range from traditional multiple-choice questions to interactive gaming modes that engage the user in a fun and 
challenging way.

The TopQuiz App opens the Welcome page, where user has to enter the username and press the 'Enter Quiz' button.
This takes the user to the Subject Selection Section.

The Subject Selection Section contains 3 subject topics to choose from, which are:
1. Geography
2. Animals
3. Spellings

After selecting a subject user has to press the 'Start Quiz' button. This takes the user to the Quiz Section.

The user has to answer Text based questions (Multiple choice) and Image based question.

There are 20 questions randomly selected from the subject topics.
The user can switch between topics during the quiz using the Subject Section on the left. 
(NOTE - User has to answer a question and then change subject in order to proceed)

A timer of 10 seconds is set for each question. The countdown can be seen above the question.

Each correct answer carries 10 points. 
The user can view the score during the test on the right.
User can leave the quiz anytime using the 'End Quiz' button and view the Score Summary.

Once the quiz is over, the Score Summary is displayed which contains:
1. Total Score
2. Questions Attempted
3. Correct Answers
4. Bar graph representation of the score summary for each Subject topic

User can replay the quiz using the 'Replay' button.

User can end quiz using the 'Exit' button.

Once the quiz is over, User data is stored in a text file which displays the username, timestamp, subjects chose,
questions attempted, number of correct answers and total score.

**NOTE:** To run jar file, Java Version jdk1.8.0 should be used. 
To check the java version of the system please run the following the terminal and do the following changes:

- check whether you have Java 8 installed
    run this command in terminal:       java -version
- if Java 8 is not available, please download and install it from Oracle website.

- Once it is installed, run the following code in terminal:   export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_XXX.jdk/Contents/Home
  Replace XXX with the update number of your Java 8 installation.

- Check that it has switched to Java 8 by running the java -version command again.

Once the Java version 8 is used, run the jar file using the command in terminal:    java -jar TopQuiz-master.jar













