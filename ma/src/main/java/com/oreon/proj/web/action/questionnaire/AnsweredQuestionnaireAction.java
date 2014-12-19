package com.oreon.proj.web.action.questionnaire;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.annotations.Name;

import com.oreon.proj.questionnaire.Answer;
import com.oreon.proj.questionnaire.AnsweredQuestion;
import com.oreon.proj.questionnaire.Question;
import com.oreon.proj.questionnaire.Section;

//@Scope(ScopeType.CONVERSATION)
@Name("answeredQuestionnaireAction")
public class AnsweredQuestionnaireAction
		extends
			AnsweredQuestionnaireActionBase implements java.io.Serializable {
	
	
	public void selectionChanged(){
		if(isNew()){
			
			if(getInstance().getAnsweredQuestions() != null)
				getInstance().getAnsweredQuestions().clear();
			else
				getInstance().setAnsweredQuestions(new ArrayList<AnsweredQuestion>());
				
			List<Section> sections = getInstance().getQuestionnaire().getSections();
			for (Section section : sections) {
				
				List<Question> questions = section.getQuestions();
				
				for (Question question : questions) {
					
					AnsweredQuestion aq = new AnsweredQuestion();
					aq.setQuestion(question);
					getInstance().addAnsweredQuestion(aq);
					
					if(aq.getAnswers() == null){
						aq.setAnswers(new ArrayList<Answer>());
						aq.addAnswer(new Answer());
					}
				}
				
			}
			
			
		}
	}
	
	
	@Override
	public String save() {
		
		return super.save();
	}

}
