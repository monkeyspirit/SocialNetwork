package main.model.event;

/**
 * La classe Field rappresenta un campo di un evento. Esso è dotato di un nome, una descrizione, e un valore.
 * @param <T> il tipo dell'attributo value
 */
public class Field<T> {

    private String name;
    private String description;
    private T value;

    public Field(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Field(String name, String description, T value) {
        this.name = name;
        this.description = description;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Field{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", value=" + value +
                '}';
    }
}

