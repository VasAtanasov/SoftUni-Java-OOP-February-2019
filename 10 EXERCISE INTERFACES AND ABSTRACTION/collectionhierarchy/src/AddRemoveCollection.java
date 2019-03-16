public class AddRemoveCollection extends Collection implements Addable, AddRemovable {
    @Override
    public String remove() {
        if (this.items.isEmpty()) {
            return null;
        }
        return this.items.remove(this.items.size() - 1);
    }

    @Override
    public int add(String item) {
        this.items.add(0, item);
        return this.items.size() - 1;
    }
}
