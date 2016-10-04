import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;
import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;

public class SightingTest{
  Sighting testSighting;
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Before
  public void setUp(){
    testSighting = new Sighting("somewhere", "Milo");
  }

  @Test
  public void sightings_instantiatesCorrectly_true() {
    assertTrue(testSighting instanceof Sighting);
  }

  @Test
  public void location_instantiatesCorrectly_true() {
    assertEquals("somewhere", testSighting.getLocation());
  }

  @Test
  public void rangername_instantiatesCorrectly_true() {
    assertEquals("Milo", testSighting.getRangerName());
  }

  @Test
  public void equals_returnsTrueIfPropertiesAreSame_true(){
    Sighting testSighting2 = new Sighting("somewhere", "Milo");
    assertTrue(testSighting.equals(testSighting2));
  }

  @Test
  public void save_insertsSightingIntoDatabase_Sighting() {
    testSighting.save();
    Sighting testSighting2 = null;
    try(Connection con = DB.sql2o.open()){
      testSighting2 = con.createQuery("SELECT * FROM sightings WHERE location='somewhere'")
      .executeAndFetchFirst(Sighting.class);
    }
    assertTrue(testSighting2.equals(testSighting));
  }



  @Test
  public void all_returnsAllInstancesOfPerson_true() {
    testSighting.save();
    Sighting testSighting2 = new Sighting("nowhere", "Stephanie");
    testSighting2.save();
    assertEquals(true, Sighting.all().get(0).equals(testSighting));
    assertEquals(true, Sighting.all().get(1).equals(testSighting2));
  }

  @Test
  public void save_assignsIdToSighting() {
    testSighting.save();
    Sighting testSighting2 = Sighting.all().get(0);
    assertEquals(testSighting.getId(), testSighting2.getId());
  }

  @Test
  public void find_returnsSightingWithSameId_secondSighting() {
    testSighting.save();
    Sighting testSighting2 = new Sighting("nowhere", "Stephanie");
    testSighting2.save();
    assertEquals(Sighting.find(testSighting2.getId()), testSighting2);
  }
//exception
  @Test(expected=IndexOutOfBoundsException.class)
  public void find_throwsExceptionIfSightingNotFound() {
    Sighting.find(1);
  }

  @Test
  public void save_insertsCurrentDateIntoDatabase_Sighting() {
    testSighting.save();
    Timestamp savedDate = Sighting.find(testSighting.getId()).getDateSighted();
    Timestamp rightNow = new Timestamp(new Date().getTime());
    assertEquals(rightNow.getDate(), savedDate.getDate());
    assertEquals(rightNow.getHours(), savedDate.getHours());
  }

  // @Test
  // public void getFormattedDate_returnsFormattedDate_Sighting() {
  //   testSighting.save();
  //   Sighting savedSighting = Sighting.find(testSighting.getId());
  //   Timestamp rightNow = new Timestamp(new Date().getTime());
  //   assertEquals(DateFormat.getDateTimeInstance().format(rightNow), savedSighting.getFormattedDate());
  // }

  @Test
  public void delete_deletesEntryInDatabase_0(){
    testSighting.save();
    testSighting.delete();
    assertEquals(0, Sighting.all().size());
  }

  @Test
  public void delete_deletesSightingAssociations(){
    testSighting.save();
    RegularAnimal testRegularAnimal = new RegularAnimal("Milo", "frog");
    testRegularAnimal.save();
    testSighting.addAnimal(testRegularAnimal);
    testSighting.delete();
    assertEquals(0, testRegularAnimal.getSightings().size());
  }

  @Test
  public void addAnimal_addsRegularAnimalToSighting(){
    testSighting.save();
    RegularAnimal testRegularAnimal = new RegularAnimal("FLea Ridden","alligator");
    testRegularAnimal.save();
    testSighting.addAnimal(testRegularAnimal);
    RegularAnimal  savedRegularAnimal = testSighting.getRegularAnimals().get(0);
    assertTrue(testRegularAnimal.equals(savedRegularAnimal));
  }

  @Test
  public void getRegularAnimals_returnsAllRegularAnimals_int(){
    testSighting.save();
    RegularAnimal testRegularAnimal = new RegularAnimal("FLea Ridden","alligator");
    testRegularAnimal.save();
    testSighting.addAnimal(testRegularAnimal);
    List savedAnimals = testSighting.getRegularAnimals();
    assertEquals(1, savedAnimals.size());
    assertTrue(savedAnimals.contains(testRegularAnimal));
  }

  @Test
  public void addAnimal_addsEndangeredAnimalToSighting(){
    testSighting.save();
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("FLea Ridden","potato bug", "ill", "old");
    testEndangeredAnimal.save();
    testSighting.addAnimal(testEndangeredAnimal);
    EndangeredAnimal  savedEndangeredAnimal = testSighting.getEndangeredAnimals().get(0);
    assertTrue(testEndangeredAnimal.equals(savedEndangeredAnimal));
  }

  @Test
  public void getEndangeredAnimals_returnsAllEndangeredAnimals_int(){
    testSighting.save();
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("FLea Ridden","potato bug", "ill", "old");
    testEndangeredAnimal.save();
    testSighting.addAnimal(testEndangeredAnimal);
    List savedAnimals = testSighting.getEndangeredAnimals();
    assertEquals(1, savedAnimals.size());
    assertTrue(savedAnimals.contains(testEndangeredAnimal));
  }

  @Test
  public void allByDate_sortsSightingsListByMostRecent_Sighting(){
    testSighting.save();
    Sighting testSighting2 = new Sighting("nowhere", "Stephanie");
    testSighting2.save();
    assertEquals(testSighting2, Sighting.allByDate().get(0));
  }

  @Test
  public void mostRecent_returnsFiveMostRecent_Sighting(){
    testSighting.save();
    Sighting testSighting2 = new Sighting("nowhere", "Stephanie");
    testSighting2.save();
    Sighting testSighting3 = new Sighting("nowhere", "Stephanie");
    testSighting3.save();
    Sighting testSighting4 = new Sighting("nowhere", "Stephanie");
    testSighting4.save();
    Sighting testSighting5 = new Sighting("nowhere", "Stephanie");
    testSighting5.save();
    Sighting testSighting6 = new Sighting("nowhere", "Stephanie");
    testSighting6.save();
    assertEquals(testSighting6, Sighting.mostRecent().get(0));
    assertFalse(Sighting.mostRecent().contains(testSighting));
  }
  }
