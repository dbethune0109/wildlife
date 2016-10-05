import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class EndangeredAnimal extends Animal {
  private String health;
  private String age;

  public static final String HEALTH_ILL = "ill";
  public static final String HEALTH_HEALTHY = "healthy";
  public static final String HEALTH_OK = "ok";

  public static final String AGE_INFANT = "Infant";
  public static final String AGE_ADOLESCENT = "Adolescent";
  public static final String AGE_OLD = "Old";

  public EndangeredAnimal(String name, String species, String health, String age){
    this.name = name;
    endangered = true;
    this.health = health;
    this.age = age;
    this.species = species;
  }

  public String getHealth() {
    return health;
  }

  public String getAge(){
    return age;
  }

  public void save() {
    super.save();
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE animals SET health=:health, age=:age WHERE id=:id";
      con.createQuery(sql).addParameter("health", this.health)
        .addParameter("age", this.age)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public static List<EndangeredAnimal> all() {
    String sql = "SELECT * FROM animals WHERE endangered = 'true'";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).throwOnMappingFailure(false)
        .executeAndFetch(EndangeredAnimal.class);
    }
  }
//Exception
  public static EndangeredAnimal find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals where id=:id";
      EndangeredAnimal animal = con.createQuery(sql)
        .addParameter("id", id)
        .throwOnMappingFailure(false)
        .executeAndFetchFirst(EndangeredAnimal.class);
      if(animal == null){
        throw new IndexOutOfBoundsException("I'm sorry, I think this animal does not exist");
      }
      return animal;
    }
  }

  public static EndangeredAnimal findByName(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals where name=:name";
      EndangeredAnimal animal = con.createQuery(sql)
        .addParameter("name", name)
        .throwOnMappingFailure(false)
        .executeAndFetchFirst(EndangeredAnimal.class);
      return animal;
    }
  }
}
