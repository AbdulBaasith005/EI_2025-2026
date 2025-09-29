package src;

//Builder interface - Defines the steps required to build the product.

public interface Builder {
    void buildCPU(String cpu);
    void buildRAM(String ram);
    void buildStorage(String storage);
    Computer getResult();
}
