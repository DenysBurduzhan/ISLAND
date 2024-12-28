package org.ua.com.javarush.gnew.Island;


import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Organism;

import java.util.*;

public class IslandManager {
    private IslandMap islandMap = IslandMap.getInstance();
    private static IslandManager INSTANCE;


    private IslandManager() {
    }

    public static IslandManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new IslandManager();
        }
        return INSTANCE;
    }


    public void processCell(Cell currentCell) {
        synchronized (currentCell) {
            Set<Class<? extends Organism>> classes = new HashSet<>(currentCell.getResidents().keySet()); // Сет классов в клетке
            for (Class<? extends Organism> clazz : classes) { //Конкретный класс
                List<Organism> organisms = currentCell.getResidents().get(clazz);//Коллекция животных выбранного класса
                if (organisms == null || organisms.isEmpty()) {
                    continue;
                }
                List<Organism> copyOrganisms = new ArrayList<>(organisms);

                for (Organism organism : copyOrganisms) { // конкретное животное
                    organism.isAnimalAlive(currentCell);
                    if (!organisms.contains(organism)) {
                        continue;
                    }
                    int maxStepsCount = organism.getMAX_STEPS_COUNT();
                    organism.eat(currentCell);
                    organism.reproduce(currentCell);

                    for (int i = 0; i < maxStepsCount; i++) {
                        organism.move(islandMap, currentCell);
                    }
                }
            }
        }
    }


    public void growGrassInCells() {
        Cell[][] cells = islandMap.getCells();
        for (Cell[] cellsArray : cells) {
            for (Cell cell : cellsArray) {
                cell.setGrassAmount(cell.getGrassAmount() + 20);
            }
        }
    }


}
