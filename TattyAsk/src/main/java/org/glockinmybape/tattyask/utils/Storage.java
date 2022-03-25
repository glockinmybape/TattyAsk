package org.glockinmybape.tattyask.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.glockinmybape.tattyask.types.Question;

public class Storage {
    public static HashMap<Integer, Question> questions = new HashMap();
    public static int lastID = 1;
    public static int count;
    public static Set<String> requesters = new HashSet();
}
