package com.oreon.proj.scoringstrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class MfdaScoringMethodBase
		implements
			com.oreon.proj.scoringstrategy.IScoringMethod {

	/**
	 * 
	

	 * @param  answeredQuesionnaire  AnsweredQuestionnaire  
	 *
	 * @return Integer
	 */
	//@Restrict("#{s:hasPermission('MfdaScoringMethod','score')}")
	public Integer score(
			com.oreon.proj.questionnaire.AnsweredQuestionnaire answeredQuesionnaire) {

		/*
		//logger.info("starting score ......" + 		      answeredQuesionnaire
		);
		 */

		Integer result = doScore(answeredQuesionnaire);

		/*
		logger.info("finished score ....."  + " returning - " + result );
		 */
		return result;
	}

	/**
	 * []
	 */

	protected Integer doScore(
			com.oreon.proj.questionnaire.AnsweredQuestionnaire answeredQuesionnaire) {
		return null;
	}

}
