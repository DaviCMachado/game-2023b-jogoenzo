package paradigmas.gauchovoador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.ObjectMap;

public class Quiz {
    ObjectMap<String, Question> questions;

    public Quiz() {
        Json json = new Json();
        FileHandle questionsFileHandle = Gdx.files.internal("questions.json");
        String jsonString = questionsFileHandle.readString();
        JsonValue root = new JsonReader().parse(jsonString);

        questions = new ObjectMap<>();

        for (JsonValue questionValue : root.get("questions")) {
            Question question = json.fromJson(Question.class, questionValue.toString());
            questions.put(Integer.toString(question.getId()), question);
        }
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "questions=" + questions +
                '}';
    }
}
