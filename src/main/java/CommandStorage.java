import java.util.ArrayList;

public class CommandStorage {


    public ArrayList<String> invalidStrings;

    CommandStorage() {
        this.invalidStrings = new ArrayList<>();
    }


    public void addInvalidString(String invalid_string) {
        this.invalidStrings.add(invalid_string);
    }


    public ArrayList<String> getInvalidStrings() {
        return this.invalidStrings;
    }
}
