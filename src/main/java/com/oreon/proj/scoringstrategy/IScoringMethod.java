package com.oreon.proj.scoringstrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public interface IScoringMethod {

	public Integer score(
			com.oreon.proj.questionnaire.AnsweredQuestionnaire answeredQuesionnaire);

}
