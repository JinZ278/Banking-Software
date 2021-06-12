package banking;

import java.util.ArrayList;

public class CommandStorage {


    protected ArrayList<String> allStrings;
    protected ArrayList<String> invalidStrings;

    CommandStorage() {
        this.allStrings = new ArrayList<>();
        this.invalidStrings = new ArrayList<>();
    }


    public void addInvalidString(String invalid_string) {
        this.invalidStrings.add(invalid_string);
    }


    public ArrayList<String> getInvalidStrings() {
        return this.invalidStrings;
    }

    public ArrayList<String> getAllStrings(ArrayList<String> output) {
        this.allStrings = output;
        this.invalidStrings.forEach((string) -> this.allStrings.add(string));
        return this.allStrings;
    }

}
