package org.example.classrelationships.composition;

class CPU {
    private String model;
    private int cores;

    public CPU(String model, int cores) {
        this.model = model;
        this.cores = cores;
    }

    public void describe() {
        System.out.println("CPU: " + model + ", Cores: " + cores);
    }
}

class RAM {
    private int sizeGB;

    public RAM(int sizeGB) {
        this.sizeGB = sizeGB;
    }

    public void describe() {
        System.out.println("RAM: " + sizeGB + " GB");
    }

    public int getSizeGB() { return sizeGB; }
}

class HardDrive {
    private int capacityGB;

    public HardDrive(int capacityGB) {
        this.capacityGB = capacityGB;
    }

    public void describe() {
        System.out.println("Hard Drive: " + capacityGB + " GB");
    }
}

class Computer {
    private String name;
    private CPU cpu;
    private RAM ram;
    private HardDrive hardDrive;

    public Computer(String name, String cpuModel, int cpuCores,
                    int ramGB, int storageGB) {
        this.name = name;
        this.cpu = new CPU(cpuModel, cpuCores);
        this.ram = new RAM(ramGB);
        this.hardDrive = new HardDrive(storageGB);
    }

    public void describeSpecs() {
        System.out.println("Computer: " + name);
        cpu.describe();
        ram.describe();
        hardDrive.describe();
    }

    public void upgradeRAM(int newSizeGB) {
        this.ram = new RAM(newSizeGB);
    }
}

public class ComputerSystem {
    public static void main(String[] args) {
        Computer pc = new Computer("Dev Workstation",
                "Intel i7-13700K", 16, 32, 1000);

        pc.describeSpecs();

        // Challenge: upgrade RAM and verify
        pc.upgradeRAM(64);
        System.out.println("\nAfter RAM upgrade:");
        pc.describeSpecs();

        // When pc is destroyed, all components are destroyed with it.
    }
}