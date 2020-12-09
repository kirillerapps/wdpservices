package com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.queries;

import com.planonsoftware.platform.backend.querybuilder.v3.IJoin;
import com.planonsoftware.platform.backend.querybuilder.v3.IQueryBuilder;
import com.planonsoftware.platform.backend.querybuilder.v3.IQueryDefinition;
import com.planonsoftware.platform.backend.querybuilder.v3.IQueryDefinitionContext;

public class GetOrderBOByCode implements IQueryDefinition
{

@Override
    public void create(IQueryBuilder aBuilder, IQueryDefinitionContext aContext) {         
       aBuilder.addSearchField("BusinessObjectRef","OrderNumber");   
        IJoin answerOption = aBuilder.addInnerJoin("SingleSelectAnswerRef");
        IJoin questionnaireRef = aBuilder.addInnerJoin("QuestionnaireRef");
        questionnaireRef.addSelectField("Code","QuestionnaireCode");
        questionnaireRef.addSelectField("Name","QuestionnaireName");

        IJoin questionRef = aBuilder.addInnerJoin("QuestionRef");
        questionRef.addSelectField("Code","QuestionCode");
        questionRef.addSelectField("Name","QuestionName");

        answerOption.addSelectField("Code","AnswerOptionCode");
        answerOption.addSelectField("Name","AnswerOptionName");
    }
   
    @Override
    public String getBOName() {
        return "BaseOrderAnswerLine";
    }
    
}
