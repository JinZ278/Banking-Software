import java.util.ArrayList;

public class CommandStorage {


    public ArrayList<String> invalidStrings;

    CommandStorage() {
        this.invalidStrings = new ArrayList<>();
    }


    public void add(String invalid_string) {
        this.invalidStrings.add(invalid_string);
    }


    public ArrayList<String> printList() {
        return this.invalidStrings;
    }
}
