package versione1;

public class Field<T> {

    private String name;
    private String description;
    private T value;
    private boolean obligatory;

    public Field(String name, String description, T value) {
        this.name = name;
        this.description = description;
        this.value = value;
    }

    public Field(String name, String description, Boolean obligatory) {
        this.name = name;
        this.description = description;
        this.value = null;
        this.obligatory = obligatory;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
    public Boolean getObligatory() {
    	return this.obligatory;
    }
    
    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

}

