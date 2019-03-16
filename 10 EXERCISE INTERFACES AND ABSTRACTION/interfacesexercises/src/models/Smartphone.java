package models;

import interfaces.Browsable;
import interfaces.Callable;

import java.util.ArrayList;
import java.util.List;

public class Smartphone implements Callable, Browsable {
    private List<String> numbers;
    private List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }

    @Override
    public String browse() {
        List<String> result = new ArrayList<>(this.urls.size());
        for (String site : this.urls) {
            if (site.matches("^[^0-9]+$")) {
                result.add(String.format("Browsing: %s!", site));
            } else {
                result.add("Invalid URL!");
            }
        }
        return String.join("\n", result);
    }

    @Override
    public String call() {
        List<String> result = new ArrayList<>(this.numbers.size());
        for (String number : this.numbers) {
            if (number.matches("^[0-9]+$")) {
                result.add(String.format("Calling... %s", number));
            } else {
                result.add("Invalid number!");
            }
        }
        return String.join("\n", result);
    }
}
