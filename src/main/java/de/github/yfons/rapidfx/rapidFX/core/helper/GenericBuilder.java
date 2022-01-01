package de.github.yfons.rapidfx.rapidFX.core.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class GenericBuilder<CLASS_TYPE> {

    private final Supplier<CLASS_TYPE> instantiator;

    private List<Consumer<CLASS_TYPE>> instanceModifiers = new ArrayList<>();

    public GenericBuilder(Supplier<CLASS_TYPE> instantiator) {
        this.instantiator = instantiator;
    }

    public <VALUE> GenericBuilder<CLASS_TYPE> with(BiConsumer<CLASS_TYPE, VALUE> consumer, VALUE value) {
        Consumer<CLASS_TYPE> c = instance -> consumer.accept(instance, value);
       
        instanceModifiers.add(c);
        return this;
    }

    public CLASS_TYPE build() {
        var value = clone();
        instanceModifiers.clear();
        return value;
    }
    public  CLASS_TYPE clone() {
        CLASS_TYPE value = instantiator.get();
        instanceModifiers.forEach(modifier -> modifier.accept(value));
        return value;
    }
    
}
