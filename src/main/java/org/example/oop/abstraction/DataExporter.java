package org.example.oop.abstraction;

import java.util.List;

abstract class DataExporter {
    boolean validate(List<String> data) {
        if (data == null || data.isEmpty()) {
            System.out.println("Export failed: No data to export.");
            return false;
        }
        System.out.println("Validation passed. Exporting " + data.size() + " records.");
        return true;
    }

    abstract void export(List<String> data);
}

class CSVExporter extends DataExporter {
    @Override
    void export(List<String> data) {
        if (!validate(data)) {
            return;
        }
        System.out.println("CSV: " + String.join(",", data));
    }
}

class JSONExporter extends DataExporter {
    @Override
    void export(List<String> data) {
        if (!validate(data)) {
            return;
        }
        System.out.println("JSON: " + data.toString());
    }
}



