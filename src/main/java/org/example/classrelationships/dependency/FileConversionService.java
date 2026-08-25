package org.example.classrelationships.dependency;

class FileReader {
    public String read(String filePath) {
        System.out.println("Reading file: " + filePath);
        String content = "name,age,city";
        System.out.println("Content: " + content);
        return content;
    }
}

class FormatParser {
    public String parse(String content, String targetFormat) {
        System.out.println("Parsing content to " + targetFormat + " format");
        String parsed = "[{\"name\":\"Alice\",\"age\":30,\"city\":\"NYC\"}]";
        System.out.println("Parsed: " + parsed);
        return parsed;
    }
}

class FileWriter {
    public void write(String filePath, String content) {
        System.out.println("Writing to file: " + filePath);
    }
}

class FileConverter {
    public void convert(String sourcePath, String targetPath, String targetFormat,
                        FileReader reader, FormatParser parser, FileWriter writer) {
        String content = reader.read(sourcePath);
        String parsed = parser.parse(content, targetFormat);
        writer.write(targetPath, parsed);
        System.out.println("File conversion complete: " + sourcePath + " -> " + targetPath);
    }
}

public class FileConversionService {
    public static void main(String[] args) {
        FileConverter converter = new FileConverter();

        FileReader reader = new FileReader();
        FormatParser parser = new FormatParser();
        FileWriter writer = new FileWriter();

        converter.convert("data.csv", "output.json", "JSON", reader, parser, writer);
    }
}