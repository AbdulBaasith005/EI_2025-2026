package src;

//Concrete Builder - Implements the builder steps to create a specific product.

public class ComputerBuilder implements Builder {
    private final Computer computer = new Computer();

    @Override
    public void buildCPU(String cpu) {
        computer.setCPU(cpu);
    }

    @Override
    public void buildRAM(String ram) {
        computer.setRAM(ram);
    }

    @Override
    public void buildStorage(String storage) {
        computer.setStorage(storage);
    }

    @Override
    public Computer getResult() {
        return computer;
    }
}
