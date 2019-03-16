public class AddCollection extends Collection implements Addable {

    @Override
    public int add(String item) {
        super.items.add(item);
        return super.items.size() - 1;
    }
}
