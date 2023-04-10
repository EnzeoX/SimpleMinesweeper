package com.github;

import com.github.models.ScoresData;
import com.github.utils.ScoreUtils;

public class Test {

    public static void main(String[] args) {
//        ServiceLoader<ChildHandler> handlers = ServiceLoader.load(ChildHandler.class);
//        for (ChildHandler handler : handlers) {
//            System.out.println(handler.getClass().getSimpleName());
//        }
//        System.out.println(System.getProperty("os.name"));
//        System.getProperties().list(System.out);
        ScoreUtils scoreUtils = new ScoreUtils();
        ScoresData data = scoreUtils.loadScores();
        System.out.println(data.toString());
    }

}
