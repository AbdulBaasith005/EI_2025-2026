package src;

//Director - Controls the building process.

public class ComputerDirector {
    public void construct(Builder builder, String cpu, String ram, String storage) {
        builder.buildCPU(cpu);
        builder.buildRAM(ram);
        builder.buildStorage(storage);
    }
}
