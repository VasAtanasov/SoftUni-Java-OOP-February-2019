package Ex01EventImplementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Dispatcher dispatcher = new Dispatcher();

        NameChangeListener handler1 = new Handler();

        dispatcher.addNameChangeListener(handler1);

        String name;
        while (true){
            if ("End".equals(name = reader.readLine())){
                break;
            }

            dispatcher.setName(name);
        }
    }
}
