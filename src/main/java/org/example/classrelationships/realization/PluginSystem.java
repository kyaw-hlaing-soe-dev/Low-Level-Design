package org.example.classrelationships.realization;

import java.util.List;
import java.util.ArrayList;

interface Plugin {
    String execute(String text);
    String getName();
}

class SpellCheckPlugin implements Plugin {
    public String execute(String text) {
        return text.replace("teh", "the").replace("adn", "and");
    }

    public String getName() {
        return "Spell Check";
    }
}

class WordCountPlugin implements Plugin {
    public String execute(String text) {
        int count = text.trim().split("\\s+").length;
        return text + "\n[Word count: " + count + "]";
    }

    public String getName() {
        return "Word Count";
    }
}

class UpperCasePlugin implements Plugin {
    public String execute(String text) {
        return text.toUpperCase();
    }

    public String getName() {
        return "Upper Case";
    }
}

class TextEditor {
    private List<Plugin> plugins = new ArrayList<>();

    public void registerPlugin(Plugin plugin) {
        plugins.add(plugin);
        System.out.println("Registered: " + plugin.getName());
    }

    public String runPlugins(String text) {
        String result = text;
        for (Plugin plugin : plugins) {
            System.out.println("Running: " + plugin.getName());
            result = plugin.execute(result);
        }
        return result;
    }
}

public class PluginSystem {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        editor.registerPlugin(new SpellCheckPlugin());
        editor.registerPlugin(new WordCountPlugin());

        String result = editor.runPlugins("teh quick brown fox adn teh lazy dog");
        System.out.println("\nFinal output: " + result);
    }
}