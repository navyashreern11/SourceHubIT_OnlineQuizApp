package quiz;

import java.util.Scanner;


class Question {
    String question;
    String[] options;
    int correctAnswer; 
    public Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    
    public boolean askQuestion(Scanner sc) {
        System.out.println("\n" + question);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }

        int answer = -1;
        while (true) {
            System.out.print("Enter your answer (1-" + options.length + "): ");
            String line = sc.nextLine().trim();
            try {
                answer = Integer.parseInt(line);
                if (answer >= 1 && answer <= options.length) break;
            } catch (NumberFormatException e) {
                // ignore and re-prompt
            }
            System.out.println("Please enter a valid number between 1 and " + options.length + ".");
        }

        if (answer == correctAnswer) {
            System.out.println("✅ Correct!");
            return true;
        } else {
            System.out.println("❌ Wrong! Correct answer is: " + options[correctAnswer - 1]);
            return false;
        }
    }
}

class Quiz {
    String title;
    Question[] questions;

    public Quiz(String title, Question[] questions) {
        this.title = title;
        this.questions = questions;
    }

   
    public int conduct(Scanner sc) {
        int score = 0;
        System.out.println("\nStarting quiz: " + title);
        for (Question q : questions) {
            if (q.askQuestion(sc)) score++;
        }
        return score;
    }
}

public class QuizApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        
        System.out.print("Enter your name: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) name = "Player";

        Quiz general = new Quiz("General Knowledge", new Question[] {
            new Question("What is the capital of India?",
                    new String[] {"Mumbai", "New Delhi", "Kolkata", "Chennai"}, 2),
            new Question("Which planet is known as the Red Planet?",
                    new String[] {"Earth", "Venus", "Mars", "Jupiter"}, 3),
            new Question("Which is the largest ocean in the world?",
                    new String[] {"Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"}, 4),
            new Question("Who invented the practical incandescent light bulb?",
                    new String[] {"Nikola Tesla", "Thomas Edison", "Albert Einstein", "Isaac Newton"}, 2),
            new Question("Which country is known as the Land of the Rising Sun?",
                    new String[] {"China", "Japan", "South Korea", "Thailand"}, 2)
        });

        Quiz javaBasics = new Quiz("Java Basics", new Question[] {
            new Question("Which company developed Java?",
                    new String[] {"Microsoft", "Sun Microsystems", "Google", "Oracle"}, 2),
            new Question("Which symbol is used for single-line comments in Java?",
                    new String[] {"//", "##", "<!-- -->", "/* */"}, 1),
            new Question("Which data type is used to store decimal numbers in Java?",
                    new String[] {"int", "double", "char", "boolean"}, 2),
            new Question("Which keyword is used to inherit a class in Java?",
                    new String[] {"implement", "extends", "inherits", "super"}, 2),
            new Question("Which language is primarily used for Android app development (traditional)?",
                    new String[] {"Java", "C++", "Python", "Swift"}, 1)
        });

        Quiz[] quizzes = new Quiz[] { general, javaBasics };

        System.out.println("\nHello " + name + "! Please select a quiz:");
        for (int i = 0; i < quizzes.length; i++) {
            System.out.println((i + 1) + ". " + quizzes[i].title + " (" + quizzes[i].questions.length + " questions)");
        }

        int selectedIndex = -1;
        while (true) {
            System.out.print("Enter quiz number (1-" + quizzes.length + "): ");
            String line = sc.nextLine().trim();
            try {
                selectedIndex = Integer.parseInt(line);
                if (selectedIndex >= 1 && selectedIndex <= quizzes.length) break;
            } catch (NumberFormatException e) { }
            System.out.println("Please enter a valid quiz number.");
        }

        Quiz chosenQuiz = quizzes[selectedIndex - 1];

        
        int score = chosenQuiz.conduct(sc);
        int total = chosenQuiz.questions.length;

       
        System.out.println("\n===== Quiz Completed =====");
        System.out.println(name + ", Your Score: " + score + "/" + total);

        double percent = (score * 100.0) / total;
        if (score == total) {
            System.out.println("🎉 Outstanding! You got all questions correct.");
        } else if (percent >= 80.0) {
            System.out.println("🔥 Excellent! Very high score.");
        } else if (percent >= 50.0) {
            System.out.println("👍 Good job! You passed.");
        } else {
            System.out.println("😢 Better luck next time. Keep practicing!");
        }

        sc.close();
    }
}
