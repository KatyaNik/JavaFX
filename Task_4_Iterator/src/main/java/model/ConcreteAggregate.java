package model;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConcreteAggregate implements Aggregate {
    private List<Image> images = new ArrayList<>();

    // Конструктор для загрузки изображений из каталога
    public ConcreteAggregate(String directoryPath) {
        File folder = new File(directoryPath);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".png") || name.endsWith(".jpg"));

        if (files != null) {
            for (File file : files) {
                images.add(new Image(file.toURI().toString()));
            }
        }
    }

    @Override
    public Iterator<Image> getIterator() {
        return new ImageIterator();
    }

    // Метод для получения всех изображений
    public List<Image> getAllImages() {
        return images;
    }

    // Внутренний класс для итератора
    private class ImageIterator implements Iterator<Image> {
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < images.size();
        }

        @Override
        public Image next() {
            return images.get(current++);
        }
    }
}