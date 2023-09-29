package akshaykanehitlu.topquiz.database;

import java.util.ArrayList;
import java.util.Random;
public class SpellingsBank extends QuestionBank{

	public SpellingsBank()
	{
		super("Spellings.txt");
		uniqueQuestionList=getQuestionList();
	}
			private static ArrayList<Question> uniqueQuestionList=new ArrayList<Question>();
		
		@Override
		public Question getRandomQuestion() {
			if(uniqueQuestionList.size()==0)//attempted all questions in selected topic
				return null;

			int randomIndex=new Random().nextInt(uniqueQuestionList.size());
			Question newQuestion=uniqueQuestionList.get(randomIndex);
			uniqueQuestionList.remove(randomIndex);
			
			return newQuestion;
		}

}

