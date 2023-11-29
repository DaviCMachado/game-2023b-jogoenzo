package paradigmas.gauchovoador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.Array;

public class Quiz {
    private final Array<Question> questions;

    public Quiz() {
        Json json = new Json();
        FileHandle questionsFileHandle = Gdx.files.internal("questions.json");
        String jsonString = questionsFileHandle.readString();
        JsonValue root = new JsonReader().parse(jsonString);

        questions = new Array<>();

        for (JsonValue questionValue : root.get("questions")) {
            Question question = json.fromJson(Question.class, questionValue.toString());
            questions.add(question);
        }

        questions.shuffle();
    }

    public boolean hasQuestions() {
        return questions.notEmpty();
    }

    public Question nextQuestion() {
        return questions.pop();
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "questions=" + questions +
                '}';
    }
}
