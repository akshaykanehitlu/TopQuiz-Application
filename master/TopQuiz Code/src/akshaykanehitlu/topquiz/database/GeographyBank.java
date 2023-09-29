package akshaykanehitlu.topquiz.database;

import java.util.ArrayList;
import java.util.Random;

public class GeographyBank extends QuestionBank{
	
	public GeographyBank()
	{
		super("Geography.txt");
		uniqueQuestionList=getQuestionList();
	}
	private static ArrayList<Question> uniqueQuestionList=new ArrayList<Question>();
	@Override
	public Question getRandomQuestion() {
		if(uniqueQuestionList.size()==0)
			return null;

		int randomIndex=new Random().nextInt(uniqueQuestionList.size());

		Question newQuestion=uniqueQuestionList.get(randomIndex);
		uniqueQuestionList.remove(randomIndex);
		
		return newQuestion;
	}

}
