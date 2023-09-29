package akshaykanehitlu.topquiz.database;

import akshaykanehitlu.topquiz.gui.Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class QuestionBank {
	public QuestionBank(String fileName)
	{		
		getAllQuestions(fileName);
	}

	private ArrayList<Question> questionList=new ArrayList<Question>();
	public final String FILE_PATH="/Resources/Data/";
	public final String IMAGE_PATH="/Resources/Images/";
	
	private int questionsAttempted=0;
	private int correctAnswers=0;

	//getters and setters
	public ArrayList<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(ArrayList<Question> questionList) {
		this.questionList = questionList;
	}

	public int getAttemptedQuestions() {
		return questionsAttempted;
	}

	public void incrementAttemptedQuestions() {
		++this.questionsAttempted;
	}

	public int getCorrectAnswers() {
		return correctAnswers;
	}

	public void incrementCorrectAnswers() {
		++this.correctAnswers;
	}

	public void getAllQuestions(String fileName)
	{
		ArrayList<Question> questions=new ArrayList<Question>();
		BufferedReader reader;
		Question newQuestion;
		int i=0;
		{
			try
			{
				//System.out.println("Loading from file " + FILE_PATH + fileName);
				reader=new BufferedReader(new InputStreamReader(Utils.getImagesAsStream(FILE_PATH + fileName)));
				String line;
				while((line=reader.readLine())!=null)
				{
					++i;
					newQuestion=new Question();
					newQuestion.setQuestionID(i);

					QuestionType qType=getQuestionType(line.substring(0, 4));

					if(qType== QuestionType.MULTIPLECHOICE)
					{
						newQuestion.setQuestionType(QuestionType.MULTIPLECHOICE);
						newQuestion.setQuestionText(line.substring(4, line.indexOf("<O>")));
					}
					if(qType==QuestionType.IMAGEQUESTION)
					{
						newQuestion.setQuestionType(QuestionType.IMAGEQUESTION);
						newQuestion.setQuestionText(line.substring(4, line.indexOf("<F>")));
						newQuestion.setQuestionImage(IMAGE_PATH+line.substring(line.indexOf("<F>")+3,line.indexOf("</F>")));//set image path
					}
					if(qType==QuestionType.IMAGEANSWER)
					{
						newQuestion.setQuestionType(QuestionType.IMAGEANSWER);
						newQuestion.setQuestionText(line.substring(4, line.indexOf("<O>")));
					}

					String optString=line.substring(line.indexOf("<O>")+3, line.indexOf("</O>"));
					List<String> options=new ArrayList<String>();
					for(String o : optString.split(":"))
					{
						options.add(o);
					}
					newQuestion.setOptions(options);
					newQuestion.setAnswer(line.substring(line.indexOf("<A>")+3, line.indexOf("</A>")));
					questions.add(newQuestion);
				}
			}
			catch (IOException e)
			{
				System.out.println("Exception:"+e.getMessage());
			}
			catch(Exception e)
			{
				System.out.println("Exception:"+e.getMessage());
			}
			finally{
				setQuestionList(questions);
			}
		}
	}
	public abstract Question getRandomQuestion();

	public double getScorePercentage()
	{
		if(getAttemptedQuestions()==0)
			return 0;
		
		int total= getAttemptedQuestions()*5;
		double score=getCorrectAnswers()*5.0;
		double percent=(score/total)*100.0;
		return percent;
	}

	private QuestionType getQuestionType(String questionType)
	{
		//System.out.println(questionType);
		if(questionType.equals("<MC>"))
			return QuestionType.MULTIPLECHOICE;
		else if(questionType.equals("<QI>"))
			return QuestionType.IMAGEQUESTION;
		else if(questionType.equals("<AI>"))
			return QuestionType.IMAGEANSWER;
		
		return null;
	}
}
