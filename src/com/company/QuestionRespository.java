package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.KeyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

public class QuestionRespository{
    private ArrayList<Question> questions;
    private MajorRepository majorRepository;

    public QuestionRespository(MajorRepository majorRepository)
    {
        questions =new ArrayList<>();
        this.majorRepository=majorRepository;
    }

    /**
     * Method read majors from file with name fileName and then save it to majors pole
     * @param fileName
     * @throws KeyException
     */
    public void readData(String fileName)throws KeyException {



        try(Scanner in=new Scanner(new File(fileName));)
        {
            in.nextLine(); //skip header
            String record=null;
            while (in.hasNextLine()) {
                record=in.nextLine();
                String[] recordContent=record.split(",");
                String[]answers={recordContent[1],recordContent[2],recordContent[3]};
                Question newQuestion=new Question(recordContent[4],recordContent[0],answers);
                normalizeQuestions(newQuestion);
                questions.add(newQuestion);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        Logger.getGlobal().info("questions has been load");

    }

    /**
     * line in question has max 34 characters. Moreover first line has exact 34 characters
     * @param question
     */
    private void normalizeQuestions(Question question)
    {
        StringBuilder questionBulider=new StringBuilder(question.question);
        if(question.question.length()>34)
        {

            breakLine(questionBulider);

        }
        else
        {
            int questionSize=questionBulider.length();
            for(int i=0;i<34-questionSize;i++)
            {
                questionBulider.append(" ");
            }
        }
        question.question=questionBulider.toString();
    }

    private void breakLine(StringBuilder questionBulider) {
        int rows=2;
        int index = Math.abs(questionBulider.length() / rows);
        while (index > 33)
        {
            rows++;
            index=Math.abs(questionBulider.length() / rows);
        }
        for(int row=1;row<rows;row++) {

            for (int i = index*row; i != -1; i--) {

                index = questionBulider.indexOf(" ", i);
                if (index != -1) {
                    break;
                }
            }
            questionBulider.deleteCharAt(index);
            questionBulider.insert(index,"\n");
        }
        questionBulider.deleteCharAt(index);
        questionBulider.insert(index,"\n");
        for(int i=0;i<33-index;i++)
        {
            questionBulider.insert(index," ");
        }
    }

    public Question getNextQuestion() {
        if(questions.isEmpty())
        {
            return null;
        }
        Map<Question,Integer>questionsRating=getQuestionsRating();
        if(questionsRating.isEmpty())
        {
            return null;
        }
        Question bestQuestion=questions.get(0);
        int bestRaiting=-1;
        for(Map.Entry<Question,Integer>raiting:questionsRating.entrySet())
        {
            if(bestRaiting<raiting.getValue())
            {
                bestQuestion=raiting.getKey();
                bestRaiting=raiting.getValue();
            }
        }

        questions.remove(bestQuestion);
        return bestQuestion;
    }
    public Map<Question,Integer> getQuestionsRating()
    {
        Map<Question,Integer>questionsRating=new HashMap<>();
        
        for(Major major:majorRepository.getMajors()) {

            for (Question question : questions) {
                String answer=majorRepository.translateFeatureValue(question.answer[0]);
                if (major.hasFeature(question.feature, answer) && major.active)
                    questionsRating.put(question, questionsRating.getOrDefault(question, 0) + 1);
            }
        }

        return questionsRating;
    }
}
