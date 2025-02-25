package game.entity.inhabitant;

import game.domain.Properties;
import game.entity.OrganismProperty;
import game.entity.animal.Predator;
import game.entity.island.Area;
import game.utils.OrganismPropertyUtil;
import java.io.IOException;
import java.util.Map;

@Properties(filename = "wolf.json")
public class Wolf extends Predator {
    public Wolf() {
    }

    //Area[][] areas = Island.getArea();

    public static final OrganismProperty ORGANISM_PROPERTY;
    public static final Map<String, Integer> NUTRITION_MAP;

    //TODO: make NUTRITION_MAP an ORGANISM_PROPERTY field
    static {
        try {
            ORGANISM_PROPERTY = OrganismPropertyUtil.readOrganismProp(Wolf.class);
            NUTRITION_MAP = OrganismPropertyUtil.readNutritionInf("wolf_nutrition.yaml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Area area;

    @Override
    public OrganismProperty getProperties() {
        return ORGANISM_PROPERTY;
    }

    @Override
    public Map<String, Integer> getNutritionMap() {
        return NUTRITION_MAP;
    }

    @Override
    public String getImage() {
        return "\uD83D\uDC3A";
    }

    @Override
    public String toString() {
        return "Wolf";
    }

    @Override
    public Wolf reproduce() {
        if(healthPoint > 25){
            healthPoint = healthPoint - 25;
            return  new Wolf();
        }
        else return null;
    }

    @Override
    public void move(Area area) {

    }
}
